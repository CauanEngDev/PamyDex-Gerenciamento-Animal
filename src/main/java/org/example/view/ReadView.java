package org.example.view;

import static org.example.controller.GeneralController.*;

import static org.example.commons.IOFunctions.*;

public class ReadView {

    /**
     * Função de menu de busca
     */
    public static void readMenu() {
        while (true) {
            printl("""
                    => MENU DE BUSCA <=
                    [1] PamGym
                    [2] PamNimal
                    [3] PamMaster
                    [4] Sair
                    """);

            String choose = ask("Escolha um tipo de busca: ");
            switch (choose) {
                case "1" -> readPamGyms();
                case "2" -> readPamNimals();
                case "3" -> readPamMaster();
                case "4" -> {
                    printl("Voltando...");
                    return;
                }
                default -> { printl("Digite uma opção válida!"); }
            }
        }
    }

    /**
     * Função de busca de PamGyms
     */
    public static void readPamGyms() {
        printl("Informações das PamGyms:");
        printl(" ");
        gymController.listPamGyms();
    }

    /**
     * Função de busca de PamMasters
     */
    public static void readPamMaster() {
        printl("Informações do(a)s PamMasters:");
        printl(" ");
        masterController.listPamMaster();
    }

    /**
     * Função de busca de PamNimals
     */
    public static void readPamNimals() {
        printl("Informações dos PamNimals:");
        printl(" ");
        animalController.listPamNimals();
    }
}
