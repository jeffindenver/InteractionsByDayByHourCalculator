package interactionscalculator;

/**
 * Made primarily for the Sergeants monthly report, this program reads a CSV
 * file, filtering for lines that begin with a digit and are length of 3.
 * Each line contains date, time, and number of interactions in that order.
 * It sums the interactions by hour by day and writes a CSV file with a header.
 * Each column is a day of week starting at Monday. Each row is an hour starting
 * at 000.
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
