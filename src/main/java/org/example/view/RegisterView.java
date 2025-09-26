package org.example.view;

import org.example.database.Pamydex;

import static org.example.commons.IOFunctions.*;
import static org.example.commons.Function.*;

public class RegisterView {
    public static void registerMenu(){
        printl("""
                => MENU DE CADASTRO <=
                [1] PamGym
                [2] PamNimal
                [3] PamMaster
                [4] Sair
                """);

        String choose = ask("Escolha um tipo de cadastro:");
        switch (choose) {
            case "1" -> pamNimalCreate();
            case "2":
            case "3":
            case "4" -> { return; }
            default -> printl("Erro: escolha uma opção válida!");
        }
    }

    /**
     * asasasasas
     */
    public static void pamNimalCreate(){
        if (Pamydex.PAMGYMS.isEmpty()) {
            print("Cadastre um PamGym primeiro!!!");
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


    }
}
