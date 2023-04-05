package com.mediscreen.microservicescore.UT.Controller;

import com.mediscreen.microservicescore.Controller.ScoreController;
import com.mediscreen.microservicescore.exception.NotFoundException;
import com.mediscreen.microservicescore.service.IScoreService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ScoreControllerTest {

    @InjectMocks
    ScoreController scoreController;

    @MockBean
    IScoreService scoreServiceMock;

    @Autowired
    MockMvc mockMvc;


    @Test
    void ScoreControllerTest() throws Exception {

        when(scoreServiceMock.getDiabeteRisk(1)).thenReturn("Ceci est un test");

        mockMvc.perform(get("/score/patient/{id}", 1))
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(status().isOk());
    }

    @Test
    void ScoreControllerTestBadParam() throws Exception {

        mockMvc.perform(get("/score/patient/{id}", "A"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void ScoreController() throws Exception {

        when(scoreServiceMock.getDiabeteRisk(1)).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/score/patient/{id}", 1))
                .andExpect(status().isNotFound());
    }

}