package com.mediscreen.microservicenote.UT.service;

import com.mediscreen.microservicenote.NoteTest;
import com.mediscreen.microservicenote.exception.NotFoundException;
import com.mediscreen.microservicenote.model.Note;
import com.mediscreen.microservicenote.repository.NoteRepository;
import com.mediscreen.microservicenote.service.NoteServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@SpringBootTest
class NoteServiceImplTest {

    @InjectMocks
    NoteServiceImpl noteService;

    @Mock
    NoteRepository noteRepositoryMock;

    NoteTest noteTest = new NoteTest();

    @Test
    void addNote() {

        when(noteRepositoryMock.save(any())).thenReturn(noteTest.note01);

        Note noteCheck = noteService.addNote(noteTest.note01);

        verify(noteRepositoryMock,times(1)).save(noteTest.note01);
        assertEquals(noteTest.note01, noteCheck);
    }

    @Test
    void getNoteById() {

        when(noteRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(noteTest.note01));

        Note noteCheck = noteService.getNoteById("1");

        verify(noteRepositoryMock,times(1)).findById("1");
        assertEquals(noteTest.note01, noteCheck);
    }

    @Test
    void getNoteByIdNoExist() {

        when(noteRepositoryMock.findById(any())).thenReturn(Optional.empty());
        boolean test = true;

        try {
            Note noteCheck = noteService.getNoteById("1");
        }catch (NotFoundException e){
            test=false;
        }

        verify(noteRepositoryMock,times(1)).findById("1");
        assertFalse(test);
    }

    @Test
    void getNoteByPatient() {

        when(noteRepositoryMock.findAllByPatientIdOrderByDateDesc(1)).thenReturn(noteTest.noteList);

        List<Note> allNoteCheck = noteService.getNoteByPatient(1);

        verify(noteRepositoryMock,times(1)).findAllByPatientIdOrderByDateDesc(1);
        assertEquals(noteTest.noteList, allNoteCheck);
    }

    @Test
    void getNoteAll() {

        when(noteRepositoryMock.findAll()).thenReturn(noteTest.noteList);

        List<Note> allNoteCheck = noteService.getNoteAll();

        verify(noteRepositoryMock,times(1)).findAll();
        assertEquals(noteTest.noteList, allNoteCheck);
    }

    @Test
    void updateNote() {
        when(noteRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(noteTest.note01));
        when(noteRepositoryMock.save(any())).thenReturn(noteTest.note01);

        Note noteCheck = noteService.updateNote(noteTest.note01);

        verify(noteRepositoryMock,times(1)).save(any());
        assertEquals(noteTest.note01, noteCheck);
    }

    @Test
    void updateNoteNoExist() {
        when(noteRepositoryMock.findById(any())).thenReturn(Optional.empty());
        boolean test = true;

        try {
            Note noteCheck = noteService.updateNote(noteTest.note01);
        }catch (NotFoundException e){
            test=false;
        }

        verify(noteRepositoryMock,times(0)).save(any());
        assertFalse(test);
    }

    @Test
    void deleteNoteById() {
        when(noteRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(noteTest.note01));
        doNothing().when(noteRepositoryMock).deleteById(any());

        noteService.deleteNoteById("1");

        verify(noteRepositoryMock,times(1)).deleteById("1");
    }

    @Test
    void deleteNoteByIdNoExist() {
        when(noteRepositoryMock.findById(any())).thenReturn(Optional.empty());
        boolean test = true;

        try {
            noteService.deleteNoteById("1");
        }catch (NotFoundException e){
            test=false;
        }

        verify(noteRepositoryMock,times(0)).deleteById("1");
        assertFalse(test);
    }
}