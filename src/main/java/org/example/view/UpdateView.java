package org.example.view;

import static org.example.commons.IOFunctions.*;

import static org.example.controller.GeneralController.*;

public class UpdateView {

    /**
     * Função de menu de atualização
     */
    public static void updateMenu() {
        while (true) {
            printl("""
                    => MENU DE Atualizção <=
                    [1] PamGym
                    [2] PamNimal
                    [3] PamMaster
                    [4] Sair
                    """);

            String choose = ask("Escolha um tipo de busca: ");
            switch (choose) {
                case "1" -> updatePamGyms();
                case "2" -> updatePamNimals();
                case "3" -> updatePamMaster();
                case "4" -> {
                    printl("Voltando...");
                    return;
                }
                default -> printl("Digite uma opção válida!");
            }
        }
    }

    public static void updatePamGyms() {
        boolean teste = true;
        gymController.showPamGyms();

        while (teste) {
            Integer choose;

            choose = inputUserInt("Escolha o PamGym a ser editado");
            if (choose == null) return;

            teste = gymController.updatePamGyms(choose - 1);
        }
    }

    public static void updatePamNimals() {
        boolean teste = true;
        animalController.showPamNimals();

        while (teste) {
            Integer choose;

            choose =  inputUserInt("Escolha o PamNimal a ser editado");
            if (choose == null) return;

            teste = animalController.updatePamNimals(choose - 1);
        }
    }

    public static void updatePamMaster() {
        boolean teste = true;
        masterController.showPamMasters();

        while (teste) {
            Integer choose;

            choose =  inputUserInt("Escolha o/a PamMaster a ser editado(a)");
            if (choose == null) return;

            teste = masterController.updatePamMaster(choose - 1);
        }
    }
}
