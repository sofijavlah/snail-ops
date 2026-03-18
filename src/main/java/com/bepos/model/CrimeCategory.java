package com.bepos.model;

import jakarta.persistence.*;

import java.util.List;

//@Entity
public class CrimeCategory {

    private Long id;
    private String name;
    private String description;

    private List<CaseFile> caseFiles;


    // GET & SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CaseFile> getCaseFiles() {
        return caseFiles;
    }

    public void setCaseFiles(List<CaseFile> caseFiles) {
        this.caseFiles = caseFiles;
    }
}
