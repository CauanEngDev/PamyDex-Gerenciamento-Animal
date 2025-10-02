package org.example.controller;

import static org.example.database.Pamydex.*;

import static org.example.commons.Function.*;
import static org.example.commons.IOFunctions.*;

import org.example.model.PamNimal;

import java.util.UUID;

public class PamNimalController {
    private PamMasterController masterControl = new PamMasterController();
    private PamGymController gymControl = new PamGymController();

    public void registerPamNimal(String name, String specie,String breed, int age,
                                 String sex, String currentStatus) {

        UUID pamGymId = choosePamGym();
        UUID pamMasterId = choosePamMaster();
        UUID id = uniqueId(PAMNIMALS);
        PamNimal pamNimal = new PamNimal(id, name, specie, breed, age, sex, currentStatus, pamGymId, pamMasterId);

        PAMNIMALS.add(pamNimal);
        saveInfo();
    }

    public boolean removePamNimal(int choose) {
        try{
            PamNimal pamNimal = PAMNIMALS.get(choose-1);
            masterControl.removePamNimal(pamNimal, pamNimal.getPamMaster());
            gymControl.removePamNimal(pamNimal, pamNimal.getPamGym());
            PAMNIMALS.remove(pamNimal);
            saveInfo();
            return false;
        } catch (IndexOutOfBoundsException e) {
            printl("Digite uma opção válida!");
        }
        return true;
    }

    public UUID choosePamGym(){
        UUID pamGymId;

        do {
            try {
                gymControl.showPamGyms();
                int choosePamGym = Integer.parseInt(ask("Qual PamGym você deseja? ")) - 1;

                pamGymId = PAMGYMS.get(choosePamGym).getId();
                return pamGymId;
            } catch (NumberFormatException ex) {
                printl("Digite apenas números!!!");
            } catch (IndexOutOfBoundsException ex) {
                printl("Digite um número dentro das opções!!!");
            }
        } while (true);
    }

    public static UUID choosePamMaster(){
        UUID pamMasterId;

        do {
            String choose = ask("""
                    Deseja escolher um PamMaster? 
                    [s] sim
                    [n] não
                    """);

            switch (choose) {
                case "s":
                    do {
                        try {
                            for (int i = 0; i < PAMMASTERS.size(); i++) {
                                System.out.printf("[%d] %s\n", i+1, PAMMASTERS.get(i).getName());
                            }
                            int choosePamMaster = Integer.parseInt(ask("Qual PamMaster você deseja? ")) - 1;

                            pamMasterId = PAMMASTERS.get(choosePamMaster).getId();
                            return pamMasterId;
                        } catch (NumberFormatException ex) {
                            printl("Digite apenas números!!!");
                        } catch (IndexOutOfBoundsException ex) {
                            printl("Digite um número dentro das opções!!!");
                        }
                    } while (true);
                case "n":
                    pamMasterId = GHOSTPAMMASTER.getId();
                    return pamMasterId;
                default: printl("Digite apenas 's' ou 'n'");
            }
        } while (true);
    }

    public void showPamNimals() {
        for (int i = 0; i < PAMNIMALS.size(); i++)
            System.out.printf("[%d] %s",  i + 1, PAMNIMALS.get(i).getName());
    }
}
