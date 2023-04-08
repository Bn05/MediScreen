package com.mediscreen.microservicescore;

import com.mediscreen.microservicescore.model.PatientBean;

import java.time.LocalDate;

public class PatientTest {

    public PatientBean patient = new PatientBean();

    public PatientBean patientHommeMoins30Ans = new PatientBean(
            1,
            "LastName",
            "FirstName",
            LocalDate.now(),
            "M"
    );

    public PatientBean patientHommePlus30Ans = new PatientBean(
            1,
            "LastName",
            "FirstName",
            LocalDate.of(1090, 1, 1),
            "M"
    );

    public PatientBean patientFemmeMoins30Ans = new PatientBean(
            1,
            "LastName",
            "FirstName",
            LocalDate.now(),
            "F"
    );

    public PatientBean patientFemmePlus30Ans = new PatientBean(
            1,
            "LastName",
            "FirstName",
            LocalDate.of(1090, 1, 1),
            "F"
    );

}
