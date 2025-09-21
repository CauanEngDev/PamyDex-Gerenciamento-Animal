package org.example.controller;

import org.example.model.PamGym;
import org.example.model.PamMaster;
import org.example.model.PamNimal;

public class PamNimalController {
    private PamNimal pamNimal;

    public void registerPamNimal(String name, String specie,String breed, int age,
                                 String sex, String currentStatus, PamGym pamGym, PamMaster pamMaster){

        pamNimal = new PamNimal();
    }
}
