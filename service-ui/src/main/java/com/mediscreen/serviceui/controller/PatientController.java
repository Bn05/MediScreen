package com.mediscreen.serviceui.controller;

import com.mediscreen.serviceui.bean.PatientBean;
import com.mediscreen.serviceui.proxies.PatientProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    private final PatientProxy patientProxy;

    public PatientController(PatientProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    @GetMapping("/test")
    public String test() {
        return "Ceci est un test";
    }

    @GetMapping("/patient/{id}")
    public PatientBean getPatientById(@PathVariable int id) {

        return patientProxy.getPatientById(id);

    }
}
