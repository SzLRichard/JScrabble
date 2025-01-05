import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.IOException;

public class WordAddMenu extends JPanel {

    private final JTextField wordField;

    public WordAddMenu(Application app) {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("Write words to add to the dictionary");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);
        JPanel rest = new JPanel(new FlowLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(_ -> app.mainMenu());
        rest.add(backButton);
        wordField = new JTextField(50);
        JButton addButton = new JButton("Add words");
        addButton.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        for (String word : wordField.getText().toUpperCase().split(" ")) {
                            try (FileWriter writer = new FileWriter("resources/wordlist.txt", true)) {
                                writer.write(word);
                                writer.write(System.lineSeparator());
                            } catch (IOException exc) {
                                System.err.println("An error occurred while appending to the file:" + exc.getMessage());
                            }
                        }
                    }
                }
        );
        rest.add(wordField);
        rest.add(addButton);
        add(rest, BorderLayout.CENTER);
    }
}
