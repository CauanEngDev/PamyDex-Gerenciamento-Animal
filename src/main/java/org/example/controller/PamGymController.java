package org.example.controller;

import org.example.model.Address;
import org.example.model.PamGym;
import org.example.model.PamNimal;

import static org.example.database.Pamydex.*;

import static org.example.commons.Function.*;
import static org.example.commons.IOFunctions.*;

import java.util.InputMismatchException;
import java.util.Scanner;
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
            System.out.printf("[%d] %s",  i + 1, PAMGYMS.get(i).getName());
    }

    public UUID showPamGyms(int pg) {
        int i = 1;
        String choose;

        for (PamGym pamGym : PAMGYMS) {
            if (!(pamGym.getId().equals(PAMGYMS.get(pg).getId())))
                System.out.printf("[%d] %s", i++, pamGym.getName());
            else {
                System.out.printf("[NÃO ESCOLHÍVEL] %s", pamGym.getName());
                i++;
            }
        }
        while (true) {
            try {
                choose = ask("Escolha uma opção: ");
                if (Integer.parseInt(choose) != pg) return PAMGYMS.get(Integer.parseInt(choose) - 1).getId();
                else printl("Não pode escolher a mesma PamGym");
            } catch (InputMismatchException ex) {
                printl("Digite uma opção válida!");
            } catch (IndexOutOfBoundsException ex) {
                printl("Digite uma opção válida!");
            } catch (NumberFormatException ex) {
                printl("Digite um numero!");
            }
        }
    }

    public boolean removePamGym(int choosePamGym, UUID newPamGym) {
        try {
            PamGym pamGym = PAMGYMS.get(choosePamGym);
            for (PamNimal p : pamGym.getPamNimalList())
                p.setPamGym(newPamGym);
            PAMGYMS.remove(pamGym);
            saveInfo();
            return false;
        } catch (IndexOutOfBoundsException ex) {
            printl("Digite uma opção válida!");
        }
        return true;
    }

    public void listPamGyms() {
        if (PAMGYMS.isEmpty()) {
            printl("Não há PamGyms cadastrados!");
        } else {
            for (PamGym pamGym : PAMGYMS) {
                printf("""
                        Id -> %s
                        "Nome -> %s
                        """, pamGym.getId(), pamGym.getName());
                printl("Endereço:");
                Address address = pamGym.getAddress();
                printf("""
                Bairro -> %s
                Cidade -> %s
                Estado -> %s
                """, address.neighborhood(), address.city(), address.state());
                printl("Nomes dos PamNimals:");
                for (PamNimal p : pamGym.getPamNimalList())
                    printl(p.getName());

                printl(" ");
                printl("-".repeat(20));
                printl(" ");
            }
        }
    }
}
