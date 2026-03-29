package com.bepos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Bounty {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bounty_seq")
    @SequenceGenerator(name="bounty_seq", sequenceName = "bounty_seq", allocationSize = 1)
    private Long id;

    private Long currentAmount;
//    public String issueDate;
//    public String bountyStatus;
//    public String closedDate;

    @OneToOne(mappedBy = "bounty")
    @JsonIgnore
    private CaseFile caseFile;


    // GET & SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Long currentAmount) {
        this.currentAmount = currentAmount;
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

        if (previousCaseFile != null && previousCaseFile.getBounty() == this) {
            previousCaseFile.setBounty(null);
        }

        if (caseFile != null && caseFile.getBounty() != this) {
            caseFile.setBounty(this);
        }
    }
}
