package com.mediscreen.microservicescore.proxies;

import com.mediscreen.microservicescore.model.NoteBean;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-note")
@LoadBalancerClient(name = "microservice-note")
public interface NoteProxy {

    @GetMapping("/note/patient/{id}")
    public List<NoteBean> getNoteByPatient(@PathVariable int id);
}
