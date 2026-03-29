package com.bepos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "crew_seq")
    @SequenceGenerator(name="crew_seq", sequenceName = "crew_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String shipName;

    @OneToMany(mappedBy = "crew", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<WantedPirate> crewMembers = new ArrayList<>();

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

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public List<WantedPirate> getCrewMembers() {
        return crewMembers;
    }

    public void addCrewMember(WantedPirate pirate) {
        crewMembers.add(pirate);
        pirate.setCrew(this);
    }

    public void removeCrewMember(WantedPirate pirate) {
        crewMembers.remove(pirate);
        pirate.setCrew(null);
    }
}
