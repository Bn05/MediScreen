package com.mediscreen.microservicescore.Controller;

import com.mediscreen.microservicescore.service.ScoreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @GetMapping("/score/patient/{id}")
    public String getDiabeteRisk(@PathVariable int id){

        return scoreService.getDiabeteRisk(id);
    }
}
