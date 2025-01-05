import javax.swing.*;
import java.awt.*;

public class HelpMenu extends JPanel {

    public HelpMenu(Application app) {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 600));
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("How to Play Scrabble", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea helpText = new JTextArea();
        helpText.setText("""
                Scrabble Rules:
                
                1. Objective:
                   - Form valid words using the letter tiles on the bottom.
                   - Earn points based on the letters used and board multipliers.
                
                2. Gameplay:
                   - Each player starts with 7 random tiles.
                   - Place tiles on the board to form words horizontally or vertically.
                        * Click on a tile you want to place, then click somewhere on the board to place it.
                        * Click on a cyan tile on the board to take it back to your hand.
                        * Click finalize if you've finished writing out your word.
                   - The first word must go over the middle tile.
                   - Words must connect to existing words on the board.
                   - You can swap 0 or more tiles instead of playing.
                        * Click the swap button, then click on each tile you want to replace.
                
                3. Scoring:
                   - Each letter has a point value.
                   - Use special board spaces for multipliers:
                        * DL: Double Letter Score
                        * TL: Triple Letter Score
                        * DW: Double Word Score
                        * TW: Triple Word Score
                
                4. Endgame:
                   - The game ends one full cycle after the tile bag gets empty.
                   - The player with the highest score wins!
                
                Tips:
                   - Plan your moves to maximize points.
                   - Take advantage of the multipliers.
                   - Keep an eye on your time in the top left corner.
                """);
        helpText.setEditable(false);
        helpText.setLineWrap(true);

        JScrollPane scrollPane = new JScrollPane(helpText);
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(_ -> app.mainMenu());
        add(backButton, BorderLayout.SOUTH);
    }
}
