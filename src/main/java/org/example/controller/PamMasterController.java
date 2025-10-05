package org.example.controller;

import static org.example.commons.Function.*;
import org.example.model.Address;
import org.example.model.PamMaster;
import org.example.model.PamNimal;

import java.util.UUID;

import static org.example.commons.IOFunctions.*;
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

    public UUID showPamMasters(int pm) {
        int i = 1;
        String choose;

        for (PamMaster pamMaster : PAMMASTERS) {
            if (!(pamMaster.getId().equals(PAMMASTERS.get(pm).getId())))
                System.out.printf("[%d] %s", i++, pamMaster.getName());
            else {
                System.out.printf("[NÃO ESCOLHÍVEL] %s", pamMaster.getName());
                i++;
            }
        }
        while (true) {
            try {
                choose = ask("Escolha uma opção: ");
                if (Integer.parseInt(choose) != pm) return PAMMASTERS.get(Integer.parseInt(choose) - 1).getId();
                else printl("Não pode escolher o mesmo PamMaster");
            } catch (IndexOutOfBoundsException ex) {
                printl("Digite uma opção válida!");
            } catch (NumberFormatException ex) {
                printl("Digite um numero!");
            }
        }
    }

    public boolean removePamMaster(int choosePamMaster, UUID newPamMaster) {
        try {
            PamMaster pamMaster = PAMMASTERS.get(choosePamMaster);
            for (PamNimal p : pamMaster.getPamNimals())
                p.setPamMaster(newPamMaster);
            PAMMASTERS.remove(choosePamMaster);
            saveInfo();
            return false;
        } catch (IndexOutOfBoundsException ex) {
            printl("Digite uma opção válida!");
        }
        return true;
    }

    public void listPamMaster() {
        if (PAMMASTERS.isEmpty()) {
            printl("Nenhum(a) PamMaster cadastrado(a)!");
        } else {
            for (PamMaster p : PAMMASTERS) {
                printf("""
                        Id -> %s
                        Nome -> %s
                        Telefone -> %s
                        Email -> %s
                        """,
                        p.getId(), p.getName(), p.getFormatedPhone(), p.getEmail());
                Address address = p.getAddress();
                printf("""
                Bairro -> %s
                Cidade -> %s
                Estado -> %s
                """, address.neighborhood(), address.city(), address.state());
                printl("Nomes dos PamNimals:");
                for (PamNimal pamNimal : p.getPamNimals())
                    printl(pamNimal.getName());

                printl(" ");
                printl("-".repeat(20));
                printl(" ");
            }
        }
    }
}
