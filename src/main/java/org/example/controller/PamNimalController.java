package org.example.controller;

import org.example.function.Function;
import org.example.model.PamGym;
import org.example.model.PamMaster;
import org.example.model.PamNimal;

import java.util.UUID;

public class PamNimalController {
    private PamNimal pamNimal;
    private Function functionController = new Function();

    public void registerPamNimal(String name, String specie,String breed, int age,
                                 String sex, String currentStatus, PamGym pamGym, PamMaster pamMaster){


        UUID id  = functionController.uniqueId();
        pamNimal = new PamNimal();
    }
}
