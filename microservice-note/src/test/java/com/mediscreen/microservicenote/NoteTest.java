package com.mediscreen.microservicenote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mediscreen.microservicenote.model.Note;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class NoteTest {

    ObjectMapper objectMapper = new ObjectMapper();


    public Note note01 = new Note(
            1,
            "Ceci est une note",
            LocalDate.of(2023, 3, 27)
    );

    public Note note02 = new Note(
            1,
            "Ceci est une seconde note",
            LocalDate.of(2023, 3, 26)
    );

    public Note noteUpdate = new Note(
            1,
            "Ceci est une seconde note Update",
            LocalDate.of(2024, 3, 26)
    );

    public List<Note> noteList = Arrays.asList(note01, note02);
    public String note01Json() throws JsonProcessingException {

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper.writeValueAsString(note01);
    }

    public String note02Json() throws JsonProcessingException {

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper.writeValueAsString(note02);
    }

    public String noteUpdateJson() throws JsonProcessingException {

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper.writeValueAsString(noteUpdate);
    }

    public String noteListJson() throws JsonProcessingException {

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper.writeValueAsString(noteList);
    }
}
