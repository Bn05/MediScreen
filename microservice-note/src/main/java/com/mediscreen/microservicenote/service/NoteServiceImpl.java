package com.mediscreen.microservicenote.service;

import com.mediscreen.microservicenote.exception.NotFoundException;
import com.mediscreen.microservicenote.model.Note;
import com.mediscreen.microservicenote.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class NoteServiceImpl implements INoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note addNote(Note note) {

        return noteRepository.save(note);
    }

    @Override
    public Note getNoteById(String id) {

        Optional<Note> noteOptional = noteRepository.findById(id);

        if (noteOptional.isEmpty()) {
            throw new NotFoundException("Not found note with this id");
        }

        return noteOptional.get();
    }

    @Override
    public List<Note> getNoteByPatient(int id) {
        return noteRepository.findAllByPatientIdOrderByDateDesc(id);
    }

    @Override
    public List<Note> getNoteAll() {

        return noteRepository.findAll();
    }

    @Override
    public Note updateNote(Note note) {
        Optional<Note> noteOptional = noteRepository.findById(note.getId());

        if (noteOptional.isEmpty()) {
            throw new NotFoundException("Not found note with this id");
        }

        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteById(String id) {
        Optional<Note> noteOptional = noteRepository.findById(id);

        if (noteOptional.isEmpty()) {
            throw new NotFoundException("Not found note with this id");
        }

        noteRepository.deleteById(id);
    }
}


