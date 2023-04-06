package com.mediscreen.microservicescore;

import com.mediscreen.microservicescore.model.NoteBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteTest {

    public List<NoteBean> getNoteTest(int idPatient, int nbOfTrigger) {

        List<String> triggerList = Arrays.asList(
                "Hémoglobine A1C",
                "Microalbumine",
                "Taille",
                "Poids",
                "Fumeur",
                "Anormal",
                "Cholestérol",
                "Vertige",
                "Rechute",

                "Réaction",
                "Anticorps");

        List<NoteBean> noteList = new ArrayList<>();

        for (int i = 0; i <= nbOfTrigger; i++) {

            NoteBean noteBean = new NoteBean(
                    1,
                    triggerList.get(i),
                    LocalDate.now()
            );

            noteList.add(noteBean);
        }

        return noteList;
    }
}
