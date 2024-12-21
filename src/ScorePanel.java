import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScorePanel extends JPanel {
    List<Player> players;
    public ScorePanel(List<Player> players) {
        this.players = players;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x=10,y=10;
        for (Player p : players) {
            g.drawString(p.getName(),x,y);
            y+=20;
        }
    }
}
