package org.example.function;

import java.util.Scanner;

public class IOFunctions {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputUser(String prompt) {
        while (true) {
            print(prompt + "(Digite 'p' para pular");
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

    public static String ask(String prompt) {
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
}
