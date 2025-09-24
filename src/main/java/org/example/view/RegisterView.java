package org.example.view;

import static org.example.function.IOFunctions.*;

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

    public static void pamNimalCreate(){
        printl("=> Cadastrando PamNimal...");

        String name = inputUser("Nome do PamNimal: ");
        if (name == null) return;

        String specie = inputUser("Specie do PamNimal: ");
        if (specie == null) return;


    }
}
