package interactionscalculator;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author JShepherd
 */
public class InteractionsCalculatorController {

    private final InteractionsCalculator calculator;
    private final CalculatorGUI view;

    public InteractionsCalculatorController(InteractionsCalculator calculator,
            CalculatorGUI view) {

        this.calculator = calculator;
        this.view = view;
    }

    public void start() {

        startListeners();

    }

    private void startListeners() {

        view.getTextArea().setDropTarget(new DropTarget() {
            private static final long serialVersionUID = 1L;

            @Override
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);

                    @SuppressWarnings("unchecked")
                    List<File> droppedFiles
                            = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

                    for (File file : droppedFiles) {
                        view.getTextArea().setText(file.toString());
                        processFile(file.toString());
                    }
                } catch (UnsupportedFlavorException ufe) {
                    view.printError(ufe.getMessage());
                } catch (IOException ioe) {
                    view.printError(ioe.getMessage());
                }
            }

            private void processFile(String filename) {

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
        });
    }
}
