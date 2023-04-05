package com.mediscreen.microservicepatient.controller;

import com.mediscreen.microservicepatient.model.Patient;
import com.mediscreen.microservicepatient.service.IPatientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PatientController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final IPatientService patientService;

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patient")
    public Patient addPatient(@RequestBody @Valid Patient patient) {

        log.info("Add new patient");
        return patientService.addPatient(patient);
    }

    @GetMapping("/patient/{id}")
    public Patient getPatientById(@PathVariable int id) {

        log.info("Get patient with id = "+id);
        return patientService.getPatientById(id);
    }

    @GetMapping("/patient/all")
    public List<Patient> getAllPatient() {

        log.info("Get all patient");
        return patientService.getAllPatient();
    }

    @PutMapping("/patient/{id}")
    public Patient updatePatient(@PathVariable int id, @RequestBody Patient patient) {

        log.info("Update patient with id = "+id);
        return patientService.updatePatient(id, patient);
    }

    @DeleteMapping("patient/{id}")
    public void deletePatient(@PathVariable int id) {

        log.info("Delete patient with id = "+id);
        patientService.deletePatient(id);
    }


}
