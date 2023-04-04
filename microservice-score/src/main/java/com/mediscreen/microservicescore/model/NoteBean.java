package com.mediscreen.microservicescore.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class NoteBean {

    private String id;
    private int patientId;
    private String note;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
