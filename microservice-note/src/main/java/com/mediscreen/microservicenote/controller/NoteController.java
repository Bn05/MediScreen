package com.mediscreen.microservicenote.controller;

import com.mediscreen.microservicenote.model.Note;
import com.mediscreen.microservicenote.service.INoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    private final INoteService noteService;
    public NoteController(INoteService noteService
    ) {
        this.noteService = noteService;
    }

    @PostMapping("/note")
    public Note addNote(@RequestBody Note note) {
        log.info("Add new note");
        return noteService.addNote(note);
    }


    @GetMapping("/note/{id}")
    public Note getNoteById(@PathVariable String id) {
        log.info("Get note with id = "+id);
        return noteService.getNoteById(id);
    }

    @GetMapping("/note/patient/{id}")
    public List<Note> getNoteByPatient(@PathVariable int id) {
        log.info("Get note with id patient = "+id);
        return noteService.getNoteByPatient(id);
    }

    @GetMapping("/note/all")
    public List<Note> getNoteAll() {
        log.info("Get all note");
        return noteService.getNoteAll();
    }

    @PutMapping("/note")
    public Note updateNote(@RequestBody Note note) {
        log.info("Update note with id = "+ note.getId());
        return noteService.updateNote(note);
    }

    @DeleteMapping("/note/{id}")
    public void deleteNoteById(@PathVariable String id) {
        log.info("Delete note with id = "+id);
        noteService.deleteNoteById(id);
    }

    @DeleteMapping("/note/patient/{id}")
    public void deleteNoteByPatientId(@PathVariable int id){
        log.info("Delete note's Patient id = "+id);
        noteService.deleteNoteByPatientId(id);
    }
}
