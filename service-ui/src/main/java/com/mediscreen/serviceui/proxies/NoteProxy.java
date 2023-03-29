package com.mediscreen.serviceui.proxies;

import com.mediscreen.serviceui.bean.NoteBean;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservice-note")
@LoadBalancerClient(name = "microservice-note")
public interface NoteProxy {

    //CREATE
    @PostMapping(value = "/note")
    public NoteBean addNote(@RequestBody NoteBean noteBean);

    //READ
    @GetMapping("/note/{id}")
    public NoteBean getNote(@PathVariable String id);

    //READ BY PATIENT ID
    @GetMapping("/note/patient/{id}")
    public List<NoteBean> getNoteByPatient(@PathVariable int id);

    //READ ALL
    @GetMapping("/note/all")
    public NoteBean getNoteAll();

    //UPDATE
    @PutMapping("/note")
    public NoteBean updateNote (@RequestBody NoteBean noteBean);

    //DELETE
    @DeleteMapping("/note/{id}")
    public void deleteNoteById(@PathVariable String id);
}
