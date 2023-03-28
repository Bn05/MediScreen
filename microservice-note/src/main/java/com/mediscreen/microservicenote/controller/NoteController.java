package com.mediscreen.microservicenote.controller;

import com.mediscreen.microservicenote.model.Note;
import com.mediscreen.microservicenote.repository.NoteRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoteController {

    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    @PostMapping("/note")
    public Note addNote(@RequestBody Note note) {

        return noteRepository.save(note);
    }

    @GetMapping("/note/{id}")
    public Note getNoteById(@PathVariable String id) {

        return noteRepository.findById(id).get();
    }
}
