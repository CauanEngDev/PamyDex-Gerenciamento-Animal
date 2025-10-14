package org.example.controller;

import static org.example.database.Pamydex.*;

import static org.example.commons.Function.*;
import static org.example.commons.IOFunctions.*;

import org.example.model.PamGym;
import org.example.model.PamMaster;
import org.example.model.PamNimal;

import java.util.UUID;


/**
 * Classe que controla toda a aba de PamNimals
 */
public class PamNimalController {
    private final PamMasterController masterControl = new PamMasterController();
    private final PamGymController gymControl = new PamGymController();

    /**
     * Função que cria um novo registro de PamNimal
     * @param name nome do PamNimal
     * @param specie Espécie do PamNimal
     * @param breed Raça do PamNimal
     * @param age Idade do PamNimal
     * @param sex Sexo do PamNimal
     * @param currentStatus Status atual do PamNimal
     * <p>
     * PamGym e PamMaster são escolhidos dentro da própria função
     */
    public void registerPamNimal(String name, String specie,String breed, int age,
                                 String sex, String currentStatus) {

        UUID pamGymId = choosePamGym();
        UUID pamMasterId = choosePamMaster();
        UUID id = uniqueId(PAMNIMALS);
        PamNimal pamNimal = new PamNimal(id, name, specie, breed, age, sex, currentStatus, pamGymId, pamMasterId);

        PAMMASTERS.stream()
                .filter(p -> p.getId().equals(pamMasterId))
                .findFirst()
                .ifPresent(pamMaster -> pamMaster.addPamNimal(pamNimal));

        PAMGYMS.stream()
                .filter(p -> p.getId().equals(pamGymId))
                .findFirst()
                .ifPresent(pamGym -> pamGym.addPamNimal(pamNimal));

        PAMNIMALS.add(pamNimal);
        saveInfo();
    }

