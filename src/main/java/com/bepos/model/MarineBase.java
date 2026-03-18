package com.bepos.model;

import jakarta.persistence.*;

import java.util.List;

//@Entity
public class MarineBase {

    private Long id;
    private String name;
    private String seaRegion;

    private List<MarineOfficer> marineOfficers;


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

    public String seaRegion() {
        return seaRegion;
    }

    public void setSeaRegion(String seaRegion) {
        this.seaRegion = seaRegion;
    }

    public List<MarineOfficer> getMarineOfficers() {
        return marineOfficers;
    }

    public void setMarineOfficers(List<MarineOfficer> marineOfficers) {
        this.marineOfficers = marineOfficers;
    }
}
