package com.mediscreen.microservicepatient;

import com.mediscreen.microservicepatient.model.Patient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientTest {


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


}
