import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;

public class DictionaryManager extends JPanel {

    private Application app;
    private JButton backButton;
    private JTextField wordField;
    private JButton addButton;

    public DictionaryManager(Application app) {
        this.app = app;
        backButton = new JButton("Back");
        backButton.addActionListener(e -> app.mainMenu());
        add(backButton);
        wordField = new JTextField(50);
        addButton = new JButton("Add words");
        addButton.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        for (String word : wordField.getText().toUpperCase().split(" ")) {
                            try (FileWriter writer = new FileWriter("resources/wordlist.txt", true)) {
                                writer.write(System.lineSeparator());
                                writer.write(word);
                            } catch (IOException exc) {
                                System.err.println("An error occurred while appending to the file:" + exc.getMessage());
                            }
                        }
                    }
                }
        );
        add(wordField);
        add(addButton);
    }
}
