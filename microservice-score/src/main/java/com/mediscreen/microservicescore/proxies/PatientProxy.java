package com.mediscreen.microservicescore.proxies;

import com.mediscreen.microservicescore.model.PatientBean;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-patient")
@LoadBalancerClient(name = "microservice-patient")
 public interface PatientProxy {

    @GetMapping("/patient/{id}")
    public PatientBean getPatient(@PathVariable int id);
}
