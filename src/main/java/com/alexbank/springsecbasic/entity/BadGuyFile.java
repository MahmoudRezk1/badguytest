package com.alexbank.springsecbasic.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "bad-guy-file")
public class BadGuyFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session-username")
    private String sessionUsername;
    @Column(name = "uploaded-excel-name")
    private String uploadedExcelName;
    @Column(name = "generated-text-name")
    private String generatedTextName;
    @Column(name = "date")
    private Date date;
    @Column(name = "status")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionUsername() {
        return sessionUsername;
    }

    public void setSessionUsername(String sessionUsername) {
        this.sessionUsername = sessionUsername;
    }

    public String getUploadedExcelName() {
        return uploadedExcelName;
    }

    public void setUploadedExcelName(String uploadedExcelName) {
        this.uploadedExcelName = uploadedExcelName;
    }

    public String getGeneratedTextName() {
        return generatedTextName;
    }

    public void setGeneratedTextName(String generatedTextName) {
        this.generatedTextName = generatedTextName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
