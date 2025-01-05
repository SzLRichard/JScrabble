import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayingFieldPanel extends JPanel {
    private final GameManager gameManager;
    private final GameBoardPanel gameBoardPanel;
    private final ScorePanel scoreBoard;
    private final HandPanel handPanel;
    private Player currentPlayer;
    private final Application game;
    private final int startingTime = 5 * 60;
    private final JLabel timerLabel;
    private int timeRemaining;

    public PlayingFieldPanel(GameManager gm, Application app) {
        setLayout(new BorderLayout());
        this.game = app;
        this.gameManager = gm;
        List<Player> players = gameManager.getPlayers();
        this.currentPlayer = gameManager.getCurrentPlayer();
        scoreBoard = new ScorePanel(players);
        scoreBoard.setCurrentPlayer(currentPlayer);
        handPanel = new HandPanel(players.getFirst().getTileList());
        gameBoardPanel = new GameBoardPanel(handPanel);
        JPanel leftSide = new JPanel(new GridLayout(3, 1));
        JPanel timerPanel = new JPanel(new FlowLayout());
        timerLabel = new JLabel();
        timerPanel.add(timerLabel);
        JButton swapTiles = new JButton("Swap tiles");
        swapTiles.addActionListener(_ -> gameManager.setSwapMode());

        JButton finalize = new JButton("Finalize");
        finalize.addActionListener(_ -> gameManager.roundEnd());

        timeRemaining = startingTime;
        Timer timer = new Timer(1000, _ -> {
            if (timeRemaining > 0) {
                timeRemaining--;
                timerLabel.setText(String.format("%02d:%02d", timeRemaining / 60, timeRemaining % 60));
            } else {
                gameManager.forceRoundEnd();
                timeRemaining = startingTime;
            }

        });

        leftSide.add(timerPanel);
        leftSide.add(swapTiles);
        leftSide.add(finalize);

        this.add(gameBoardPanel, BorderLayout.CENTER);
        this.add(scoreBoard, BorderLayout.EAST);
        this.add(leftSide, BorderLayout.WEST);
        this.add(handPanel, BorderLayout.SOUTH);
        setVisible(true);
        timer.start();
    }

    public void resetTimer() {
        timeRemaining = startingTime;
    }

    public GameBoardPanel getGameBoard() {
        return gameBoardPanel;
    }

    public HandPanel getHandPanel() {
        return handPanel;
    }

    public void loadEndScreen(Player winner) {
        game.loadEndScreen(winner);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentPlayer = gameManager.getCurrentPlayer();
        handPanel.setHand(currentPlayer.getTileList());
        handPanel.repaint();
        scoreBoard.setCurrentPlayer(currentPlayer);
        scoreBoard.repaint();
        gameBoardPanel.repaint();
    }

}
