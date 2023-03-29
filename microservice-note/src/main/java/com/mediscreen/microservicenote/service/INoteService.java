package com.mediscreen.microservicenote.service;

import com.mediscreen.microservicenote.model.Note;

import java.util.List;

public interface INoteService {

    //CREATE
    public Note addNote(Note note);

    //READ
    public Note getNoteById(String id);

    //READ BY ID PATIENT
    public List<Note> getNoteByPatient(int id);

    //READ ALL
    public List<Note> getNoteAll();

    //UPDATE
    public Note updateNote(Note note);

    //DELETE
    public void deleteNoteById(String id);
}
