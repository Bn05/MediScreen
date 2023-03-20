package com.mediscreen.microservicepatient.controller;

import com.mediscreen.microservicepatient.model.Patient;
import com.mediscreen.microservicepatient.service.IPatientService;
import jakarta.ws.rs.DELETE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    private final IPatientService patientService;

    public PatientController(IPatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/patient")
    public Patient addPatient(@RequestBody Patient patient) {

        return patientService.addPatient(patient);
    }

    @GetMapping("/patient/{id}")
    public Patient getPatientById(@PathVariable int id) {
        return patientService.getPatientById(id);
    }

    @GetMapping("/patient/all")
    public List<Patient> getAllPatient() {
        return patientService.getAllPatient();
    }

    @PutMapping("/patient/{id}")
    public Patient updatePatient(@PathVariable int id, @RequestBody Patient oldPatient) {

        oldPatient.setId(id);

        return patientService.updatePatient(oldPatient);
    }

    @DeleteMapping("patient/{id}")
    public void deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
    }


}
