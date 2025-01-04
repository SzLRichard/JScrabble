import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TileSpace extends JPanel {
    private Tile tile;
    private boolean taken = false;
    private String multiplier = "";
    private final HandPanel hand;
    private final int x;
    private final int y;

    private void handleClick() {
        if (!taken && tile != null) {
            hand.addTile(tile);
            tile = null;
            hand.repaint();
        } else if (!taken) {
            tile = hand.getSelectedTile();
            hand.removeSelectedTile();
            hand.repaint();
        }
        this.repaint();
    }

    public TileSpace(HandPanel hand,int x,int y) {
        this.x=x;
        this.y=y;
        this.hand = hand;
        setPreferredSize(new Dimension(40, 40));
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick();
            }
        });
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public boolean checkIfTaken() {
        return taken;
    }

    public void take() {
        this.taken = true;
    }

    public boolean occupied() {
        return (!taken && tile != null);
    }

    public void setMultiplier(String multiplier) {
        this.multiplier = multiplier;
    }

    public String getMultiplier() {
        return multiplier;
    }

    public int getXpos(){
        return x;
    }
    public int getYpos(){
        return y;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        FontMetrics metrics = g.getFontMetrics(); // To measure text dimensions
        int width = getWidth();
        int height = getHeight();

        if (!taken) {
            if (tile == null) {
                switch (multiplier) {
                    case "DL":
                        g.setColor(new Color(173, 216, 230));
                        break;
                    case "DW":
                        g.setColor(Color.ORANGE);
                        break;
                    case "TL":
                        g.setColor(Color.blue);
                        break;
                    case "TW":
                        g.setColor(Color.red);
                        break;
                    default:
                        g.setColor(Color.LIGHT_GRAY);
                        break;
                }
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.BLACK);
                g.drawString(multiplier, 10, 20);
            } else {
                g.setColor(Color.CYAN);
                g.fillRect(0, 0, getWidth(), getHeight());
                g.setColor(Color.BLACK);
                g.drawString(Character.toString(tile.getLetter()), 10, 20);
                String score = Integer.toString(tile.getPoints());
                g.drawString(score,
                        width - metrics.stringWidth(score) - 5,
                        height - 5);
            }
        } else {
            g.setColor(new Color(139,69,19));
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.BLACK);
            g.drawString(Character.toString(tile.getLetter()), 10, 20);
            String score = Integer.toString(tile.getPoints());
            g.drawString(score,
                    width - metrics.stringWidth(score) - 5,
                    height - 5);
        }
    }


}
