package com.mediscreen.serviceui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHomePage(){
        return "/home";
    }



    @GetMapping("/")
    public String patientList() {
        return "redirect:http://localhost:8888/mediscreen/ui/home";
    }

}