import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Timer timer;
    private int startingTime=5*60;
    private JLabel timerLabel;
    private int timeRemaining;

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
        timerLabel=new JLabel();
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
        timeRemaining=startingTime;
        timer=new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(timeRemaining>0){
                    timeRemaining--;
                    timerLabel.setText(String.format("%02d:%02d", timeRemaining/60,timeRemaining%60));
                }
                else{
                    gameManager.forceRoundEnd();
                    timeRemaining=startingTime;
                }

            }
        });

        leftSide.add(timerLabel);
        leftSide.add(swapTiles);
        leftSide.add(finalize);

        this.add(gameBoard, BorderLayout.CENTER);
        this.add(scoreBoard, BorderLayout.EAST);
        this.add(leftSide,BorderLayout.WEST);
        this.add(handPanel, BorderLayout.SOUTH);
        setVisible(true);
        timer.start();
    }

    public void resetTimer(){
        timeRemaining=startingTime;
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
