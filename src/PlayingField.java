import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayingField extends JFrame {
    private GameBoard gameBoard;
    private ScorePanel scoreBoard;
    public PlayingField(List playerList) {
        setBounds(25,25,750,750);
        setLayout(new BorderLayout());

        gameBoard = new GameBoard();
        scoreBoard=new ScorePanel(playerList);
        this.add(gameBoard, BorderLayout.CENTER);
        this.add(scoreBoard,BorderLayout.EAST);


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
