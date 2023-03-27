package com.mediscreen.microservicepatient.UT.controller;

import com.mediscreen.microservicepatient.PatientTest;
import com.mediscreen.microservicepatient.controller.PatientController;
import com.mediscreen.microservicepatient.exception.NotFoundException;
import com.mediscreen.microservicepatient.model.Patient;
import com.mediscreen.microservicepatient.service.IPatientService;
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
class PatientControllerTest {

    @InjectMocks
    PatientController patientController;

    @MockBean
    IPatientService patientService;

    @Autowired
    MockMvc mockMvc;

    PatientTest patientTest = new PatientTest();

    @Test
    void addPatient() throws Exception {

        when(patientService.addPatient(any())).thenReturn(patientTest.newPatient01);

        mockMvc.perform(post("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientTest.newPatientJson01()))
                .andExpect(content().json(patientTest.newPatientJson01()))
                .andExpect(status().isOk());
    }

    @Test
    void addPatientBadRequest() throws Exception {

        when(patientService.addPatient(any())).thenReturn(patientTest.newPatient01);

        mockMvc.perform(post("/patient"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getPatientById() throws Exception {

        when(patientService.getPatientById(1)).thenReturn(patientTest.newPatient01);

        mockMvc.perform(get("/patient/{id}", 1))
                .andExpect(content().json(patientTest.newPatientJson01()))
                .andExpect(status().isOk());

    }

    @Test
    void getPatientByIdBadRequest() throws Exception {

        mockMvc.perform(get("/patient/{id}", "A"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void getPatientByIdNotFound() throws Exception {

        when(patientService.getPatientById(1)).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/patient/{id}", 1))
                .andExpect(status().isNotFound());

    }

    @Test
    void getAllPatient() throws Exception {
        when(patientService.getAllPatient()).thenReturn(patientTest.allPatient);

        mockMvc.perform(get("/patient/all"))
                .andExpect(content().json(patientTest.allPatientJson()))
                .andExpect(status().isOk());
    }

    @Test
    void updatePatient() throws Exception {

        when(patientService.updatePatient(anyInt(), any(Patient.class))).thenReturn(patientTest.newPatient01);

        mockMvc.perform(put("/patient/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientTest.newPatientJson01()))
                .andExpect(content().json(patientTest.newPatientJson01()))
                .andExpect(status().isOk());
    }

    @Test
    void updatePatientBadParam() throws Exception {

        when(patientService.updatePatient(anyInt(), any(Patient.class))).thenReturn(patientTest.newPatient01);

        mockMvc.perform(put("/patient/{id}", "A")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientTest.newPatientJson01()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatePatientBadBody() throws Exception {

        when(patientService.updatePatient(anyInt(), any(Patient.class))).thenReturn(patientTest.newPatient01);

        mockMvc.perform(put("/patient/{id}", 1))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatePatientNotFound() throws Exception {

        when(patientService.updatePatient(anyInt(), any(Patient.class))).thenThrow(NotFoundException.class);

        mockMvc.perform(put("/patient/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientTest.newPatientJson01()))
                .andExpect(status().isNotFound());
    }


    @Test
    void deletePatient() throws Exception {

        doNothing().when(patientService).deletePatient(anyInt());

        mockMvc.perform(delete("/patient/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void deletePatientBadRequest() throws Exception {

        mockMvc.perform(delete("/patient/{id}", "A"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deletePatientNotFound() throws Exception {

     doThrow(NotFoundException.class).when(patientService).deletePatient(anyInt());

        mockMvc.perform(delete("/patient/{id}", 1))
                .andExpect(status().isNotFound());
    }
}