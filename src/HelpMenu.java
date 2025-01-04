import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HelpMenu extends JPanel {

    private Application app;
    private JButton backButton;
    public HelpMenu(Application app) {
        this.app = app;

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
               - Form valid words using letter tiles on the game board.
               - Earn points based on the letters used and board multipliers.
            
            2. Gameplay:
               - Each player starts with 7 random tiles.
               - Place tiles on the board to form words horizontally or vertically.
               - Words must connect to existing words on the board.
               - You can swap tiles or skip your turn instead of playing.
            
            3. Scoring:
               - Each letter has a point value.
               - Use special board spaces for multipliers:
                   * DL: Double Letter Score
                   * TL: Triple Letter Score
                   * DW: Double Word Score
                   * TW: Triple Word Score
            
            4. Endgame:
               - The game ends when there are no more tiles in the tile bag.
               - The player with the highest score wins!
            
            Tips:
               - Plan your moves to maximize points.
               - Use high-value letters on premium spaces.
            """);
        helpText.setFont(new Font("Arial", Font.PLAIN, 14));
        helpText.setEditable(false);
        helpText.setWrapStyleWord(true);
        helpText.setLineWrap(true);
        helpText.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(helpText);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(scrollPane, BorderLayout.CENTER);

        backButton=new JButton("Back");
        backButton.addActionListener(e->app.mainMenu());
        add(backButton,BorderLayout.SOUTH);
    }
}
