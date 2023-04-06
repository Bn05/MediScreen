package com.mediscreen.microservicenote.service;

import com.mediscreen.microservicenote.exception.NotFoundException;
import com.mediscreen.microservicenote.model.Note;
import com.mediscreen.microservicenote.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class NoteServiceImpl implements INoteService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note addNote(Note note) {
        log.info("Add note to dataBase");
        return noteRepository.save(note);
    }

    @Override
    public Note getNoteById(String id) {

        Optional<Note> noteOptional = noteRepository.findById(id);

        if (noteOptional.isEmpty()) {
            log.info("Note with this id = " + id + " not found");
            throw new NotFoundException("Not found note with this id");
        }
        log.info("Get patient with id =" + id + " from dataBase");
        return noteOptional.get();
    }

    @Override
    public List<Note> getNoteByPatient(int id) {
        log.info("Get all notes from database with patient id = "+id);
        return noteRepository.findAllByPatientIdOrderByDateDesc(id);
    }

    @Override
    public List<Note> getNoteAll() {
        log.info("Get all notes from database");
        return noteRepository.findAll();
    }

    @Override
    public Note updateNote(Note note) {
        Optional<Note> noteOptional = noteRepository.findById(note.getId());

        if (noteOptional.isEmpty()) {
            log.info("Note with this id = " + note.getId() + " not found");
            throw new NotFoundException("Not found note with this id");
        }

        log.info("Update note with id ="+note.getId()+" to dataBase");

        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteById(String id) {
        Optional<Note> noteOptional = noteRepository.findById(id);

        if (noteOptional.isEmpty()) {
            log.info("Note with this id = " + id + " not found");
            throw new NotFoundException("Not found note with this id");
        }

        noteRepository.deleteById(id);
        log.info("Delete note with id ="+id+" to dataBase");
    }

    @Override
    public void deleteNoteByPatientId(int id) {

        noteRepository.deleteNoteByPatientId(id);

    }
}


