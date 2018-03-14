package interactionscalculator;

import java.io.IOException;

/**
 *
 * @author JShepherd
 */
public class InteractionsCalculatorDriver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InteractionsCalculator calculator = new InteractionsCalculator();

        String prompt = "Enter the filename.";
        calculator.setFilename(calculator.getUserInput(prompt));
        calculator.readFile();
        calculator.composeStats();
        calculator.calculateInteractions();
        String totals = calculator.writeTotals();
        System.out.println(totals);

        try {
            calculator.writeFile(totals);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Hit Enter To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }
}
