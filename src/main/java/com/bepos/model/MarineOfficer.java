package com.bepos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class MarineOfficer {

    // PROPS

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marine_seq")
    @SequenceGenerator(name = "marine_seq", sequenceName = "marine_seq", allocationSize = 1)
    private Long id;

    private String fullName;
    private String rank;
    private String badgeNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marine_base_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private MarineBase marineBase;

    @ManyToMany(mappedBy = "marineOfficers")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<CaseFile> caseFiles = new HashSet<>();


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
        if (this.marineBase == marineBase) {
            return;
        }

        MarineBase previousMarineBase = this.marineBase;
        this.marineBase = marineBase;

        if (previousMarineBase != null) {
            previousMarineBase.getOfficers().remove(this);
        }

        if (marineBase != null && !marineBase.getOfficers().contains(this)) {
            marineBase.getOfficers().add(this);
        }
    }

    public Set<CaseFile> getCaseFiles() {
        return caseFiles;
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
