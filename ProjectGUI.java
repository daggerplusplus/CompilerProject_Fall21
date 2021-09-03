import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
//import java.awt.*;

public class ProjectGUI extends JFrame {
    private JPanel mainPanel;
    
    private JTextArea originalFile; private JLabel originalTitle; private JLabel
     translatedFile; private JButton importButton; private JTextArea
      convertedFile; private JButton saveButton; private JButton translateButton;
     

    public ProjectGUI(String title) {
        super(title);
        mainPanel = new javax.swing.JPanel();

        JButton b1 = new JButton("hey");
        mainPanel.add(b1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        setSize(700, 700);
    }

    public static void main(String[] args) {
        JFrame frame = new ProjectGUI("Java to C# Translator");
        frame.setVisible(true);

        System.out.println("Your mom");
        System.out.println("no, you");

    }
}
    