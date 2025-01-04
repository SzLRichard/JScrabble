import javax.swing.*;
import java.awt.*;

public class TileButton extends JButton {
    private final Tile tile;

    public TileButton(Tile tile) {
        super(String.valueOf(tile.getLetter()));
        this.tile = tile;
        setFont(new Font("Arial", Font.BOLD, 20));
        setHorizontalTextPosition(SwingConstants.CENTER);
        setVerticalTextPosition(SwingConstants.CENTER);
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.setColor(Color.BLACK);
        String score = String.valueOf(tile.getPoints());
        int x = getWidth() - g.getFontMetrics().stringWidth(score) - 5;
        int y = getHeight() - 5;
        g.drawString(score, x, y);
    }
}