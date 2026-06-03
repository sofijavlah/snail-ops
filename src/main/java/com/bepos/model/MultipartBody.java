package com.bepos.model;

import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.jboss.resteasy.reactive.RestForm;

public class MultipartBody {

    @RestForm("file")
    public FileUpload file;

    @RestForm("fileName")
    public String fileName;
}
