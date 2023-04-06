package com.mediscreen.serviceui.controller;

import com.mediscreen.serviceui.bean.NoteBean;
import com.mediscreen.serviceui.proxies.NoteProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;


@Controller
public class NoteController {

    private final NoteProxy noteProxy;

    public NoteController(NoteProxy noteProxy) {
        this.noteProxy = noteProxy;
    }

    @PostMapping("/add/note/{idPatient}")
    public String addNote(@PathVariable int idPatient, String note) {

        System.out.println(note);

        NoteBean noteBean = new NoteBean();

        noteBean.setPatientId(idPatient);
        noteBean.setNote(note);
        noteBean.setDate(LocalDate.now());


        noteProxy.addNote(noteBean);


        return "redirect:http://localhost:8888/mediscreen/ui/patient/"+idPatient+"/details";
    }



}
