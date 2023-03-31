package com.mediscreen.microservicenote.UT.controller;

import com.mediscreen.microservicenote.NoteTest;
import com.mediscreen.microservicenote.controller.NoteController;
import com.mediscreen.microservicenote.exception.NotFoundException;
import com.mediscreen.microservicenote.model.Note;
import com.mediscreen.microservicenote.service.INoteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class NoteControllerTest {

    @InjectMocks
    NoteController noteController;

    @MockBean
    INoteService noteService;

    @Autowired
    MockMvc mockMvc;

    NoteTest noteTest = new NoteTest();

    @Test
    void addNote() throws Exception {
        when(noteService.addNote(any())).thenReturn(noteTest.note01);

        mockMvc.perform(post("/note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(noteTest.note01Json()))
                .andExpect(content().json(noteTest.note01Json()))
                .andExpect(status().isOk());
    }

    @Test
    void addNoteBadRequest() throws Exception {

        mockMvc.perform(post("/note"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getNoteById() throws Exception {
        when(noteService.getNoteById(any())).thenReturn(noteTest.note01);

        mockMvc.perform(get("/note/{id})", "1"))
                .andExpect(content().json(noteTest.note01Json()))
                .andExpect(status().isOk());
    }

    @Test
    void getNoteByIdNotFound() throws Exception {
        when(noteService.getNoteById(any())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/note/{id})", "1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getNoteByPatient() throws Exception {
        when(noteService.getNoteByPatient(anyInt())).thenReturn(noteTest.noteList);

        mockMvc.perform(get("/note/patient/{id}", 1))
                .andExpect(content().json(noteTest.noteListJson()))
                .andExpect(status().isOk());
    }

    @Test
    void getNoteByPatientBadRequest() throws Exception {

        mockMvc.perform(get("/note/patient/{id}", "AB"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getNoteAll() throws Exception {
        when(noteService.getNoteAll()).thenReturn(noteTest.noteList);

        mockMvc.perform(get("/note/all"))
                .andExpect(content().json(noteTest.noteListJson()))
                .andExpect(status().isOk());
    }

    @Test
    void updateNote() throws Exception {
        when(noteService.updateNote(any(Note.class))).thenReturn(noteTest.note01);

        mockMvc.perform(put("/note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(noteTest.note01Json()))
                .andExpect(content().json(noteTest.note01Json()))
                .andExpect(status().isOk());
    }

    @Test
    void updateNoteBadBody() throws Exception {

        mockMvc.perform(put("/note"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateNoteNotFound() throws Exception {
        when(noteService.updateNote(any(Note.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(put("/note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(noteTest.note01Json()))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteNoteById() throws Exception {
        doNothing().when(noteService).deleteNoteById(anyString());

        mockMvc.perform(delete("/note/{id}", "A"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteNoteByIdNotFound() throws Exception {
       doThrow(NotFoundException.class).when(noteService).deleteNoteById(anyString());

        mockMvc.perform(delete("/note/{id}", "A"))
                .andExpect(status().isNotFound());
    }
}