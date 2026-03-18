package com.bepos.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class MarineOfficer {

    // PROPS

    @Id
//    @GeneratedValue
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marine_seq")
    @SequenceGenerator(name = "marine_seq", sequenceName = "marine_seq", allocationSize = 1)
    private Long id;
    private String fullName;
    private String rank;
    private String badgeNumber;

    @Transient
    private MarineBase marineBase;
    @Transient
    private List<CaseFile> caseFiles;


    // GET & SET

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRank() {
        return rank;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public MarineBase getMarineBase() {
        return marineBase;
    }

    public void setMarineBase(MarineBase marineBase) {
        this.marineBase = marineBase;
    }

    public List<CaseFile> getCaseFiles() {
        return caseFiles;
    }

    public void setCaseFiles(List<CaseFile> caseFiles) {
        this.caseFiles = caseFiles;
    }

    // FUNCTIONS & OVERRIDES

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarineOfficer marine = (MarineOfficer) o;
        return Objects.equals(id, marine.id)
                && Objects.equals(badgeNumber, marine.badgeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, badgeNumber);
    }

}
