import javax.swing.*;

public class ProjectGUI extends JFrame{
    private JPanel mainPanel;
    private JTextArea originalFile;
    private JLabel originalTitle;
    private JLabel translatedFile;
    private JButton importButton;
    private JTextArea convertedFile;
    private JButton saveButton;
    private JButton translateButton;

    public ProjectGUI(String title){
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        setSize(700, 700);
    }

    public static void main(String[] args){
        JFrame frame = new ProjectGUI("Java to C# Translator");
        frame.setVisible(true);
    }
}
