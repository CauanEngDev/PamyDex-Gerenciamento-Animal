package org.example.view;

import static org.example.commons.IOFunctions.*;

public class PrincipalMenu {
    /**
     * Função de menu principal
     * Dependendo da escolha manda pra outro menu ou fecha o programa
     */
    public static void principalMenu(){
        printl("---Bem-vindo ao PamyDex---");
        while(true){
            printl("""
                    => Opções de Interação:
                    [1] Cadastrar
                    [2] Listar
                    [3] Remover
                    [4] Buscar
                    [5] Atualizar
                    [6] Crédito e Homenagem
                    [0] Sair
                    """);

            String choose = ask("Escolha uma das opções:");

            switch (choose){
                case "1" -> RegisterView.registerMenu();
                case "2" -> ReportView.reportMenu();
                case "3" -> RemoveView.removeMenu();
                case "4" -> ReadView.readMenu();
                case "5" -> UpdateView.updateMenu();
                case "6" -> printl("""
                        Este código foi criado pelo estudante Cauan dos Reis Almeida como trabalho
                        da matéria MI de programação.
                        O projeto leva como inspiração e homenagem a professora Pâmela, a maior
                        amante de animais da universidade.
                        """);
                case "0" -> {
                    printl("Fechando programa...");
                    return;
                }
                default -> printl("Entrada Inválida!");
            }
        }
    }
}
