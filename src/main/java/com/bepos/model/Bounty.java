package com.bepos.model;

import jakarta.persistence.*;

//@Entity
public class Bounty {

    private Long id;
    private Double currentAmount;
//    public String issueDate;
//    public String bountyStatus;
//    public String closedDate;

    private CaseFile caseFile;


    // GET & SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double currentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public CaseFile getCaseFile() {
        return caseFile;
    }

    public void setCaseFile(CaseFile caseFile) {
        this.caseFile = caseFile;
    }
}
