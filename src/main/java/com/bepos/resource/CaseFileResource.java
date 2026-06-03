package com.bepos.resource;

import com.bepos.model.CaseFile;
import com.bepos.model.MultipartBody;
import com.bepos.model.UploadedFile;
import com.bepos.service.CaseFileService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.nio.file.Files;
import java.util.List;
import com.bepos.model.CaseReport;

@Path("/case-files")
public class CaseFileResource {

    @Inject
    private CaseFileService caseFileService;

    @Inject
    private EntityManager entityManager;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<CaseFile> caseFiles = null;
        try {
            caseFiles = caseFileService.getAll();
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(caseFiles).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        CaseFile caseFile = null;
        try {
            caseFile = caseFileService.get(id);
        } catch (Exception e) {
            return Response.status(Response.Status.NO_CONTENT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(caseFile).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/reports")
    public Response getReportsByCaseFileId(@PathParam("id") Long id) {
        List<CaseReport> caseReports = null;
        try {
            caseReports = caseFileService.getReportsByCaseFileId(id);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().entity(caseReports).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/by-status")
    public Response getByCaseStatus(@QueryParam("caseStatus") String caseStatus) {
        List<CaseFile> caseFiles = null;
        try {
            caseFiles = caseFileService.getByCaseStatus(caseStatus);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
        return Response.ok().entity(caseFiles).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response add(CaseFile caseFile) {
        try {
            caseFileService.create(caseFile);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        return Response.ok().build();
    }

    @DELETE
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            caseFileService.delete(id);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/upload")
    @Transactional
    public Response uploadFiles(@QueryParam("id") Long id, MultipartBody multipartBody) {
        CaseFile caseFile = null;
        try {
            caseFile = caseFileService.get(id);
            if (caseFile == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            java.nio.file.Path targetPath = java.nio.file.Path.of("uploads", multipartBody.fileName);

            // kreiramo folder uploads ako ne postoji
            Files.createDirectories(targetPath.getParent());

            // ako fajl sa prosledjenim imenom postoji, vracamo 409
            if (Files.exists(targetPath)) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("File with name already exists at: " + targetPath.toString())
                        .build();
            }

            // ako ne postoji fajl vec, kopiramo
            Files.copy(multipartBody.file.uploadedFile(), targetPath);

            // cuvamo fajl u bazu
            UploadedFile uploadedFile = new UploadedFile();
            uploadedFile.setFilename(targetPath.toString());
            entityManager.persist(uploadedFile);

            // dodajemo referencu
            caseFile.addUploadedFile(uploadedFile);

            return Response.ok("File uploaded.").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
