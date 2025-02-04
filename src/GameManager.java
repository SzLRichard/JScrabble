import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.max;

public class GameManager {
    private PlayingFieldPanel playingFieldPanel;
    private final List<Player> playerList;
    private final TileBag tileBag;
    private final Dictionary dictionary;
    private GameBoardPanel gameBoardPanel;
    private Player currentPlayer;
    private int playerIndex;
    private Clip placeSFX;
    private int emptyBagRounds;

    private boolean validWords() {
        StringBuilder word;
        StringBuilder word2;
        for (int i = 0; i < 15; i++) {
            word = new StringBuilder();
            word2 = new StringBuilder();
            for (int j = 0; j < 15; j++) {
                try {
                    word.append(gameBoardPanel.getTile(i, j).getLetter());
                } catch (NullPointerException e) {
                    if (word.length() > 1 && !dictionary.contains(word.toString())) {
                        return false;
                    }
                    word = new StringBuilder();
                }
                try {
                    word2.append(gameBoardPanel.getTile(j, i).getLetter());
                } catch (NullPointerException e) {
                    if (word2.length() > 1 && !dictionary.contains(word2.toString())) {
                        return false;
                    }
                    word2 = new StringBuilder();
                }
            }
        }
        return true;
    }

    private int tileScore(TileSpace tileSpace) {
        if (tileSpace.getTile() == null) return 0;
        if (Objects.equals(tileSpace.getMultiplier(), "DL"))
            return tileSpace.getTile().getPoints() * 2;
        else if (Objects.equals(tileSpace.getMultiplier(), "TL"))
            return tileSpace.getTile().getPoints() * 3;
        else
            return tileSpace.getTile().getPoints();
    }

    private int calculateScore() {
        int score = 0;
        StringBuilder word;
        int wordScore;
        int wordMultiplier;
        for (int i = 0; i < 15; i++) {
            word = new StringBuilder();
            wordScore = 0;
            wordMultiplier = 0;
            for (int j = 0; j <= 15; j++) {
                if (j == 15 || gameBoardPanel.getTile(i, j) == null) {
                    if (word.length() > 1) {
                        score += wordScore * wordMultiplier;
                    }
                    word = new StringBuilder();
                    wordMultiplier = 0;
                    wordScore = 0;
                } else {
                    word.append(gameBoardPanel.getTile(i, j).getLetter());
                    wordScore += tileScore(gameBoardPanel.getTileSpace(i, j));
                    if (!gameBoardPanel.checkIfTaken(i, j) && gameBoardPanel.getTile(i, j) != null) {
                        wordMultiplier = max(1, wordMultiplier);
                        if (Objects.equals(gameBoardPanel.getTileSpace(i, j).getMultiplier(), "DW"))
                            wordMultiplier = 2;
                        if (Objects.equals(gameBoardPanel.getTileSpace(i, j).getMultiplier(), "TW"))
                            wordMultiplier = 3;

                    }
                }
            }
        }
        for (int j = 0; j < 15; j++) {
            word = new StringBuilder();
            wordScore = 0;
            wordMultiplier = 0;
            for (int i = 0; i <= 15; i++) {
                if (i == 15 || gameBoardPanel.getTile(i, j) == null) {
                    if (word.length() > 1) {
                        score += wordScore * wordMultiplier;
                    }
                    word = new StringBuilder();
                    wordMultiplier = 0;
                    wordScore = 0;
                } else {
                    word.append(gameBoardPanel.getTile(i, j).getLetter());
                    wordScore += tileScore(gameBoardPanel.getTileSpace(i, j));
                    if (!gameBoardPanel.checkIfTaken(i, j) && gameBoardPanel.getTile(i, j) != null) {
                        wordMultiplier = max(1, wordMultiplier);
                        if (Objects.equals(gameBoardPanel.getTileSpace(i, j).getMultiplier(), "DW"))
                            wordMultiplier = 2;
                        if (Objects.equals(gameBoardPanel.getTileSpace(i, j).getMultiplier(), "TW"))
                            wordMultiplier = 3;

                    }
                }
            }
        }
        return score;
    }

