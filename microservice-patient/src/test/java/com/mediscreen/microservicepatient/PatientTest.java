package com.mediscreen.microservicepatient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mediscreen.microservicepatient.model.Patient;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PatientTest {

    ObjectMapper objectMapper = new ObjectMapper();

    public Patient newPatient01 = new Patient(
            "LastName",
            "FirstName",
            LocalDate.of(2023, 3, 27),
            "Other",
            "addressTest",
            "0000000"
    );

    public Patient newPatient02 = new Patient(
            "LastName",
            "FirstName",
            LocalDate.of(2023, 3, 27),
            "Other",
            "addressTest",
            "0000000"
    );

    public List<Patient> allPatient = Arrays.asList(newPatient01, newPatient02);


    public String newPatientJson01() throws JsonProcessingException {

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper.writeValueAsString(newPatient01);
    }

    public String allPatientJson() throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper.writeValueAsString(allPatient);
    }
}
