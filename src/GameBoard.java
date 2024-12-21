import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GameBoard extends JPanel {
    private TileSpace[][] tileBoard;
    public GameBoard() {
        tileBoard = new TileSpace[15][15];
        setLayout(new GridLayout(15,15));
        Arrays.stream(tileBoard).flatMap(Arrays::stream).forEach(tileSpace -> {tileSpace=new TileSpace();this.add(tileSpace);});
    }

}
