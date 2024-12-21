import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;

class MenuPanel extends JPanel {
    private JTextField player1Field;
    private JTextField player2Field;
    private JTextField player3Field;
    private JTextField player4Field;

    public MenuPanel(Application game) {
        setLayout(new GridLayout(6, 1, 10, 10));

        add(new JLabel("Enter Player Names:", SwingConstants.CENTER));

        player1Field = new JTextField("Player 1");
        player2Field = new JTextField("Player 2");
        player3Field = new JTextField("Player 3");
        player4Field = new JTextField("Player 4");

        add(player1Field);
        add(player2Field);
        add(player3Field);
        add(player4Field);

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            List<String> names = new ArrayList<>();
            names.add(player1Field.getText());
            names.add(player2Field.getText());
            if (!player3Field.getText().isEmpty()) {
                names.add(player3Field.getText());
            }
            if (!player4Field.getText().isEmpty()) {
                names.add(player4Field.getText());
            }
            try {
                game.setPlayerNames(names);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            game.switchToGame();
        });

        add(startButton);
    }
}
