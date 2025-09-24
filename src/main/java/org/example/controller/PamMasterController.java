package org.example.controller;

import org.example.function.Function;
import org.example.model.Address;
import org.example.model.PamMaster;
import org.example.model.PamNimal;

import java.util.List;
import java.util.UUID;

import static org.example.database.Pamydex.*;

public class PamMasterController {
    private final Function functionController = new Function();

    public void registerPamMaster(String name, Address address, String phone, String email, List<PamNimal> pamNimals){
        UUID id = functionController.uniqueId(PAMMASTERS);
        PamMaster pamMaster = new PamMaster(id, name, address, phone, email, pamNimals);

        PAMMASTERS.add(pamMaster);
    }
}
