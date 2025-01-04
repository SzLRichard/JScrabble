import javax.swing.*;
import java.awt.*;

public class HelpMenu extends JPanel {

    public HelpMenu(Application app) {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 600));
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("How to Play Scrabble", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea helpText = new JTextArea();
        helpText.setText("""
            Scrabble Rules:
            
            1. Objective:
               - Form valid words using letter tiles on the bottom.
               - Earn points based on the letters used and board multipliers.
            
            2. Gameplay:
               - Each player starts with 7 random tiles.
               - Place tiles on the board to form words horizontally or vertically.
               - The first word must go over the middle tile.
               - Words must connect to existing words on the board.
               - You can swap 0 or more tiles instead of playing.
            
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
        helpText.setFont(new Font("Arial", Font.PLAIN, 14));
        helpText.setEditable(false);
        helpText.setWrapStyleWord(true);
        helpText.setLineWrap(true);
        helpText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(helpText);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(_ ->app.mainMenu());
        add(backButton,BorderLayout.SOUTH);
    }
}
