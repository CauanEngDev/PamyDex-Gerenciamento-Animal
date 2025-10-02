package org.example.view;

import java.util.UUID;

import static org.example.controller.GeneralController.*;

import static org.example.commons.IOFunctions.*;

public class RemoveView {

    public static void removeMenu() {
        printl("""
                => MENU DE REMOÇÃO <=
                [1] PamGym
                [2] PamNimal
                [3] PamMaster
                [4] Sair
                """);

        String choose = ask("Escolha um tipo de cadastro: ");
        switch (choose) {
            case "1" -> pamGymRemove();
            case "2" -> pamNimalRemove();
//            case "3" -> pamMasterRemove();
            case "4" -> {}
            default -> printl("Erro: escolha uma opção válida!");
        }
    }

    public static void pamGymRemove() {
        gymController.showPamGyms();
        Integer choose;
        UUID choose2;
        boolean fazalgo = true;

        while (fazalgo) {
            choose = inputUserInt("Escolha a PamGym para ser excluída: ");
            if (choose == null) return;
            else {
                choose2 = gymController.showPamGyms(choose);
                if (choose2 == null) return;
                else fazalgo = gymController.removePamGym(choose-1, choose2);
            }
        }
    }

    public static void pamNimalRemove() {
        animalController.showPamNimals();
        Integer choose;
        boolean fazalgo = true;

        while (fazalgo) {
            choose = inputUserInt("Escolha o PamNimal para ser excluído: ");
            if (choose == null) return;
            else  fazalgo = animalController.removePamNimal(choose-1);
        }
    }

    public static void pamMasterRemove() {
        masterController.showPamMasters();
        Integer choose;
        boolean fazalgo = true;
        while (fazalgo) {
            choose = inputUserInt("Escolha o PamMaster para ser excluído: ");
            if (choose == null) return;
            else  fazalgo = masterController.removePamMaster(choose-1);
        }
    }
}
