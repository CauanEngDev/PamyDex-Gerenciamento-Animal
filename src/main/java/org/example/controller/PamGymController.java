package org.example.controller;

import org.example.model.Address;
import org.example.model.PamGym;
import org.example.model.PamNimal;

import static org.example.database.Pamydex.*;

import static org.example.commons.Function.*;
import static org.example.commons.IOFunctions.*;

import java.util.UUID;

public class PamGymController {

    public void registerPamGym(String name, String neighborhood, String city, String state) {
        UUID pamGymId = uniqueId(PAMGYMS);
        Address address = new Address(city, state, neighborhood);
        PamGym pamGym = new PamGym(pamGymId, name, address);

        PAMGYMS.add(pamGym);
        saveInfo();
    }

    public void removePamNimal(PamNimal pamNimal, UUID pamGymId) {
        for (PamGym pamGym : PAMGYMS)
            if (pamGym.getId().equals(pamGymId)) {
                pamGym.removePamNimal(pamNimal);
                break;
            }
    }

    public void showPamGyms() {
        for (int i = 0; i < PAMGYMS.size(); i++)
            System.out.printf("[%d] %s",  i, PAMGYMS.get(i).getName());
    }

    public void removePamGym(int choosePamGym) {
        try {
            PamGym pamGym = PAMGYMS.get(choosePamGym);
            PAMGYMS.remove(pamGym);
            saveInfo();
            return;
        } catch (IndexOutOfBoundsException ex) {
            printl("Digite uma opção válida!");
        }

        saveInfo();
    }
}
