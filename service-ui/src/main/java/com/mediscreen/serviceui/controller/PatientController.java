package com.mediscreen.serviceui.controller;

import com.mediscreen.serviceui.bean.PatientBean;
import com.mediscreen.serviceui.proxies.PatientProxy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientController {

    private final PatientProxy patientProxy;

    public PatientController(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    @GetMapping("/")
    public String patientList(Model model) {

        model.addAttribute("allPatient", patientProxy.getAllPatient());

        return "/Patients";
    }

    @GetMapping("/patient/add")
    public String addPatientPage(Model model) {

        PatientBean patientBean = new PatientBean();
        model.addAttribute("patientBean", patientBean);

        return "/addPatientPage";
    }


    @PostMapping("/patient/add")
    public String addPatient(@Validated PatientBean patientBean,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/addPatientPage";
        }

        patientProxy.addPatient(patientBean);

        return "redirect:http://localhost:8888/mediscreen/ui/";
    }

    @GetMapping("/patient/update/{id}")
    public String updatePatientPage(@PathVariable int id, Model model) {

        model.addAttribute("patientBean", patientProxy.getPatientById(id));

        return "/updatePatientPage";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable int id, @Validated PatientBean patientBean, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/updatePatientPage";
        }

        patientProxy.updatePatient(id, patientBean);

        return "redirect:http://localhost:8888/mediscreen/ui/";
    }

    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable int id) {

        patientProxy.deletePatient(id);

        return "redirect:http://localhost:8888/mediscreen/ui/";
    }




}
