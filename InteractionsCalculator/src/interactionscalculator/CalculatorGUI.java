package interactionscalculator;

import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 *
 * @author JShepherd
 */
public class CalculatorGUI {
    
    private final JFrame frame;
    private final JTextArea textArea;

    public CalculatorGUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setBounds(40, 40, 400, 400);
		
        textArea = new JTextArea("Drop files below for processing.");
        JScrollPane scrollPane = new JScrollPane(textArea);
        Insets inset = new Insets(20, 20, 20, 20);
        textArea.setMargin(inset);
        textArea.setEditable(false);
     
        frame.add(scrollPane);
        frame.setVisible(true);        
    }

    void addListener(ActionListener dropListener, JTextArea textArea) {
        //@TODO this
    }

    public JTextArea getTextArea() {
        return textArea;
    }
    
    public void printError (String msg) {
       JOptionPane.showMessageDialog(frame, msg);
    }
    
    public void printMessage (String msg) {
        textArea.setText(msg);
    }
}
