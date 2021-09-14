
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
  public class Main {


    public static void main(String[] args) {
        // createWindow();

        LexerDraft lexer = new LexerDraft();
        lexer.readFile();
        try {
            lexer.createTokens();
        } catch (IOException E) {

        }

    }
}
