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
            printf("[%d] %s",  i + 1, PAMMASTERS.get(i).getName());
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
            PamMaster newPamMasterObj = PAMMASTERS.stream()
                    .filter(p -> p.getId().equals(newPamMaster))
                    .findFirst()
                    .orElse(null);

            for (PamNimal p : pamMaster.getPamNimals()) {
                if (p != null && newPamMasterObj != null) {
                    p.setPamMaster(newPamMaster);
                    newPamMasterObj.addPamNimal(p);
                }
            }

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
                """, address.getNeighborhood(), address.getCity(), address.getState());
                printl("Nomes dos PamNimals:");
                for (PamNimal pamNimal : p.getPamNimals())
                    printl(pamNimal.getName());

                printl(" ");
                printl("-".repeat(20));
                printl(" ");
            }
        }
    }

    public boolean updatePamMaster(int choosePamMaster) {
        try {
            while (true) {
                PamMaster pamMaster = PAMMASTERS.get(choosePamMaster);
                String choose;

                printl("""
                        [1] Nome
                        [2] Bairro
                        [3] Cidade
                        [4] Estado
                        [5] Telefone
                        [6] email
                        [7] Sair
                        """);

                choose = ask("=> Qual atributo deseja editar? ");

                switch (choose) {
                    case "1" -> {
                        printf("Nome atual do(a) PamMaster: %s\n", pamMaster.getName());
                        String newName = inputUser("Digite o novo nome");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newName != null) {
                                pamMaster.setName(newName);
                                saveInfo();
                                printl("Nome atualizado com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "2" -> {
                        printf("Bairro atual do(a) PamMaster: %s", pamMaster.getAddress().getNeighborhood());
                        String newNeighbourHood = inputUser("Digite o novo Bairro");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newNeighbourHood != null) {
                                pamMaster.getAddress().setNeighborhood(newNeighbourHood);
                                saveInfo();
                                printl("Bairro atualizado com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "3" -> {
                        printf("Cidade atual do(a) PamMaster: %s", pamMaster.getAddress().getCity());
                        String newCity = inputUser("Digite a nova cidade");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newCity != null) {
                                pamMaster.getAddress().setCity(newCity);
                                saveInfo();
                                printl("Cidade atualizada com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "4" -> {
                        printf("Estado atual do(a) PamMaster: %s", pamMaster.getAddress().getState());
                        String newState = inputUser("Digite o novo Estado");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newState != null) {
                                pamMaster.getAddress() .setState(newState);
                                saveInfo();
                                printl("Estado atualizado com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "5" -> {
                        printf("Estado atual do(a) PamMaster: %s", pamMaster.getFormatedPhone());
                        String newPhone = createPhone();
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newPhone != null) {
                                pamMaster.setPhone(newPhone);
                                saveInfo();
                                printl("Telefone atualizado com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "6" -> {
                        printf("Email atual do(a) PamMaster: %s", pamMaster.getEmail());
                        String newEmail = inputUser("Digite o novo email");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newEmail != null) {
                                pamMaster.setEmail(newEmail);
                                saveInfo();
                                printl("Email atualizado com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "7" -> { return false; }

                    default -> printl("Digite uma opção válida!");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            printl("Digite um número dentro da opções!");
            return true;
        }
    }
}
