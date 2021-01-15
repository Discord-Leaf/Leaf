package uk.toadl3ss.Leaf.Utillites.Console;

import java.util.Scanner;

public class ConsoleInput {
    public ConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String input = scanner.nextLine();
            ConsoleHandler.ConsoleHandler(input);
        }
    }
}