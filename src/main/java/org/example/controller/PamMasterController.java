package org.example.controller;

import static org.example.commons.Function.*;
import org.example.model.Address;
import org.example.model.PamMaster;
import org.example.model.PamNimal;

import java.util.UUID;

import static org.example.database.Pamydex.*;

public class PamMasterController {

    public void registerPamMaster(String name, String phone, String email, String neigborhood, String city, String state) {
        UUID id = uniqueId(PAMMASTERS);
        Address address = new Address(neigborhood, city, state);
        PamMaster pamMaster = new PamMaster(id, name, address, phone, email);

        PAMMASTERS.add(pamMaster);
        saveInfo();
    }

    public void removePamNimal(PamNimal pamNimal, UUID pamMasterId){
        for (PamMaster pamMaster : PAMMASTERS)
            if (pamMasterId.equals(pamMaster.getId())) {
                pamMaster.removePamNimal(pamNimal);
                break;
            }
        saveInfo();
    }

    public void showPamMasters() {
        for (int i = 0; i < PAMMASTERS.size(); i++)
            System.out.printf("[%d] %s",  i + 1, PAMMASTERS.get(i).getName());
    }

    public void removePamMaster
}
