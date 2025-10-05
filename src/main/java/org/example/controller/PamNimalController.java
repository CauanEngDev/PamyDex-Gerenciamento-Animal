package org.example.controller;

import static org.example.database.Pamydex.*;

import static org.example.commons.Function.*;
import static org.example.commons.IOFunctions.*;

import org.example.database.Pamydex;
import org.example.model.PamMaster;
import org.example.model.PamNimal;

import java.util.UUID;

public class PamNimalController {
    private final PamMasterController masterControl = new PamMasterController();
    private final PamGymController gymControl = new PamGymController();

    public void registerPamNimal(String name, String specie,String breed, int age,
                                 String sex, String currentStatus) {

        UUID pamGymId = choosePamGym();
        UUID pamMasterId = choosePamMaster();
        UUID id = uniqueId(PAMNIMALS);
        PamNimal pamNimal = new PamNimal(id, name, specie, breed, age, sex, currentStatus, pamGymId, pamMasterId);

        PAMMASTERS.stream()
                .filter(p -> p.getId().equals(pamMasterId))
                .findFirst()
                .ifPresent(pamMaster -> {
                    pamMaster.addPamNimal(pamNimal);
                });

        PAMGYMS.stream()
                .filter(p -> p.getId().equals(pamGymId))
                .findFirst()
                .ifPresent(pamGym -> {
                    pamGym.addPamNimal(pamNimal);
                });

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


    public void listPamNimals() {
        if (PAMNIMALS.isEmpty()) {
            printl("Nenhum PamNimal cadastrado!");
        } else {
            for (PamNimal pamNimal : PAMNIMALS) {
                printf("""
                        Id -> %s
                        Nome -> %s
                        Espécie -> %s
                        Raça -> %s
                        Idade Aproximada -> %d
                        Sexo -> %s
                        Status do PamNimal -> %s
                        """,
                        pamNimal.getId(), pamNimal.getName(),
                        pamNimal.getSpecie(), pamNimal.getBreed(),
                        pamNimal.getAge(), pamNimal.getSex(),
                        pamNimal.getStatus());

                PAMMASTERS.stream()
                        .filter(p -> p.getId().equals(pamNimal.getPamMaster()))
                        .findFirst()
                        .ifPresent(pamMaster -> {
                            printf("Nome do(a) PamMaster: %s\n", pamMaster.getName());
                        });

                PAMGYMS.stream()
                        .filter(p -> p.getId().equals(pamNimal.getPamGym()))
                        .findFirst()
                        .ifPresent(pamGym -> {
                            printf("Nome da PamGym: %s\n", pamGym.getName());
                        });

                printl(" ");
                printl("-".repeat(20));
                printl(" ");
            }
        }
    }
}
