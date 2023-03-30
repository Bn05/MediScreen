package com.mediscreen.microservicepatient.IT;

import com.mediscreen.microservicepatient.PatientTest;
import com.mediscreen.microservicepatient.repository.PatientRepository;
import com.mediscreen.microservicepatient.service.IPatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PatientIT {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    IPatientService patientService;

    @Autowired
    MockMvc mockMvc;

    PatientTest patientTest = new PatientTest();

    @BeforeEach
    public void setUp() {

        patientRepository.deleteAll();
        patientRepository.save(patientTest.newPatient01);
        patientRepository.save(patientTest.newPatient02);
  }

    @Test
    public void addPatientTest() throws Exception {

        mockMvc.perform(post("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientTest.newPatientJson01()))
                .andExpect(status().isOk());

    }

    @Test
    public void getPatientTest() throws Exception {
        mockMvc.perform(get("/patient/1"))
                .andExpect(content().json(patientTest.newPatientJson01()))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllPatientTest() throws Exception {
        mockMvc.perform(get("/patient/all"))
                .andExpect(content().json(patientTest.allPatientJson()))
                .andExpect(status().isOk());


    }

    @Test
    public void updatePatientTest() throws Exception {
        mockMvc.perform(put("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientTest.updatePatientJson()))
                .andExpect(content().json(patientTest.updatePatientJson()))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePatient() throws Exception {
        mockMvc.perform(delete("/patient/1"))
                .andExpect(status().isOk());
    }


}
