package com.mediscreen.microservicepatient.UT.service;


import com.mediscreen.microservicepatient.PatientTest;
import com.mediscreen.microservicepatient.exception.NotFoundException;
import com.mediscreen.microservicepatient.model.Patient;
import com.mediscreen.microservicepatient.repository.PatientRepository;
import com.mediscreen.microservicepatient.service.PatientServiceImpl;
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
class PatientServiceTest {

    @InjectMocks
    PatientServiceImpl patientService;

    @Mock
    PatientRepository patientRepositoryMock;

    PatientTest patientTest = new PatientTest();

    @Test
    void addPatient() {

        when(patientRepositoryMock.save(any())).thenReturn(patientTest.newPatient01);

        Patient patientCheck = patientService.addPatient(patientTest.newPatient01);

        verify(patientRepositoryMock, times(1)).save(patientTest.newPatient01);
        assertEquals(patientTest.newPatient01, patientCheck);

    }

    @Test
    void getPatientById() {

        when(patientRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(patientTest.newPatient01));

        Patient patientCheck = patientService.getPatientById(1);

        verify(patientRepositoryMock, times(1)).findById(1);
        assertEquals(patientTest.newPatient01, patientCheck);
    }

    @Test
    void getPatientByIdNoExist() {

        when(patientRepositoryMock.findById(any())).thenReturn(Optional.empty());
        boolean test = true;

        try {
            patientService.getPatientById(1);
        }catch (NotFoundException e){
            test = false;
        }

        verify(patientRepositoryMock,times(1)).findById(1);
        assertFalse(test);


    }

    @Test
    void getAllPatient() {

        when(patientRepositoryMock.findAll()).thenReturn(patientTest.allPatient);

        List<Patient> allPatientCheck = patientService.getAllPatient();

        verify(patientRepositoryMock, times(1)).findAll();
        assertEquals(patientTest.allPatient, allPatientCheck);
    }

    @Test
    void updatePatient() {

        when(patientRepositoryMock.save(any())).thenReturn(patientTest.newPatient01);
        when(patientRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(patientTest.newPatient01));

        Patient patientCheck = patientService.updatePatient(1, patientTest.newPatient01);

        verify(patientRepositoryMock, times(1)).save(patientTest.newPatient01);
        assertEquals(patientTest.newPatient01, patientCheck);
    }

    @Test
    void updatePatientNoExist() {

        when(patientRepositoryMock.findById(any())).thenReturn(Optional.empty());
        boolean test = true;

        try {
            patientService.updatePatient(1, patientTest.newPatient01);
        }catch (NotFoundException e){
            test = false;
        }

        verify(patientRepositoryMock,times(0)).save(patientTest.newPatient01);
        assertFalse(test);
    }

    @Test
    void deletePatient() {

        when(patientRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(patientTest.newPatient01));
        doNothing().when(patientRepositoryMock).deleteById(1);

        patientService.deletePatient(1);

        verify(patientRepositoryMock, times(1)).deleteById(1);

    }

    @Test
    void deletePatientNoExist() {

        when(patientRepositoryMock.findById(any())).thenReturn(Optional.empty());
        boolean test = true;

        try {
            patientService.deletePatient(1);
        }catch (NotFoundException e){
            test = false;
        }

        verify(patientRepositoryMock,times(0)).deleteById(1);
        assertFalse(test);

    }
}