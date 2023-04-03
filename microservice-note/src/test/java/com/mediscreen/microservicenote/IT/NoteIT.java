package com.mediscreen.microservicenote.IT;

import com.mediscreen.microservicenote.NoteTest;
import com.mediscreen.microservicenote.model.Note;
import com.mediscreen.microservicenote.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureDataMongo
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class NoteIT {
    @Autowired
    NoteRepository noteRepository;

    @Autowired
    MockMvc mockMvc;

    NoteTest noteTest = new NoteTest();

    String idNote01;
    int idNote02;

    @BeforeEach
    public void setUp() {
        Note save01 = noteRepository.save(noteTest.note01);
        Note save02 = noteRepository.save(noteTest.note02);

        idNote01 = save01.getId();
    }

    @Test
    public void addNoteTest() throws Exception {
        mockMvc.perform(post("/note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(noteTest.note01Json()))
                .andExpect(content().json(noteTest.note01Json()))
                .andExpect(status().isOk());
    }

    @Test
    public void getNoteById() throws Exception {

        mockMvc.perform(get("/note/{id}", idNote01))
                .andExpect(content().json(noteTest.note01Json()))
                .andExpect(status().isOk());

    }

    @Test
    public void getNoteAllTest() throws Exception {

        mockMvc.perform(get("/note/all"))
                .andExpect(content().json(noteTest.noteListJson()))
                .andExpect(status().isOk());

    }

    @Test
    public void updateNoteTest() throws Exception {



        mockMvc.perform(put("/note")
                .contentType(MediaType.APPLICATION_JSON)
                .content(noteTest.noteUpdateJson(idNote01)))
                .andExpect(content().json(noteTest.noteUpdateJson(idNote01)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteNoteTest () throws Exception {

        mockMvc.perform(delete("/note/{id}",idNote01))
                .andExpect(status().isOk());
    }


}
