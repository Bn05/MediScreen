package com.mediscreen.microservicepatient.service;

import com.mediscreen.microservicepatient.exception.NotFoundException;
import com.mediscreen.microservicepatient.model.Patient;
import com.mediscreen.microservicepatient.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientServiceImpl implements IPatientService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient addPatient(Patient patient) {
        log.info("Add patient to dataBase");
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(int id) {

        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            log.info("Patient with id = "+id+" not found");
            throw new NotFoundException("Not found patient with this id");
        }

        log.info("Get patient with id = "+id+" from dataBase");

        return patientOptional.get();
    }

    @Override
    public List<Patient> getAllPatient() {

        List<Patient> patientList = new ArrayList<>();
        patientRepository.findAll().forEach(patientList::add);

        log.info("Get all patients from dataBase");

        return patientList;
    }

    @Override
    public Patient updatePatient(int id, Patient patient) {

        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            log.info("Patient with id = "+id+" not found");
            throw new NotFoundException("Patient not found !");
        }

        patient.setId(id);

        log.info("Update patient with id ="+id+" to dataBase");

        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(int id) {

        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            log.info("Patient with id = "+id+" not found");
            throw new NotFoundException("Patient not found !");
        }
        patientRepository.deleteById(id);

        log.info("Delete patient with id ="+id+" to dataBase");
    }
}
