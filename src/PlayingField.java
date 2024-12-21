import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayingField extends JPanel {
    private GameBoard gameBoard;
    private ScorePanel scoreBoard;

    public PlayingField(List<Player> playerList) {

        setLayout(new BorderLayout());
        gameBoard = new GameBoard();
        scoreBoard = new ScorePanel(playerList);
        this.add(gameBoard, BorderLayout.CENTER);
        this.add(scoreBoard, BorderLayout.EAST);

        setVisible(true);
    }
}
