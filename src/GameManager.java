import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.max;

public class GameManager {
    private PlayingField playingField;
    private List<Player> playerList;
    private TileBag tileBag;
    private Dictionary dictionary;
    private GameBoard gameBoard;
    private Player currentPlayer;
    private int playerIndex;
    private Clip placeSFX;

    private boolean validWords() {
        boolean allValid = true;
        String word = "", word2 = "";
        for (int i = 0; i < 15; i++) {
            word = "";
            word2 = "";
            for (int j = 0; j < 15; j++) {
                try {
                    word += gameBoard.getTile(i, j).getLetter();
                } catch (NullPointerException e) {
                    if (word.length() > 1 && !dictionary.contains(word)) {
                        return false;
                    }
                    word = "";
                }
                try {
                    word2 += gameBoard.getTile(j, i).getLetter();
                } catch (NullPointerException e) {
                    if (word2.length() > 1 && !dictionary.contains(word2)) {
                        return false;
                    }
                    word2 = "";
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
        StringBuilder word = new StringBuilder();
        int wordScore = 0;
        int wordMultiplier = 0;
        for (int i = 0; i < 15; i++) {
            word = new StringBuilder();
            wordScore = 0;
            wordMultiplier = 0;
            for (int j = 0; j < 15; j++) {
                if (gameBoard.getTile(i, j) == null) {
                    if(word.length()>1){
                    score += wordScore * wordMultiplier;}
                    word = new StringBuilder();
                    wordMultiplier = 0;
                    wordScore = 0;
                } else {
                    word.append(gameBoard.getTile(i, j).getLetter());
                    wordScore += tileScore(gameBoard.getTileSpace(i, j));
                    if (!gameBoard.checkIfTaken(i, j) && gameBoard.getTile(i, j) != null) {
                        wordMultiplier = max(1, wordMultiplier);
                        if (Objects.equals(gameBoard.getTileSpace(i, j).getMultiplier(), "DW"))
                            wordMultiplier = 2;
                        if (Objects.equals(gameBoard.getTileSpace(i, j).getMultiplier(), "TW"))
                            wordMultiplier = 3;

                    }
                }
            }
        }
        for (int j = 0; j < 15; j++) {
            word = new StringBuilder();
            wordScore = 0;
            wordMultiplier = 0;
            for (int i = 0; i< 15; i++) {
                if (gameBoard.getTile(i, j) == null) {
                    if(word.length()>1){
                    score += wordScore * wordMultiplier;
                    }
                    word = new StringBuilder();
                    wordMultiplier = 0;
                    wordScore = 0;
                } else {
                    word.append(gameBoard.getTile(i, j).getLetter());
                    wordScore += tileScore(gameBoard.getTileSpace(i, j));
                    if (!gameBoard.checkIfTaken(i, j) && gameBoard.getTile(i, j) != null) {
                        wordMultiplier = max(1, wordMultiplier);
                        if (Objects.equals(gameBoard.getTileSpace(i, j).getMultiplier(), "DW"))
                            wordMultiplier = 2;
                        if (Objects.equals(gameBoard.getTileSpace(i, j).getMultiplier(), "TW"))
                            wordMultiplier = 3;

                    }
                }
            }
        }
        return score;
    }

    boolean isConnectedToExisting(List<TileSpace> tiles) {
        if (!gameBoard.checkIfTaken(7, 7) && gameBoard.getTile(7, 7) != null)
            return true;
        for (TileSpace tile : tiles) {
            int x = tile.getXpos(), y = tile.getYpos();
            if ((x > 0 && gameBoard.checkIfTaken(x - 1, y)) ||
                    (x < 14 && gameBoard.checkIfTaken(x + 1, y)) ||
                    (y > 0 && gameBoard.checkIfTaken(x, y - 1)) ||
                    (y < 14 && gameBoard.checkIfTaken(x, y + 1))) {
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
        JOptionPane.showMessageDialog(playingField, message, message, JOptionPane.ERROR_MESSAGE);
    }

    public boolean validMove() {
        List<TileSpace> placedTiles = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (!gameBoard.checkIfTaken(i, j)) {
                    if (gameBoard.getTile(i, j) != null) {
                        placedTiles.add(gameBoard.getTileSpace(i, j));
                    }
                }
            }
        }
        if (checkIfSameRow(placedTiles)) {
            System.out.println("Same row");
            if (isConnectedToExisting(placedTiles)) {
                System.out.println("Connected to existing tile");
                if (validWords()) {
                    System.out.println("All words are valid");
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

    public void loadNextRound(){
        if (tileBag.getSize() == 0) {
            gameEnd();
            return;
        }
        playerIndex += 1;
        if (playerIndex < playerList.size()) {
            currentPlayer = playerList.get(playerIndex);
        } else {
            playerIndex = 0;
            currentPlayer = playerList.getFirst();
        }
        System.out.println("The next player up is " + currentPlayer.getName());
        playingField.repaint();
        System.out.println("Repainted the playing field");
        placeSFX.start();
    }

    public void roundEnd() {

        if(playingField.getHandPanel().checkIfSwap()){
            List<Tile> currentTiles = currentPlayer.getTileList();
            List<Tile> swappedTiles=playingField.getHandPanel().getSwappedTiles();
            if(tileBag.getSize()<swappedTiles.size()){
                showErrorMessage("Insufficient tiles remaining");
                return;
            }
            List<Tile> newTiles=tileBag.swapTiles(swappedTiles);
            currentTiles.addAll(newTiles);
            playingField.getHandPanel().clearSwappedTiles();
            playingField.getHandPanel().setSwapMode(false);
            currentPlayer.setTileList(currentTiles);
            loadNextRound();
        }
        else
        if (validMove()) {
            System.out.println("The move is valid");
            int score = calculateScore();
            System.out.println("Calculated additional score of " + score);
            currentPlayer.setScore(currentPlayer.getScore() + score);
            List<Tile> currentTiles = currentPlayer.getTileList();
            currentTiles.addAll(tileBag.replenishTiles(7 - currentPlayer.getTileList().size()));
            currentPlayer.setTileList(currentTiles);
            loadNextRound();
        }
    }

    private void gameEnd() {
        Player winner = playerList.getFirst();
        for (Player p : playerList) {
            if (p.getScore() > winner.getScore()) {
                winner = p;
            }
        }
        playingField.loadEndScreen(winner);
    }

    public GameManager(PlayingField playingField, List<Player> playerList) {
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

    public void setPlayingField(PlayingField pf) {
        this.playingField = pf;
        this.gameBoard = this.playingField.getGameBoard();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public List<Player> getPlayers() {
        return playerList;
    }

    public void setSwapMode(){
        for(int i=0;i<15;i++){
            for(int j=0;j<15;j++){
                if(!gameBoard.checkIfTaken(i,j) && gameBoard.getTile(i,j)!=null) {
                    showErrorMessage("You can't swap if you've placed tiles");
                    return;
                }
            }
        }
        playingField.getHandPanel().setSwapMode(true);
    }

}
