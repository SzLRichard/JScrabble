import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;

public class DictionaryManager extends JPanel {

    private final JTextField wordField;

    public DictionaryManager(Application app) {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(_ -> app.mainMenu());
        add(backButton);
        wordField = new JTextField(50);
        JButton addButton = new JButton("Add words");
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
