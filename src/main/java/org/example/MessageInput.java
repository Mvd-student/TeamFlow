package org.example;
import java.time.LocalDate;
import java.util.Scanner;




//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MessageInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Userstory processor = new Message(1, "", LocalDate.now(), 1001, 2001,
                3001, "Story Titel", "Story beschrijving", 4001, 5001
        );

        System.out.println("Typ berichten (typ 'stop' om te stoppen):");

        System.out.println("Typ berichten ('stop' om te stoppen, 'toon' om alles te tonen):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("stop")) {
                break;
            } else if (input.equalsIgnoreCase("toon")) {
                System.out.println("\n📋 Alle berichten:");
                for (String msg : processor.getMessages()) {
                    System.out.println("- " + msg);
                }
                System.out.println();
            } else {
                processor.testProcess(input);
            }
        }

        scanner.close();
    }
}
