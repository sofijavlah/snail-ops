package com.bepos.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class CaseReport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "case_report_seq")
    @SequenceGenerator(name="case_report_seq", sequenceName = "case_report_seq", allocationSize = 1)
    private Long id;

    private String reportDate;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_file_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CaseFile caseFile;


    // GET & SET

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

        if (previousCaseFile != null) {
            previousCaseFile.getReports().remove(this);
        }

        if (caseFile != null && !caseFile.getReports().contains(this)) {
            caseFile.getReports().add(this);
        }
    }
}
