package com.mediscreen.microservicescore.Controller;

import com.mediscreen.microservicescore.service.IScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private final IScoreService scoreService;

    public ScoreController(IScoreService scoreService) {
        this.scoreService = scoreService;
    }


    @GetMapping("/score/patient/{id}")
    public String getDiabeteRisk(@PathVariable int id){

        log.info("Get diabetes risk, patient with id = "+id);
        return scoreService.getDiabeteRisk(id);
    }
}
