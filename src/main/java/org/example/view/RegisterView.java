package org.example.view;

import static org.example.controller.GeneralController.*;
import static org.example.database.Pamydex.*;

import static org.example.commons.IOFunctions.*;
import static org.example.commons.Function.*;

public class RegisterView {

    public static void registerMenu() {
        printl("""
                => MENU DE CADASTRO <=
                [1] PamGym
                [2] PamNimal
                [3] PamMaster
                [4] Sair
                """);

        String choose = ask("Escolha um tipo de cadastro:");
        switch (choose) {
            case "1" -> pamGymCreate();
            case "2" -> pamNimalCreate();
            case "3" -> pamMasterCreate();
            case "4" -> {}
            default -> printl("Erro: escolha uma opção válida!");
        }
    }

    public static void pamGymCreate() {
        printl("=> Cadastrando Pagym...");

        String name = inputUser("Nome do Pagym");
        if (name == null) return;

        String neighborhood = inputUser("Neighborhood do PamMaster");
        if (neighborhood == null) return;

        String city = inputUser("Cidade do PamMaster");
        if (city == null) return;

        String state = inputUser("Estado do PamMaster");
        if (state == null) return;

        gymController.registerPamGym(name, neighborhood, city, state);

        printl("=> PamGym cadastrado com sucesso! <=");
    }

    public static void pamNimalCreate() {
        if (PAMGYMS.isEmpty()) {
            print("=> Cadastre um PamGym primeiro!!!");
            return;
        }

        printl("=> Cadastrando PamNimal...");

        String name = inputUser("Nome do PamNimal");
        if (name == null) return;

        String specie = inputUser("Espécie do PamNimal");
        if (specie == null) return;

        String breed = inputUser("Raça do PamNimal");
        if (breed == null) return;

        Integer age = inputUserInt("Idade do PamNimal");
        if (age == null) return;

        String sex = inputUser("Sexo do PamNimal");
        if (sex == null) return;

        printl("""
                [1] Em Observação
                [2] Disponível para Adoção
                [3] Em Tratamento
                [0] Voltar
                """);
        String currentStatus = chooseCurrentStatus();
        if (currentStatus == null) return;

        animalController.registerPamNimal(name, specie, breed, age, sex, currentStatus);

        printl("=> PamNimal cadastrado com sucesso! <=");
    }

    public static void pamMasterCreate() {
        printl("=> Cadastrando PamMaster...");

        String name = inputUser("Nome do PamMaster");
        if (name == null) return;

        String phone = createPhone();
        if (phone == null) return;

        String email = inputUser("E-mail do PamMaster");
        if (email == null) return;

        String neighborhood = inputUser("Neighborhood do PamMaster");
        if (neighborhood == null) return;

        String city = inputUser("Cidade do PamMaster");
        if (city == null) return;

        String state = inputUser("Estado do PamMaster");
        if (state == null) return;

        masterController.registerPamMaster(name, phone, email, neighborhood, city, state);

        printl("=> PamMaster cadastrado com sucesso! <=");
    }
}
