package org.example.commons;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Scanner;

public class IOFunctions {
    private static final Scanner scanner = new Scanner(System.in);

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


    public static @NotNull String ask(String prompt) {
        try {
            print(prompt);
            return scanner.nextLine().trim();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void print(String prompt) {
        System.out.print(prompt);
    }

    public static void printl(String prompt) {
        System.out.println(prompt);
    }

    public static void printf(String formato, Object... args) {
        System.out.printf(formato, args);
    }
}
