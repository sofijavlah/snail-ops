package com.bepos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_seq")
    @SequenceGenerator(name = "file_seq", sequenceName = "file_seq", allocationSize = 1)
    Long id;

    String filename;

    @Transient
    File file;

    @ManyToMany(mappedBy = "uploadedFiles")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<CaseFile> caseFiles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getFilename() {
        return filename;
    }

    public File getFile() {
        return file;
    }

    public Set<CaseFile> getCaseFiles() {
        return caseFiles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
