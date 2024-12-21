import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TileSpace extends JPanel {
    private Tile tile;
    boolean taken=false;
    private String multiplier="_";
    public TileSpace() {
        setPreferredSize(new Dimension(40, 40));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //handleClick();
            }
        });
    }
    public Tile getTile() {
        return tile;
    }
    public void setTile(Tile tile) {
        this.tile = tile;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (multiplier != null) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.drawString(multiplier, 10, 20);
        }
    }

}
