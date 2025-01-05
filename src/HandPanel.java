import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HandPanel extends JPanel {
    private List<Tile> hand;
    private Tile selectedTile;
    private boolean swapMode = false;
    private final List<Tile> swappedTiles = new ArrayList<>();

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

    private char getWildcardLetter() {
        String input = JOptionPane.showInputDialog(null,
                "Enter the letter for the wildcard tile:",
                "Set Wildcard Letter",
                JOptionPane.PLAIN_MESSAGE);

        if (input != null && input.length() == 1 && Character.isLetter(input.charAt(0))) {
            return Character.toUpperCase(input.charAt(0)); // Return as uppercase
        } else {
            JOptionPane.showMessageDialog(null,
                    "Invalid input. Please enter a single letter.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return getWildcardLetter();
        }
    }

    private TileButton createTileButton(Tile tile) {
        TileButton button = new TileButton(tile);
        button.setPreferredSize(new Dimension(50, 50));
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button.addActionListener(_ -> {
            if (swapMode) {
                swappedTiles.add(tile);
                hand.remove(tile);
                selectedTile = null;
                refreshHand();
            } else {
                selectedTile = tile;
                if (tile.getLetter() == '_') {
                    char newLetter = getWildcardLetter();
                    tile.setLetter(newLetter);
                    refreshHand();
                }
            }
        });


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

    public void setHand(List<Tile> newHand) {
        this.hand = newHand;
        refreshHand();
    }

    public void addTile(Tile tile) {
        hand.add(tile);
        refreshHand();
    }

    public void setSwapMode(boolean newMode) {
        swapMode = newMode;
    }

    public boolean checkIfSwap() {
        return swapMode;
    }

    public List<Tile> getSwappedTiles() {
        return swappedTiles;
    }

    public void clearSwappedTiles() {
        swappedTiles.clear();
    }

}
