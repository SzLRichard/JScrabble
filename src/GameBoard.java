import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class GameBoard extends JPanel {
    private final TileSpace[][] tileBoard;

    private boolean removeTile() {
        return false;
    }

    private void setMultipliers() {
        try {

            BufferedReader br = new BufferedReader(new FileReader("resources/boardLayout.txt"));
            br.lines()
                    .map(line -> line.split(" "))
                    .forEach(parts -> {
                        int x = Integer.parseInt(parts[0]);
                        int y = Integer.parseInt(parts[1]);
                        String multiplier = parts[2];
                        tileBoard[x][y].setMultiplier(multiplier);
                    });
        } catch (Exception e) {
            System.out.println("Could not read board layout");
        }

    }

    public GameBoard(HandPanel handPanel) {
        tileBoard = new TileSpace[15][15];
        setLayout(new GridLayout(15, 15));
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                tileBoard[i][j] = new TileSpace(handPanel, i, j);
            }
        }
        setMultipliers();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.add(tileBoard[i][j]);
            }
        }

    }

    public TileSpace getTileSpace(int x, int y) {
        return tileBoard[x][y];
    }

    public Tile getTile(int x, int y) {
        return tileBoard[x][y].getTile();
    }

    public boolean checkIfTaken(int x, int y) {
        return tileBoard[x][y].checkIfTaken();
    }

    public void placeTiles() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (tileBoard[i][j].getTile() != null) {
                    tileBoard[i][j].take();
                    tileBoard[j][i].repaint();
                }
            }
        }
    }
}
