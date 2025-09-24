package org.example.view;

import java.util.Scanner;
import static org.example.function.IOFunctions.*;

public class PrincipalMenu {
    public static void principalMenu(){
        printl("---Bem-vindo ao PamyDex---");
        while(true){
            printl("""
                    => Opções de Interação:
                    [1] Cadastrar
                    [2] Listar
                    [3] Remover
                    [4] Buscar
                    [5] Atualiazar
                    [0] Sair
                    """);

            String choose = ask("Escolha uma das opções:");

            switch (choose){
                case "1" -> RegisterView.registerMenu();
                case "2";
                case "3";
                case "4";
                case "5";
                case "0" -> { return; }
                default -> printl("Entrada Inválida!");
            }
        }
    }
}
