package org.example.view;

import static org.example.controller.GeneralController.*;

import static org.example.commons.IOFunctions.*;

public class ReportView {
    /**
     * Função de menu de relatórios
     */
    public static void reportMenu() {
        printl("=> MENU DE RELATÓRIOS <=");

        while (true) {
            printl("""
                    [1] Geral
                    [2] Por PamMaster
                    [3] Por PamGym
                    [4] Sair
                    """);

            String choose = ask("=> Escolha uma das opções: ");

            switch (choose) {
                case "1" -> animalController.generalReport();
                case "2" -> animalController.pamNimalsForMasters();
                case "3" -> animalController.pamNimalsForGyms();
                case "4" -> {
                    printl("Retornando...");
                    return;
                }
                default -> printl("Digite uma opção válida!");
            }
        }
    }
}
