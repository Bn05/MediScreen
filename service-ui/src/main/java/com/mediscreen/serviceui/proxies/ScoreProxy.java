package com.mediscreen.serviceui.proxies;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-score")
@LoadBalancerClient(name = "microservice-score")
public interface ScoreProxy {

    @GetMapping("/score/patient/{id}")
    public String getDiabeteRisk(@PathVariable int id);
}
