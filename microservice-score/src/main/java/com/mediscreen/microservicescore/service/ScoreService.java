package com.mediscreen.microservicescore.service;

import com.mediscreen.microservicescore.model.NoteBean;
import com.mediscreen.microservicescore.model.PatientBean;
import com.mediscreen.microservicescore.proxies.NoteProxy;
import com.mediscreen.microservicescore.proxies.PatientProxy;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ScoreService {

    private final NoteProxy noteProxy;
    private final PatientProxy patientProxy;

    private List<String> triggerList = Arrays.asList("avion", "voiture", "bateau");

    public ScoreService(NoteProxy noteProxy, PatientProxy patientProxy) {
        this.noteProxy = noteProxy;
        this.patientProxy = patientProxy;
    }


    public String getDiabeteRisk(int id) {

        List<Serializable> patientAndGender = getPatientAndGender(id);

        int age = (int) patientAndGender.get(0);
        String gender = (String) patientAndGender.get(1);
        long triggerScore = getTriggerScore(id);


        if (triggerScore == 0) {
            return "None";
        }

        if (triggerScore == 2 && age > 30) {
            return "Bordeline";
        }

        if (gender.equals("H") && age < 30 && triggerScore == 3) {
            return "In Danger";
        }

        if (gender.equals("F") && age < 30 && triggerScore == 4) {
            return "In Danger";
        }

        if (age > 30 && triggerScore == 6 ) {
            return "In Danger";
        }

        if (gender.equals("H") && age < 30 && triggerScore == 5) {
            return "Early onset";
        }

        if (gender.equals("F") && age < 30 && triggerScore == 7) {
            return "Early onset";
        }

        if (age > 30 && triggerScore >= 8) {
            return "Early onset";
        }

        return "null";
    }


    public long getTriggerScore(int id) {

        return noteProxy.getNoteByPatient(id).stream()
                .map(NoteBean::getNote)
                .map(String::trim)
                .flatMap(Pattern.compile(" ")::splitAsStream)
                .filter(w1 -> triggerList.contains(w1))
                .distinct()
                .count();
    }

    public List<Serializable> getPatientAndGender(int id) {

        PatientBean patient = patientProxy.getPatient(id);

        int age = Period.between(patient.getBirthdate(), LocalDate.now()).getYears();
        String gender = patient.getGender();

        return Arrays.asList(age, gender);
    }


}


