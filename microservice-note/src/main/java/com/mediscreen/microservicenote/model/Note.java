package com.mediscreen.microservicenote.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document(collection = "notes")
public class Note {

    @Id
    private String id;
    @NotNull
    private int patientId;
    @NotBlank
    private String note;
    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public Note(int patientId, String note, LocalDate date) {
        this.patientId = patientId;
        this.note = note;
        this.date = date;
    }

    public Note() {
    }

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
