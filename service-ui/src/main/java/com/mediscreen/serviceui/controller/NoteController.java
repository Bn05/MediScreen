package com.mediscreen.serviceui.controller;

import com.mediscreen.serviceui.bean.NoteBean;
import com.mediscreen.serviceui.proxies.NoteProxy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/note/add/{idPatient}")
    public String addNote(@PathVariable int idPatient, String note) {

        NoteBean noteBean = new NoteBean();

        noteBean.setPatientId(idPatient);
        noteBean.setNote(note);
        noteBean.setDate(LocalDate.now());


        noteProxy.addNote(noteBean);


        return "redirect:http://localhost:8888/mediscreen/ui/patient/" + idPatient + "/details";
    }

    @GetMapping("/note/update/{id}")
    public String updateNotePage(@PathVariable String id, Model model) {

        model.addAttribute("note", noteProxy.getNote(id));

        return "/updateNote";
    }

    @PostMapping("/note/update")
    public String updateNote(NoteBean note) {

        noteProxy.updateNote(note);

        return "redirect:http://localhost:8888/mediscreen/ui/patient/" + note.getPatientId() + "/details";
    }


    @GetMapping("/note/delete/{id}")
    public String deleteNote(@PathVariable String id) {

        int idPatient = noteProxy.getNote(id).getPatientId();

        noteProxy.deleteNoteById(id);

        return "redirect:http://localhost:8888/mediscreen/ui/patient/" + idPatient + "/details";
    }


}
