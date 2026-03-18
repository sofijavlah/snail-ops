package com.bepos.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Crew {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String shipName;

    @Transient
    private List<WantedPerson> wantedPersons;


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

    public String shipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public List<WantedPerson> getWantedPersons() {
        return wantedPersons;
    }

    public void setWantedPersons(List<WantedPerson> wantedPersons) {
        this.wantedPersons = wantedPersons;
    }
}
