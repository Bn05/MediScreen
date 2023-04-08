package com.mediscreen.serviceui.controller;

import com.mediscreen.serviceui.bean.NoteBean;
import com.mediscreen.serviceui.bean.PatientBean;
import com.mediscreen.serviceui.proxies.NoteProxy;
import com.mediscreen.serviceui.proxies.PatientProxy;
import com.mediscreen.serviceui.proxies.ScoreProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PatientController {

    private final PatientProxy patientProxy;
    private final NoteProxy noteProxy;
    private final ScoreProxy scoreProxy;

    public PatientController(PatientProxy patientProxy, NoteProxy noteProxy, ScoreProxy scoreProxy) {
        this.patientProxy = patientProxy;
        this.noteProxy = noteProxy;
        this.scoreProxy = scoreProxy;
    }

    @GetMapping("/patient")
    public String patientList(Model model) {

        model.addAttribute("allPatient", patientProxy.getAllPatient());

        return "patients";
    }

    @GetMapping("/patient/{id}/details")
    public String patientDetails(@PathVariable int id, Model model) {

        PatientBean patient = patientProxy.getPatientById(id);
        List<NoteBean> notes = noteProxy.getNoteByPatient(id);
        String score = scoreProxy.getDiabeteRisk(id);

        model.addAttribute("patientBean", patient);
        model.addAttribute("notes", notes);
        model.addAttribute("score", score);

        return "historyPage";

    }

    @GetMapping("/patient/add")
    public String addPatientPage(Model model) {

        PatientBean patientBean = new PatientBean();
        model.addAttribute("patientBean", patientBean);

        return "addPatient";
    }


    @PostMapping("/patient/add")
    public String addPatient(@Validated PatientBean patientBean,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "addPatient";
        }

        patientProxy.addPatient(patientBean);

        return "redirect:http://localhost:8888/mediscreen/ui/patient";
    }

    @GetMapping("/patient/update/{id}")
    public String updatePatientPage(@PathVariable int id, Model model) {

        model.addAttribute("patientBean", patientProxy.getPatientById(id));

        return "updatePatient";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable int id, @Validated PatientBean patientBean, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "updatePatient";
        }

        patientProxy.updatePatient(id, patientBean);

        return "redirect:http://localhost:8888/mediscreen/ui/patient";
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable int id) {

        patientProxy.deletePatient(id);
        noteProxy.deleteNoteByPatientId(id);

        return "redirect:http://localhost:8888/mediscreen/ui/patient";
    }


}
