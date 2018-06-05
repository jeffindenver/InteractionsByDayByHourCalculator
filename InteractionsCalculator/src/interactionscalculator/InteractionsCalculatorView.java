package interactionscalculator;

import java.util.Scanner;

/**
 *
 * @author JShepherd
 */
public class InteractionsCalculatorView {

    String getUserInput(String prompt) {
        Scanner sc = new Scanner(System.in);
        String input = "";
        System.out.println(prompt);
        if (sc.hasNextLine()) {
            input = sc.nextLine();
        }
        return input;
    }

    void printMessage(String message) {
        System.out.println(message);
    }

    void printError(String error) {
        System.err.println(error);
    }
}
