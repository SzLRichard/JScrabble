import javax.swing.*;
import java.awt.*;

public class WinPanel extends JPanel {
    public WinPanel(Application app, Player winner) {
        setLayout(new BorderLayout());

        JPanel winnerDetailsPanel = new JPanel();
        winnerDetailsPanel.setLayout(new FlowLayout());

        JLabel winnerNameLabel = new JLabel("Winner: " + winner.getName());
        winnerNameLabel.setSize(40,15);
        JLabel winnerPointsLabel = new JLabel("Points: " + winner.getScore());
        winnerPointsLabel.setSize(40,15);

        winnerDetailsPanel.add(winnerNameLabel);
        winnerDetailsPanel.add(winnerPointsLabel);

        WinBanner banner = new WinBanner();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(_ -> app.mainMenu());

        add(banner, BorderLayout.CENTER);
        add(winnerDetailsPanel, BorderLayout.NORTH);
        add(backButton, BorderLayout.SOUTH);
    }
}
