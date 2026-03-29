package com.bepos.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class MarineBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marine_base_seq")
    @SequenceGenerator(name="marine_base_seq", sequenceName = "marine_base_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String seaRegion;

    @OneToMany(mappedBy = "marineBase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MarineOfficer> officers = new ArrayList<>();

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

    public String getSeaRegion() {
        return seaRegion;
    }

    public void setSeaRegion(String seaRegion) {
        this.seaRegion = seaRegion;
    }

    public List<MarineOfficer> getOfficers() {
        return officers;
    }

    public void addOfficer(MarineOfficer officer) {
        if (officer != null) {
            officer.setMarineBase(this);
        }
    }

    public void removeOfficer(MarineOfficer officer) {
        if (officer != null && officers.contains(officer)) {
            officer.setMarineBase(null);
        }
    }
}
