package org.example.view;

import java.util.Scanner;

public class PrincipalMenu {
    public static void PrincipalMenu(){
        System.out.println(" ---Bem-vindo ao PamyDex---");
        while(true){
            System.out.println("""
                    =>Escolha uma das opções:
                    [1] Cadastrar
                    [2] Listar
                    [3] Remover
                    [4] Buscar
                    [5] Atualiazar
                    [0] Sair""");

            String input = new Scanner(System.in).nextLine().trim();

            switch (input){
                case "1";
                case "2";
                case "3";
                case "4";
                case "5";
                case "0" -> { return; }
                default -> System.out.println("Entrada Inválida!");
            }
        }
    }
}
