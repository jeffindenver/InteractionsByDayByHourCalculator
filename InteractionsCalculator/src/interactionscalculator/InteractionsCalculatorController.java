package interactionscalculator;

import java.io.IOException;

/**
 *
 * @author JShepherd
 */
public class InteractionsCalculatorController {

    private final InteractionsCalculator calculator;
    private final InteractionsCalculatorView view;

    public InteractionsCalculatorController(InteractionsCalculator calculator,
            InteractionsCalculatorView view) {
        
        this.calculator = calculator;
        this.view = view;
    }

    public void start() {
        String prompt = "Enter the filename.";
        String filename = view.getUserInput(prompt);
        view.printMessage("Filename set to " + filename);
        
        try {
            calculator.readFile(filename); 
        } catch (IOException e) {
            view.printError(e.getMessage());
        }

        calculator.composeStats();
        calculator.calculateInteractions();
        String totals = calculator.writeTotals();
        view.printMessage(totals);

        try {
            calculator.writeFile(totals);
        } catch (IOException e) {
            view.printError(e.getMessage());
        }
    }
}
