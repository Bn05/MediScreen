package com.mediscreen.microservicescore.service;

import org.springframework.stereotype.Service;

@Service
public interface IScoreService {

    public String getDiabeteRisk(int id);
}
