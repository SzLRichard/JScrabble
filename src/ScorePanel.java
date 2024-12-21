import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScorePanel extends JPanel {
    List<Player> players;
    public ScorePanel(List<Player> players) {
        this.players = players;
        setPreferredSize(new Dimension(200, 750));
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x=10,y=20;
        for (Player p : players) {
            g.drawString(p.getName() + " - " + p.getScore(),x,y);
            y+=40;
        }
    }
}
