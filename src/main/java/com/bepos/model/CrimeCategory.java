package com.bepos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class CrimeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crime_cat_seq")
    @SequenceGenerator(name = "crime_cat_seq", sequenceName = "crime_cat_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String description;

    @ManyToMany(mappedBy = "crimeCategories")
    @JsonIgnore
    private Set<CaseFile> caseFiles = new HashSet<>();


    // GET & SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CaseFile> getCaseFiles() {
        return caseFiles;
    }
}
