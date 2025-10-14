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
        Address address = new Address(neighborhood, city, state);
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
            printf("[%d] %s\n",  i + 1, PAMGYMS.get(i).getName());
    }

    public UUID showPamGyms(int pg) {
        int i = 1;
        String choose;

        for (PamGym pamGym : PAMGYMS) {
            if (!(pamGym.getId().equals(PAMGYMS.get(pg).getId())))
                printf("[%d] %s\n", i++, pamGym.getName());
            else {
                printf("[NÃO ESCOLHÍVEL] %s\n", pamGym.getName());
                i++;
            }
        }
        while (true) {
            try {
                choose = ask("Escolha uma opção: ");
                if (Integer.parseInt(choose) != pg) return PAMGYMS.get(Integer.parseInt(choose) - 1).getId();
                else printl("Não pode escolher a mesma PamGym");
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
            for (PamNimal p : pamGym.getPamNimals())
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
                """, address.getNeighborhood(), address.getCity(), address.getState());
                printl("Nomes dos PamNimals:");
                for (PamNimal p : pamGym.getPamNimals())
                    printl(p.getName());

                printl(" ");
                printl("-".repeat(20));
                printl(" ");
            }
        }
    }

    public boolean updatePamGyms(int editPamGym) {
        try {
            while (true) {
                PamGym pamGym = PAMGYMS.get(editPamGym);
                String choose;

                printl("""
                        [1] Nome
                        [2] Bairro
                        [3] Cidade
                        [4] Estado
                        [5] Sair
                        """);

                choose = ask("=> Qual atributo deseja editar? ");

                switch (choose) {
                    case "1" -> {
                        printf("Nome atual da PamGym: %s\n", pamGym.getName());
                        String newName = inputUser("Digite o novo nome");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newName != null) {
                                pamGym.setName(newName);
                                saveInfo();
                                printl("Nome atualizado com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "2" -> {
                        printf("Bairro atual da PamGym: %s\n", pamGym.getAddress().getNeighborhood());
                        String newNeighbourHood = inputUser("Digite o novo Bairro");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newNeighbourHood != null) {
                                pamGym.getAddress().setNeighborhood(newNeighbourHood);
                                saveInfo();
                                printl("Bairro atualizado com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "3" -> {
                        printf("Cidade atual da PamGym: %s\n", pamGym.getAddress().getCity());
                        String newCity = inputUser("Digite a nova cidade");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newCity != null) {
                                pamGym.getAddress().setCity(newCity);
                                saveInfo();
                                printl("Cidade atualizada com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "4" -> {
                        printf("Estado atual da Pamgym: %s\n", pamGym.getAddress().getState());
                        String newState = inputUser("Digite o novo Estado");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newState != null) {
                                pamGym.getAddress().setState(newState);
                                saveInfo();
                                printl("Estado atualizado com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "5" -> {
                        return false;
                    }

                    default -> printl("Digite uma opção válida!");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            printl("Digite um número dentro da opções!");
            return true;
        }
    }
}
