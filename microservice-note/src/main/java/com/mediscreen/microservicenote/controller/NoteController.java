package com.mediscreen.microservicenote.controller;

import com.mediscreen.microservicenote.model.Note;
import com.mediscreen.microservicenote.repository.NoteRepository;
import com.mediscreen.microservicenote.service.INoteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private final INoteService noteService;


    public NoteController(INoteService noteService
    ) {
        this.noteService = noteService;

    }


    @PostMapping("/note")
    public Note addNote(@RequestBody Note note) {
        return noteService.addNote(note);
    }


    @GetMapping("/note/{id}")
    public Note getNoteById(@PathVariable String id) {
        return noteService.getNoteById(id);
    }

    @GetMapping("/note/patient/{id}")
    public List<Note> getNoteByPatient(@PathVariable int id) {
        return noteService.getNoteByPatient(id);
    }

    @GetMapping("/note/all")
    public List<Note> getNoteAll() {
        return noteService.getNoteAll();
    }

    @PutMapping("/note")
    public Note updateNote(@RequestBody Note note) {
        return noteService.updateNote(note);
    }

    @DeleteMapping("/note/{id}")
    public void deleteNoteById(@PathVariable String id) {
        noteService.deleteNoteById(id);
    }
}
