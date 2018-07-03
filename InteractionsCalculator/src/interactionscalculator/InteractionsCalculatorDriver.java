package interactionscalculator;

/**
 * Made primarily for the Sergeants monthly report, this program reads a CSV
 * file with no header and three items per line: date, time, interactions.
 * It sums the interactions by hour by day and writes a CSV file with a header.
 * @author JShepherd
 */
public class InteractionsCalculatorDriver {

    public static void main(String[] args) {
        InteractionsCalculatorView view = new InteractionsCalculatorView();
        InteractionsCalculator calculator = new InteractionsCalculator();
        InteractionsCalculatorController controller = new InteractionsCalculatorController(calculator, view);
        controller.start();

        System.out.println("Hit Enter To Continue...");
        new java.util.Scanner(System.in).nextLine();
    }
}
