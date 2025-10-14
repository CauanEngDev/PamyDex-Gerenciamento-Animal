package org.example.commons;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Scanner;

/**
 * Classe de funções de entrada/saída estáticas para faciltar e modularizar o código
 */
public class IOFunctions {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Classe que mostra uma mensagem na tela e retorna uma entrada do usuário
     * @param prompt Mensagem que quer que mostre
     * @return Entrada do usuário
     * @author Taylon TaylonLu
     */
    public static @Nullable String inputUser(String prompt) {
        while (true) {
            print(prompt + " (Digite 'p' para voltar) -> ");
            String input =  scanner.nextLine().trim();

            if (input.equals("p")) {
                return null;
            }

            if (input.isEmpty()) {
                print("Erro: Digite algo  na entrada!");
            } else {
                return input;
            }
        }
    }

    /**
     * Versão da inputUser para inteiros
     * @param prompt Mensagem que quer que mostre
     * @return Entrada do usuário
     * @author Taylon TaylonLu
     */
    public static @Nullable Integer inputUserInt(String prompt) {
        while (true) {
            String input;
            try {
                do {
                    print(prompt + " (Digite '0' para voltar) -> ");
                    input = scanner.nextLine().trim();
                } while (Integer.parseInt(input) < 0);

                if (input.equals("0")) {
                    return null;
                }

                if (input.isEmpty()) {
                    print("Erro: Digite algo  na entrada!");
                } else {
                    return Integer.parseInt(input);
                }
            } catch (NumberFormatException ex) {
                printl("Digite apenas números!");
            }
        }
    }

    /**
     * Função para fazer uma pergunta ao usuário
     * @param prompt Mesnagem que será mostrada
     * @return Entrada do usuário
     * @author Taylon TaylonLu
     */
    public static @NotNull String ask(String prompt) {
        try {
            print(prompt);
            return scanner.nextLine().trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Função para facilitar e deixar mais limpo o usar do print tornando-o estático
     * @param prompt Mensagem que será mostrada
     */
    public static void print(String prompt) {
        System.out.print(prompt);
    }

    /**
     * Função para facilitar e deixar mais limpo o usar do printl tornando-o estático
     * @param prompt Mensagem que será mostrada
     */
    public static void printl(String prompt) {
        System.out.println(prompt);
    }

    /**
     * Função para facilitar e deixar mais limpo o usar do printf tornando-o estático
     * @param formato Mensagem com formato que será impresso
     * @param args Argumentos que seram usados no printf
     */
    public static void printf(String formato, Object... args) {
        System.out.printf(formato, args);
    }
}
