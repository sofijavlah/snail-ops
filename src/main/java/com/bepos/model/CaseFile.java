package com.bepos.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class CaseFile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "case_file_seq")
    @SequenceGenerator(name="case_file_seq", sequenceName = "case_file_seq", allocationSize = 1)
    private Long id;

    private String caseNumber;
    private String openedDate;
    private String caseStatus;
    private String priorityLevel;
    private String summary;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wanted_pirate_id", unique = true, nullable = false)
    private WantedPirate wantedPirate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bounty_id", unique = true, nullable = false)
    private Bounty bounty;

    @OneToMany(mappedBy = "caseFile", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CaseReport> reports = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "case_file_marine_officer",
            joinColumns = @JoinColumn(name = "case_file_id"),
            inverseJoinColumns = @JoinColumn(name = "marine_officer_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"case_file_id", "marine_officer_id"})
    )
    private Set<MarineOfficer> marineOfficers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "case_file_crime_category",
            joinColumns = @JoinColumn(name = "case_file_id"),
            inverseJoinColumns = @JoinColumn(name = "crime_category_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"case_file_id", "crime_category_id"})
    )
    private Set<CrimeCategory> crimeCategories = new HashSet<>();


    // GET & SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(String openedDate) {
        this.openedDate = openedDate;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(String priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public WantedPirate getWantedPirate() {
        return wantedPirate;
    }

    public void setWantedPirate(WantedPirate wantedPirate) {
        if (this.wantedPirate == wantedPirate) {
            return;
        }

        WantedPirate previousWantedPirate = this.wantedPirate;
        this.wantedPirate = wantedPirate;

        if (previousWantedPirate != null && previousWantedPirate.getCaseFile() == this) {
            previousWantedPirate.setCaseFile(null);
        }

        if (wantedPirate != null && wantedPirate.getCaseFile() != this) {
            wantedPirate.setCaseFile(this);
        }
    }

    public Bounty getBounty() {
        return bounty;
    }

    public void setBounty(Bounty bounty) {
        if (this.bounty == bounty) {
            return;
        }

        Bounty previousBounty = this.bounty;
        this.bounty = bounty;

        if (previousBounty != null && previousBounty.getCaseFile() == this) {
            previousBounty.setCaseFile(null);
        }

        if (bounty != null && bounty.getCaseFile() != this) {
            bounty.setCaseFile(this);
        }
    }

    public List<CaseReport> getReports() {
        return reports;
    }

    public void addReport(CaseReport report) {
        if (report != null) {
            report.setCaseFile(this);
        }
    }

    public void removeReport(CaseReport report) {
        if (report != null && reports.contains(report)) {
            report.setCaseFile(null);
        }
    }

    public Set<MarineOfficer> getMarineOfficers() {
        return marineOfficers;
    }

    public void addMarineOfficer(MarineOfficer marineOfficer) {
        marineOfficers.add(marineOfficer);
        if (!marineOfficer.getCaseFiles().contains(this)) {
            marineOfficer.getCaseFiles().add(this);
        }
    }

    public void removeMarineOfficer(MarineOfficer marineOfficer) {
        marineOfficers.remove(marineOfficer);
        marineOfficer.getCaseFiles().remove(this);
    }

    public Set<CrimeCategory> getCrimeCategories() {
        return crimeCategories;
    }

    public void addCrimeCategory(CrimeCategory crimeCategory) {
        crimeCategories.add(crimeCategory);
        if (!crimeCategory.getCaseFiles().contains(this)) {
            crimeCategory.getCaseFiles().add(this);
        }
    }

    public void removeCrimeCategory(CrimeCategory crimeCategory) {
        crimeCategories.remove(crimeCategory);
        crimeCategory.getCaseFiles().remove(this);
    }

}
