package com.mediscreen.microservicepatient.service;

import com.mediscreen.microservicepatient.exception.NotFoundException;
import com.mediscreen.microservicepatient.model.Patient;
import com.mediscreen.microservicepatient.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientServiceImpl implements IPatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient addPatient(Patient patient) {

        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(int id) {

        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            throw new NotFoundException("Not found patient with this id");
        }

        return patientOptional.get();
    }

    @Override
    public List<Patient> getAllPatient() {

        List<Patient> patientList = new ArrayList<>();
        patientRepository.findAll().forEach(patientList::add);

        return patientList;

    }

    @Override
    public Patient updatePatient(int id, Patient patient) {

        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) {
            throw new NotFoundException("Patient not found !");
        }

        patient.setId(id);

        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(int id) {

        Optional<Patient> patientOptional = patientRepository.findById(id);

        if (patientOptional.isEmpty()) {
            throw new NotFoundException("Patient not found !");
        }
        patientRepository.deleteById(id);
    }
}
