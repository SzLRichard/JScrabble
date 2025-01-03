import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class PlayingField extends JPanel {
    private GameManager gameManager;
    private GameBoard gameBoard;
    private ScorePanel scoreBoard;
    private HandPanel handPanel;
    private List<Player> players;
    private JButton finalize;
    private JButton swapTiles;
    private Player currentPlayer;
    private Application game;

    private void roundEnd(){
        gameBoard.placeTiles();
    }

    public PlayingField(GameManager gm, Application app) {
        setLayout(new BorderLayout());
        this.game=app;
        this.gameManager=gm;
        this.players=gameManager.getPlayers();
        this.currentPlayer=gameManager.getCurrentPlayer();
        scoreBoard = new ScorePanel(players);
        handPanel = new HandPanel(players.getFirst().getTileList());
        gameBoard = new GameBoard(handPanel);
        JPanel leftSide=new JPanel(new GridLayout(3,1));

        swapTiles=new JButton("Swap tiles");
        swapTiles.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        System.out.println("The swap button was clicked");
                        gameManager.setSwapMode();
                    }
                }
        );
        finalize = new JButton("Finalize");
        finalize.addMouseListener(
                new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        System.out.println("The finalize button was clicked");
                        gameManager.roundEnd();
                    }
                }
        );
        //leftSide.add(timer);
        leftSide.add(swapTiles);
        leftSide.add(finalize);

        this.add(gameBoard, BorderLayout.CENTER);
        this.add(scoreBoard, BorderLayout.EAST);
        this.add(leftSide,BorderLayout.WEST);
        this.add(handPanel, BorderLayout.SOUTH);
        setVisible(true);
    }


    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public HandPanel getHandPanel(){
        return handPanel;
    }

    public void loadEndScreen(Player winner){
        game.loadEndScreen(winner);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentPlayer=gameManager.getCurrentPlayer();
        handPanel.setHand(currentPlayer.getTileList());
        handPanel.repaint();
        scoreBoard.repaint();
        gameBoard.placeTiles();
        gameBoard.repaint();
    }

    public void updateScores() {
        scoreBoard.repaint();
    }
}
