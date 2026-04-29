package com.bepos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class WantedPirate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pirate_seq")
    @SequenceGenerator(name="pirate_seq", sequenceName = "pirate_seq", allocationSize = 1)
    private Long id;

    private String fullName;

    @ManyToOne
    @JoinColumn(name = "crew_id", nullable = true)
    private Crew crew;

    @OneToOne(mappedBy = "wantedPirate")
    @JsonIgnore
    private CaseFile caseFile;

    @OneToMany(mappedBy = "wantedPirate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TimeResponse> timeResponses = new ArrayList<>();


    @OneToMany(mappedBy="wantedPirate", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CurrencyResponse> currencyResponses = new ArrayList<>();

    // GET & SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        if (this.crew == crew) {
            return;
        }

        Crew previousCrew = this.crew;
        this.crew = crew;

        if (previousCrew != null && previousCrew.getCrewMembers().contains(this)) {
            previousCrew.getCrewMembers().remove(this);
        }

        if (crew != null && !crew.getCrewMembers().contains(this)) {
            crew.getCrewMembers().add(this);
        }
    }

    public CaseFile getCaseFile() {
        return caseFile;
    }

    public void setCaseFile(CaseFile caseFile) {
        if (this.caseFile == caseFile) {
            return;
        }

        CaseFile previousCaseFile = this.caseFile;
        this.caseFile = caseFile;

        if (previousCaseFile != null && previousCaseFile.getWantedPirate() == this) {
            previousCaseFile.setWantedPirate(null);
        }

        if (caseFile != null && caseFile.getWantedPirate() != this) {
            caseFile.setWantedPirate(this);
        }
    }

    public List<TimeResponse> getTimeResponses() {
        return timeResponses;
    }

    public void addTimeResponse(TimeResponse timeResponse) {
        if (timeResponse == null) {
            return;
        }

        timeResponse.setWantedPirate(this);
        timeResponses.add(timeResponse);
    }

    public void removeTimeResponse(TimeResponse timeResponse) {
        if (timeResponse == null) {
            return;
        }

        timeResponses.remove(timeResponse);
        if (timeResponse.getWantedPirate() == this) {
            timeResponse.setWantedPirate(null);
        }
    }

    public List<CurrencyResponse> getCurrencyResponses() {
        return currencyResponses;
    }

    public void addCurrencyResponse(CurrencyResponse currencyResponse) {
        if (currencyResponse == null) {
            return;
        }

        currencyResponse.setWantedPirate(this);
        currencyResponses.add(currencyResponse);
    }

    public void removeCurrencyResponse(CurrencyResponse currencyResponse) {
        if (currencyResponse == null) {
            return;
        }

        currencyResponses.remove(currencyResponse);
        if (currencyResponse.getWantedPirate() == this) {
            currencyResponse.setWantedPirate(null);
        }
    }
}
