import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class HandPanel extends JPanel {
    private List<Tile> hand;
    private Tile selectedTile;
    private boolean swapMode = false;
    List<Tile> swappedTiles = new ArrayList<>();

    public HandPanel(List<Tile> tiles) {
        this.hand = new ArrayList<>(tiles);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setPreferredSize(new Dimension(500, 100));
        setBackground(Color.LIGHT_GRAY);
        for (Tile tile : hand) {
            TileButton tileButton = createTileButton(tile);
            add(tileButton);
        }
    }

    private TileButton createTileButton(Tile tile) {
        TileButton button = new TileButton(tile);
        button.setPreferredSize(new Dimension(50, 50));
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(swapMode){
                    swappedTiles.add(tile);
                    hand.remove(tile);
                    selectedTile = null;
                    refreshHand();
                }
                else{
                selectedTile = tile;
                }

            }
        });
        if (tile == selectedTile) {
            button.setBackground(Color.YELLOW);
        }
        return button;
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void removeSelectedTile() {
        if (selectedTile != null) {
            hand.remove(selectedTile);
            selectedTile = null;
            refreshHand();
        }
    }

    private void refreshHand() {
        System.out.println("Refreshing hand panel");
        removeAll();
        for (Tile tile : hand) {
            add(createTileButton(tile));
        }
        revalidate();
        repaint();
    }

    public void setHand(List<Tile> newHand){
        this.hand=newHand;
        refreshHand();
    }

    public void addTile(Tile tile) {
        hand.add(tile);
        refreshHand();
    }

    public void setSwapMode(boolean newMode){
        swapMode=newMode;
    }

    public boolean checkIfSwap(){
        return swapMode;
    }

    public List<Tile> getSwappedTiles(){
        return swappedTiles;
    }
    public void clearSwappedTiles(){
        swappedTiles.clear();
    }

}