    private boolean isConnectedToExisting(List<TileSpace> tiles) {
        if (gameBoardPanel.getTileSpace(7, 7).occupied())
            return true;
        for (TileSpace tile : tiles) {
            int x = tile.getXpos(), y = tile.getYpos();
            if ((x > 0 && gameBoardPanel.checkIfTaken(x - 1, y)) ||
                    (x < 14 && gameBoardPanel.checkIfTaken(x + 1, y)) ||
                    (y > 0 && gameBoardPanel.checkIfTaken(x, y - 1)) ||
                    (y < 14 && gameBoardPanel.checkIfTaken(x, y + 1))) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfSameRow(List<TileSpace> placedTiles) {
        boolean sameRow = placedTiles.stream().allMatch(t -> t.getXpos() == placedTiles.getFirst().getXpos());
        boolean sameCol = placedTiles.stream().allMatch(t -> t.getYpos() == placedTiles.getFirst().getYpos());
        return sameRow || sameCol;
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(playingFieldPanel, message, message, JOptionPane.ERROR_MESSAGE);
    }

    private boolean validMove() {
        List<TileSpace> placedTiles = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (!gameBoardPanel.checkIfTaken(i, j)) {
                    if (gameBoardPanel.getTile(i, j) != null) {
                        placedTiles.add(gameBoardPanel.getTileSpace(i, j));
                    }
                }
            }
        }
        if (checkIfSameRow(placedTiles)) {
            if (isConnectedToExisting(placedTiles)) {
                if (validWords()) {
                    return true;
                } else {
                    showErrorMessage("Not a valid word");
                }
            } else {
                showErrorMessage("New word needs to connect to existing tile");
            }
        } else {
            showErrorMessage("Tiles must be in a row");
        }
        return false;
    }

    private void gameEnd() {
        Player winner = playerList.getFirst();
        for (Player p : playerList) {
            if (p.getScore() > winner.getScore()) {
                winner = p;
            }
        }
        playingFieldPanel.loadEndScreen(winner);
    }

    private void loadNextRound() {
        if (emptyBagRounds == 4) {
            gameEnd();
            return;
        }
        if (tileBag.getSize() == 0) {
            emptyBagRounds++;
        }
        playerIndex += 1;
        if (playerIndex < playerList.size()) {
            currentPlayer = playerList.get(playerIndex);
        } else {
            playerIndex = 0;
            currentPlayer = playerList.getFirst();
        }
        playingFieldPanel.repaint();
        placeSFX.start();
        playingFieldPanel.resetTimer();
    }

    public void roundEnd() {

        if (playingFieldPanel.getHandPanel().checkIfSwap()) {
            List<Tile> currentTiles = currentPlayer.getTileList();
            List<Tile> swappedTiles = playingFieldPanel.getHandPanel().getSwappedTiles();
            if (tileBag.getSize() < swappedTiles.size()) {
                showErrorMessage("Insufficient tiles remaining");
                return;
            }
            List<Tile> newTiles = tileBag.swapTiles(swappedTiles);
            currentTiles.addAll(newTiles);
            playingFieldPanel.getHandPanel().clearSwappedTiles();
            playingFieldPanel.getHandPanel().setSwapMode(false);
            currentPlayer.setTileList(currentTiles);
            loadNextRound();
        } else if (validMove()) {
            int score = calculateScore();
            currentPlayer.setScore(currentPlayer.getScore() + score);
            List<Tile> currentTiles = currentPlayer.getTileList();
            currentTiles.addAll(tileBag.replenishTiles(7 - currentPlayer.getTileList().size()));
            currentPlayer.setTileList(currentTiles);
            gameBoardPanel.placeTiles();
            loadNextRound();
        }
    }

    public void forceRoundEnd() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (!gameBoardPanel.checkIfTaken(i, j) && gameBoardPanel.getTile(i, j) != null) {
                    Tile removedTile = gameBoardPanel.getTile(i, j);
                    gameBoardPanel.getTileSpace(i, j).setTile(null);
                    gameBoardPanel.getTileSpace(i, j).repaint();
                    playingFieldPanel.getHandPanel().addTile(removedTile);
                    playingFieldPanel.getHandPanel().repaint();
                }
            }
        }
        loadNextRound();
    }

    public GameManager(List<Player> playerList) {
        emptyBagRounds = 0;
        this.playerList = playerList;
        this.dictionary = new Dictionary();
        tileBag = new TileBag();
        for (Player player : playerList) {
            player.setTileList(tileBag.replenishTiles(7));
        }
        playerIndex = 0;
        currentPlayer = playerList.getFirst();
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/place.wav"));
            placeSFX = AudioSystem.getClip();
            placeSFX.open(audioInputStream);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("Could not open placing sfx");
        }
    }

    public void setPlayingField(PlayingFieldPanel pf) {
        this.playingFieldPanel = pf;
        this.gameBoardPanel = this.playingFieldPanel.getGameBoard();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Player> getPlayers() {
        return playerList;
    }

    public void setSwapMode() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (!gameBoardPanel.checkIfTaken(i, j) && gameBoardPanel.getTile(i, j) != null) {
                    showErrorMessage("You can't swap if you've placed tiles");
                    return;
                }
            }
        }
        playingFieldPanel.getHandPanel().setSwapMode(true);
    }

}
