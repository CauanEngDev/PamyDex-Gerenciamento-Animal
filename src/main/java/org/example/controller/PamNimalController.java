package org.example.controller;

import static org.example.database.Pamydex.*;
import static org.example.commons.Function.*;
import org.example.model.PamGym;
import org.example.model.PamMaster;
import org.example.model.PamNimal;

import java.util.UUID;

public class PamNimalController {
    private PamNimal pamNimal;

    public void registerPamNimal(String name, String specie,String breed, int age,
                                 String sex, String currentStatus){

        PamMaster pamMaster;
        UUID id = uniqueId(PAMNIMALS);
        pamNimal = new PamNimal(id, name, specie, breed, age, sex, currentStatus, pamGym, pamMaster);

        PAMNIMALS.add(pamNimal);
    }
}
