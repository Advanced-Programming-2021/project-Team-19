package view;

import java.util.Scanner;

public class GetInput {

    static Scanner scanner = new Scanner(System.in);

    public static String getString() {
        return scanner.nextLine().replaceAll("\\s+", " ").trim();
    }


}
