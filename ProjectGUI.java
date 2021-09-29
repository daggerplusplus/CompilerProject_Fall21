import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;

public class ProjectGUI extends JFrame {
    static String path;
    ProjectGUI() {
    
    }
    static String getInputPath() {
      return path; 
    }

    static void setInputPath(String path2) {
      path = path2;
    }

   
     public static void createWindow() {
      JFrame frame = new JFrame("Java to C# Translator");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); createGUI(frame);
      frame.setSize(700, 700); frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      }
      
      private static void createGUI(final JFrame frame) { 
        JPanel mainPanel = new JPanel();
        LayoutManager layout = new FlowLayout();
        mainPanel.setLayout(layout);

        JLabel originalTitle = new JLabel("Java"); 
        JLabel translatedFile = new JLabel("C#");       

        JButton importButton = new JButton("Import Java File"); 
        JButton saveButton = new JButton("Save C# File"); 
        JButton translateButton = new JButton("Tokenize");       
        
        JTextArea originalFile = new JTextArea("Enter error-free Java code here",35,25); 
        originalFile.setLineWrap(true);
        JScrollPane scrollOriginal = new JScrollPane(originalFile);
        JTextArea convertedFile = new JTextArea(35,25);
        JScrollPane scrollConverted = new JScrollPane(convertedFile);
        convertedFile.setLineWrap(true);

        saveButton.setLayout(new FlowLayout(FlowLayout.LEFT));
        translateButton.setLayout(new FlowLayout(FlowLayout.CENTER));
        importButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
    
        frame.getContentPane().add(saveButton,BorderLayout.SOUTH);
        frame.getContentPane().add(translateButton,BorderLayout.SOUTH);
        
        mainPanel.add(originalTitle); 
        mainPanel.add(originalFile);
        mainPanel.add(scrollOriginal);
        mainPanel.add(scrollConverted);
        mainPanel.add(translatedFile); 
        mainPanel.add(convertedFile);
        mainPanel.add(importButton); 
        mainPanel.add(saveButton);
        mainPanel.add(translateButton);
        frame.getContentPane().add(mainPanel,BorderLayout.CENTER);



        frame.setSize(500,500);
        frame.setVisible(true);
      
        //Button to import Java file and load into text area
        importButton.addActionListener(new ActionListener() {      
          @Override
          public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();      
            BufferedReader reader = null;  
            int option = fileChooser.showOpenDialog(frame);
            
            if (option == JFileChooser.APPROVE_OPTION)
              { 
                File file = fileChooser.getSelectedFile();
                String fileName = file.getName();
                setInputPath(fileName);
                try {
                  FileReader fr = new FileReader(fileName);
                  reader = new BufferedReader(fr);
                  originalFile.setText("");
                  String line = reader.readLine();
                  while (line != null) {
                  originalFile.append(line + "\n");
                  line = reader.readLine();
                  } //end while
                } catch (IOException e2)
              {
                originalFile.setText("");
                originalFile.append("Error reading file");
              }
            
              } //end if
          };  
        });   //end ActionListener
        translateButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            LexerDraft lexer = new LexerDraft();
            lexer.readFile();
            try {
              lexer.createTokens();
              FileReader fr = new FileReader("output.txt");
              BufferedReader reader = new BufferedReader(fr);
              convertedFile.setText("");
              String line = reader.readLine();
              while (line != null) {
                convertedFile.append(line + "\n");
                line = reader.readLine();
              }            
              //convertedFile.append();  //append output
              
            } catch (IOException E) {
              originalFile.setText("");
              originalFile.append("Error translating file");
            }


            
          }

        });
    } //end createGUI
}
