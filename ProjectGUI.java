
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProjectGUI extends JFrame {
    /*
     * private static void createWindow() { JFrame frame = new
     * JFrame("Java to C# Translator");
     * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); createGUI(frame);
     * frame.setSize(700, 700); frame.setLocationRelativeTo(null);
     * frame.setVisible(true); }
     * 
     * private static void createGUI(final JFrame frame) { JPanel mainPanel = new
     * JPanel(); LayoutManager layout = new FlowLayout();
     * mainPanel.setLayout(layout);
     * 
     * JLabel originalTitle = new JLabel("Java"); JLabel translatedFile = new
     * JLabel("C#"); JButton importButton = new JButton("Import Java File"); JButton
     * saveButton = new JButton("Save C# File"); JButton translateButton = new
     * JButton("Translate"); JTextArea originalFile = new
     * JTextArea("Enter error-free Java code here",35,25); JTextArea convertedFile =
     * new JTextArea(35,25);
     * 
     * mainPanel.add(originalTitle); mainPanel.add(originalFile);
     * mainPanel.add(translatedFile); mainPanel.add(convertedFile);
     * mainPanel.add(importButton); mainPanel.add(saveButton);
     * mainPanel.add(translateButton);
     * frame.getContentPane().add(mainPanel,BorderLayout.CENTER);
     * 
     * importButton.addActionListener(new ActionListener() {
     * 
     * @Override public void actionPerformed(ActionEvent e) { JFileChooser
     * fileChooser = new JFileChooser(); int option =
     * fileChooser.showOpenDialog(frame); if (option == JFileChooser.APPROVE_OPTION)
     * { File file = fileChooser.getSelectedFile();
     * 
     * } else {
     * 
     * } } }); }
     */

    public static void main(String[] args) {
        // createWindow();

        Lexer lexer = new Lexer();
        lexer.readFile();
        try {
            lexer.createTokens();
        } catch (IOException E) {

        }

    }
}
