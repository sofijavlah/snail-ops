package com.bepos.model;

import jakarta.persistence.*;

import java.util.List;

//@Entity
public class CaseFile {

    private Long id;
    private String caseNumber;
    private String openedDate;
    private String caseStatus;
    private String priorityLevel;
    private String summary;

    private WantedPerson wantedPerson;
    private Bounty bounty;

    private List<CaseReport> caseReports;
    private List<MarineOfficer> marineOfficers;
    private List<CrimeCategory> crimeCategories;


    // GET & SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String caseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String openedDate() {
        return openedDate;
    }

    public void setOpenedDate(String openedDate) {
        this.openedDate = openedDate;
    }

    public String caseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String priorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String summary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public WantedPerson getWantedPerson() {
        return wantedPerson;
    }

    public void setWantedPerson(WantedPerson wantedPerson) {
        this.wantedPerson = wantedPerson;
    }

    public Bounty getBounty() {
        return bounty;
    }

    public void setBounty(Bounty bounty) {
        this.bounty = bounty;
    }

    public List<CaseReport> getCaseReports() {
        return caseReports;
    }

    public void setCaseReports(List<CaseReport> caseReports) {
        this.caseReports = caseReports;
    }

    public List<MarineOfficer> getMarineOfficers() {
        return marineOfficers;
    }

    public void setMarineOfficers(List<MarineOfficer> marineOfficers) {
        this.marineOfficers = marineOfficers;
    }

    public List<CrimeCategory> getCrimeCategories() {
        return crimeCategories;
    }

    public void setCrimeCategories(List<CrimeCategory> crimeCategories) {
        this.crimeCategories = crimeCategories;
    }

}
