import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScorePanel extends JPanel {
    private final List<Player> players;
    private Player currentPlayer;

    public ScorePanel(List<Player> players) {
        this.players = players;
        setPreferredSize(new Dimension(200, 750));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10, y = 20;
        for (Player p : players) {
            if (p == currentPlayer) {
                g.setColor(Color.BLUE);
                g.drawString(p.getName() + " - " + p.getScore(), x, y);
                y += 40;
                g.setColor(Color.BLACK);
            } else {
                g.drawString(p.getName() + " - " + p.getScore(), x, y);
                y += 40;
            }
        }
    }

    public void setCurrentPlayer(Player p) {
        currentPlayer = p;
    }
}