    /**
     * Função para remover um PamNimal
     * @param choose Índice do PamNimal dentro da lista geral de PamNimals
     * @return false se conseguir remover o PamNimal e true se tentar acessar um índice fora da lista
     */
    public boolean removePamNimal(int choose) {
        try{
            PamNimal pamNimal = PAMNIMALS.get(choose);
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

    /**
     * Função para escolher um PamGym para o PamNimal
     * @return id do PamGym
     */
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

    /**
     * Função para escolher um(a) PamMaster para o PamNimal
     * @return id do(a) PamMaster
     */
    public UUID choosePamMaster(){
        UUID pamMasterId;

        do {
            try {
                masterControl.showPamMasters();
                int choosePamMaster = Integer.parseInt(ask("Qual PamMaster você deseja? ")) - 1;

                pamMasterId = PAMMASTERS.get(choosePamMaster).getId();
                return pamMasterId;
            } catch (NumberFormatException ex) {
                printl("Digite apenas números!!!");
            } catch (IndexOutOfBoundsException ex) {
                printl("Digite um número dentro das opções!!!");
            }
        } while (true);
    }

    /**
     * Função para mostrar nome de todos os PamNimals existentes
     */
    public void showPamNimals() {
        for (int i = 0; i < PAMNIMALS.size(); i++)
            printf("[%d] %s\n",  i + 1, PAMNIMALS.get(i).getName());
    }

    /**
     * Função para listar todos os PamNimals e suas informações
     */
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
                        .ifPresent(pamMaster -> printf("Nome do(a) PamMaster -> %s\n", pamMaster.getName()));

                PAMGYMS.stream()
                        .filter(p -> p.getId().equals(pamNimal.getPamGym()))
                        .findFirst()
                        .ifPresent(pamGym -> printf("Nome da PamGym -> %s\n", pamGym.getName()));

                printl("-".repeat(20));

            }
        }
    }

    /**
     * Função para atualizar informações dos PamNimals
     * @param editPamNimal Índice do PamNimal a ser editado
     * @return false se escolher a opção de sair e true se tentar acessar um índice fora da lista
     */
    public boolean updatePamNimals(Integer editPamNimal) {
        try {
            while (true) {
                PamNimal pamNimal = PAMNIMALS.get(editPamNimal);
                String choose;

                printl("""
                        [1] Nome
                        [2] Espécie
                        [3] Raça
                        [4] Sexo
                        [5] Status
                        [6] PamGym
                        [7] PamMaster
                        [8] Idade
                        [9] Sair
                        """);

                choose = ask("=> Qual atributo deseja editar? ");

                switch (choose) {
                    case "1" -> {
                        printf("Nome atual do PamNimal: %s\n", pamNimal.getName());
                        String newName = inputUser("=> Digite o novo nome");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newName != null) {
                                pamNimal.setName(newName);
                                saveInfo();
                                printl("Nome atualizado com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }

                    }
                    case "2" -> {
                        printf("Espécie atual do PamNimal: %s\n", pamNimal.getSpecie());
                        String newSpecie = inputUser("=> Digite a nova espécie do PamNimal");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newSpecie != null) {
                                pamNimal.setSpecie(newSpecie);
                                saveInfo();
                                printl("Espécie atualizada com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "3" -> {
                        printf("Raça atual do PamNimal: %s\n", pamNimal.getBreed());
                        String newBreed= inputUser("=> Digite a nova raça do PamNimal");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newBreed != null) {
                                pamNimal.setBreed(newBreed);
                                saveInfo();
                                printl("Raça atualizada com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "4" -> {
                        printf("Sexo atual do PamNimal: %s\n", pamNimal.getSex());
                        String newSex = inputUser("=> Digite o novo sexo do PamNimal");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newSex != null) {
                                pamNimal.setSex(newSex);
                                saveInfo();
                                printl("Sexo atualizada com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "5" -> {
                        printf("Status atual do PamNimal: %s\n", pamNimal.getStatus());
                        printl("""
                                [1] Em Observação
                                [2] Disponível para Adoção
                                [3] Em Tratamento
                                """);
                        String currentStatus = chooseCurrentStatus();
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            pamNimal.setStatus(currentStatus);
                            saveInfo();
                            printl("Status atualizada com sucesso!");
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "6" -> {
                        PamGym pamGymAtual = PAMGYMS.stream()
                                                    .filter(p -> p.getId().equals(pamNimal.getPamGym()))
                                                    .findFirst()
                                                    .orElse(null);

                        if (pamGymAtual != null) {
                            printf("PamGym atual do PamNimal: %s", pamGymAtual.getName());
                            gymControl.showPamGyms();
                            Integer newPamgymId = inputUserInt("=> Digite o novo PamGym do PamNimal");
                            String option = ask("=> Deseja realmente fazer a troca? ");
                            if (option.equalsIgnoreCase("s")) {
                                if (newPamgymId != null) {
                                    pamGymAtual.removePamNimal(pamNimal);
                                    PamGym newPamgym = PAMGYMS.get(newPamgymId);
                                    pamNimal.setPamGym(newPamgym.getId());
                                    newPamgym.addPamNimal(pamNimal);
                                    saveInfo();
                                    printl("PamGym atualizada com sucesso!");
                                }
                            } else {
                                printl("Troca não realizada! Retornando...");
                            }
                        }
                    }

                    case "7" -> {
                        PamMaster pamMaster = PAMMASTERS.stream()
                                                        .filter(p -> p.getId().equals(pamNimal.getPamMaster()))
                                                        .findFirst()
                                                        .orElse(null);

                        masterControl.showPamMasters();
                        Integer newPamMasterId = inputUserInt("=> Digite o novo PamMaster do PamNimal");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newPamMasterId != null && pamMaster != null) {
                                pamMaster.removePamNimal(pamNimal);
                                PamMaster newPamMaster = PAMMASTERS.get(newPamMasterId);
                                pamNimal.setPamMaster(newPamMaster.getId());
                                newPamMaster.addPamNimal(pamNimal);
                            }
                            saveInfo();
                            printl("PamMaster atualizado com sucesso!");
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }

                    case "8" -> {
                        printf("Idade atual do PamNimal: %s\n", pamNimal.getAge());
                        Integer newAge = inputUserInt("=> Digite a nova idade do PamNimal");
                        String option = ask("=> Deseja realmente fazer a troca? ");
                        if (option.equalsIgnoreCase("s")) {
                            if (newAge != null) {
                                pamNimal.setAge(newAge);
                                saveInfo();
                                printl("Idade atualizada com sucesso!");
                            }
                        } else {
                            printl("Troca não realizada! Retornando...");
                        }
                    }
                    case "9" -> { return false; }

                    default -> printl("Digite uma opção válida!");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            printl("Digite um número dentro da opções!");
            return true;
        }
    }

    /**
     * Função que imprime um relatório geral dos PamNimals
     */
    public void generalReport () {
        if (PAMNIMALS.isEmpty()) {
            printl("Nenhum PamNimal cadastrado!");
            return;
        }
        for (PamNimal pamNimal : PAMNIMALS) {
            printf("""
                    Nome: %s
                    Idade: %d
                    """, pamNimal.getName(), pamNimal.getAge());
            PAMMASTERS.stream()
                    .filter(p -> p.getId().equals(pamNimal.getPamMaster()))
                    .findFirst()
                    .ifPresent(pamMaster -> printf("Nome do(a) PamMaster: %s\n", pamMaster.getName()));

            PAMGYMS.stream()
                    .filter(p -> p.getId().equals(pamNimal.getPamGym()))
                    .findFirst()
                    .ifPresent(pamGym -> printf("Nome da PamGym: %s\n", pamGym.getName()));
            printl("-".repeat(20));
        }
    }

    /**
     * Função que imprime um relatório de PamNimals por PamMaster
     */
    public void pamNimalsForMasters () {
        if (PAMNIMALS.isEmpty()) {
            printl("Nenhum PamNimal cadastrado!");
            return;
        }
        for (PamMaster pamMaster : PAMMASTERS) {
            printl("PamNimals do PamMaster: " + pamMaster.getName());

            for  (PamNimal pamNimal : pamMaster.getPamNimals()) {
                printf("""
                    Nome: %s
                    Idade: %d
                    """, pamNimal.getName(), pamNimal.getAge());
                printl("-".repeat(20));
            }
        }
    }

    /**
     * Função que imprime um relatório de PamNimals por PamGym
     */
    public void pamNimalsForGyms() {
        if (PAMNIMALS.isEmpty()) {
            printl("Nenhum PamNimal cadastrado!");
            return;
        }
        for (PamGym pamGym : PAMGYMS) {
            printl("PamNimals do PamGym: " + pamGym.getName());
            for  (PamNimal pamNimal : pamGym.getPamNimals()) {
                printf("""
                    Nome: %s
                    Idade: %d
                    """, pamNimal.getName(), pamNimal.getAge());
                printl("-".repeat(20));
            }
        }
    }
}
