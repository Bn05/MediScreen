package com.mediscreen.microservicescore.service;

import com.mediscreen.microservicescore.model.NoteBean;
import com.mediscreen.microservicescore.model.PatientBean;
import com.mediscreen.microservicescore.proxies.NoteProxy;
import com.mediscreen.microservicescore.proxies.PatientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


public class ScoreServiceImpl implements IScoreService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private final NoteProxy noteProxy;
    private final PatientProxy patientProxy;

    //TODO : Modifier d'déclencheur
    private List<String> triggerList = Arrays.asList("avion", "voiture", "bateau");

    public ScoreServiceImpl(NoteProxy noteProxy, PatientProxy patientProxy) {
        this.noteProxy = noteProxy;
        this.patientProxy = patientProxy;
    }

    @Override
    public String getDiabeteRisk(int id) {

        List<Serializable> patientAgeAndGender = getPatientAgeAndGender(id);

        int age = (int) patientAgeAndGender.get(0);
        String gender = (String) patientAgeAndGender.get(1);
        long triggerScore = getTriggerScore(id);


        // Homme de moins de 30ans.
        if (gender.equals("H") && age < 30) {

            if (triggerScore <= 2) {
                return "NONE";
            }

            if (triggerScore <= 4) {
                return "IN DANGER";
            }

            return "EARLY ONSET";
        }

        // Femme de moins de 30ans.
        if (gender.equals("F") && age < 30) {

            if (triggerScore <= 3) {
                return "NONE";
            }

            if (triggerScore <= 6) {
                return "IN DANGER";
            }

            return "EARLY ONSET";
        }

        // Patient (homme ou femme) de plus de 30 ans.
        if (age >= 30) {

            if (triggerScore <= 1) {
                return "NONE";
            }

            if (triggerScore <= 5) {
                return "BORDERLINE";
            }

            if (triggerScore <= 7) {
                return "IN DANGER";
            }

            return "EARLY ONSET";
        }

        return "Nous n'avons pu déterminer les risques du patient";


    }


    private long getTriggerScore(int id) {

        log.info("Get trigger score for patient : " + id);

        return noteProxy.getNoteByPatient(id).stream()
                .map(NoteBean::getNote)
                .map(String::trim)
                .flatMap(Pattern.compile(" ")::splitAsStream)
                .filter(w1 -> triggerList.contains(w1))
                //.distinct() //TODO ME BUST REACTIVATE IN SERVICE
                .count();
    }

    private List<Serializable> getPatientAgeAndGender(int id) {

        PatientBean patient = patientProxy.getPatient(id);

        int age = Period.between(patient.getBirthdate(), LocalDate.now()).getYears();
        String gender = patient.getGender();

        log.info("Get info patient || age = " + age + " || gender = " + gender);

        return Arrays.asList(age, gender);
    }


}


