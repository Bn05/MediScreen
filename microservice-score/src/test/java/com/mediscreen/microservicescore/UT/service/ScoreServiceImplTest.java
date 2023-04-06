package com.mediscreen.microservicescore.UT.service;

import com.mediscreen.microservicescore.NoteTest;
import com.mediscreen.microservicescore.PatientTest;
import com.mediscreen.microservicescore.model.NoteBean;
import com.mediscreen.microservicescore.model.PatientBean;
import com.mediscreen.microservicescore.proxies.NoteProxy;
import com.mediscreen.microservicescore.proxies.PatientProxy;
import com.mediscreen.microservicescore.service.ScoreServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ScoreServiceImplTest {

    @InjectMocks
    ScoreServiceImpl scoreService;

    @Mock
    PatientProxy patientProxy;

    @Mock
    NoteProxy noteProxy;

    PatientTest patientTest = new PatientTest();

    NoteTest noteTest = new NoteTest();

    @Test
    public void getDiabeteRiskPatientHommeMoins30AnsNone() {

        PatientBean patient = patientTest.patientHommeMoins30Ans;
        List<NoteBean> noteBeanList = noteTest.getNoteTest(1,0);

        when(noteProxy.getNoteByPatient(1)).thenReturn(noteBeanList);
        when(patientProxy.getPatient(1)).thenReturn(patient);

        String test01 = scoreService.getDiabeteRisk(1);

        assertEquals("NONE", test01);


    }
    @Test
    public void getDiabeteRiskPatientHommeMoins30AnsInDanger() {

        PatientBean patient = patientTest.patientHommeMoins30Ans;
        List<NoteBean> noteBeanList = noteTest.getNoteTest(1,3);

        when(noteProxy.getNoteByPatient(1)).thenReturn(noteBeanList);
        when(patientProxy.getPatient(1)).thenReturn(patient);

        String test01 = scoreService.getDiabeteRisk(1);

        assertEquals("IN DANGER", test01);
    }
    @Test
    public void getDiabeteRiskPatientHommeMoins30AnsEarlyOnset() {
        PatientBean patient = patientTest.patientHommeMoins30Ans;
        List<NoteBean> noteBeanList = noteTest.getNoteTest(1,5);

        when(noteProxy.getNoteByPatient(1)).thenReturn(noteBeanList);
        when(patientProxy.getPatient(1)).thenReturn(patient);

        String test01 = scoreService.getDiabeteRisk(1);

        assertEquals("EARLY ONSET", test01);

    }

    @Test
    public void getDiabeteRiskPatientFemmeMoins30AnsNone() {
        PatientBean patient = patientTest.patientFemmeMoins30Ans;
        List<NoteBean> noteBeanList = noteTest.getNoteTest(1,3);

        when(noteProxy.getNoteByPatient(1)).thenReturn(noteBeanList);
        when(patientProxy.getPatient(1)).thenReturn(patient);

        String test01 = scoreService.getDiabeteRisk(1);

        assertEquals("NONE", test01);
    }

    @Test
    public void getDiabeteRiskPatientFemmeMoins30AnsInDanger() {
        PatientBean patient = patientTest.patientFemmeMoins30Ans;
        List<NoteBean> noteBeanList = noteTest.getNoteTest(1,4);

        when(noteProxy.getNoteByPatient(1)).thenReturn(noteBeanList);
        when(patientProxy.getPatient(1)).thenReturn(patient);

        String test01 = scoreService.getDiabeteRisk(1);

        assertEquals("IN DANGER", test01);
    }

    @Test
    public void getDiabeteRiskPatientFemmeMoins30AnsEarlyOnset() {
        PatientBean patient = patientTest.patientFemmeMoins30Ans;
        List<NoteBean> noteBeanList = noteTest.getNoteTest(1,7);

        when(noteProxy.getNoteByPatient(1)).thenReturn(noteBeanList);
        when(patientProxy.getPatient(1)).thenReturn(patient);

        String test01 = scoreService.getDiabeteRisk(1);

        assertEquals("EARLY ONSET", test01);
    }

    @Test
    public void getDiabeteRiskPatientPlus30AnsNone() {
        PatientBean patient = patientTest.patientHommePlus30Ans;
        List<NoteBean> noteBeanList = noteTest.getNoteTest(1,0);

        when(noteProxy.getNoteByPatient(1)).thenReturn(noteBeanList);
        when(patientProxy.getPatient(1)).thenReturn(patient);

        String test01 = scoreService.getDiabeteRisk(1);

        assertEquals("NONE", test01);
    }

    @Test
    public void getDiabeteRiskPatientPlus30AnsBorderline() {
        PatientBean patient = patientTest.patientHommePlus30Ans;
        List<NoteBean> noteBeanList = noteTest.getNoteTest(1,5);

        when(noteProxy.getNoteByPatient(1)).thenReturn(noteBeanList);
        when(patientProxy.getPatient(1)).thenReturn(patient);

        String test01 = scoreService.getDiabeteRisk(1);

        assertEquals("BORDERLINE", test01);
    }

    @Test
    public void getDiabeteRiskPatientPlus30AnsInDanger() {
        PatientBean patient = patientTest.patientHommePlus30Ans;
        List<NoteBean> noteBeanList = noteTest.getNoteTest(1,7);

        when(noteProxy.getNoteByPatient(1)).thenReturn(noteBeanList);
        when(patientProxy.getPatient(1)).thenReturn(patient);

        String test01 = scoreService.getDiabeteRisk(1);

        assertEquals("IN DANGER", test01);
    }

    @Test
    public void getDiabeteRiskPatientPlus30AnsEarlyOnset() {
        PatientBean patient = patientTest.patientHommePlus30Ans;
        List<NoteBean> noteBeanList = noteTest.getNoteTest(1,8);

        when(noteProxy.getNoteByPatient(1)).thenReturn(noteBeanList);
        when(patientProxy.getPatient(1)).thenReturn(patient);

        String test01 = scoreService.getDiabeteRisk(1);

        assertEquals("EARLY ONSET", test01);
    }










}