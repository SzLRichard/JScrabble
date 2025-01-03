import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application extends JFrame {
    private JPanel mainPanel;
    private List<Player> players;
    private CardLayout cardLayout;
    private PlayingField playingField;
    private GameManager gameManager;
    private Clip bgm;
    public Application() {
        try{
            AudioInputStream audioInputStream  = AudioSystem.getAudioInputStream(new File("resources/bgm.wav"));
            bgm = AudioSystem.getClip();
            bgm.open(audioInputStream);
            bgm.start();
        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException e){
            System.out.println("Could not open bgm");
        }
        setTitle("JScrabble");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(25, 25, 950, 750);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        players = new ArrayList<>();
        mainPanel.add(new MenuPanel(this), "Menu");

        add(mainPanel);
        cardLayout.show(mainPanel, "Menu");
        setVisible(true);
    }

    public void setPlayerNames(List<String> names) throws Exception {
        for (String name : names) {
            if(name != null) {
                this.players.add(new Player(name));
            }
        }
        if(players.size() < 2) {
            throw(new Exception("Not enough players"));
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void switchToGame() {
        gameManager = new GameManager(playingField,players);
        playingField=new PlayingField(gameManager,this);
        gameManager.setPlayingField(playingField);
        mainPanel.add(playingField, "Game");
        cardLayout.show(mainPanel, "Game");
    }
    public void loadEndScreen(Player winner){
        JPanel winnerPanel = new JPanel(cardLayout);
        winnerPanel.setLayout(new BorderLayout());
        WinBanner banner=new WinBanner();
        JLabel winnerName=new JLabel(winner.getName());
        JLabel winnerPoints=new JLabel(String.valueOf(winner.getScore()));
        winnerPanel.add(winnerName, BorderLayout.NORTH);
        winnerPanel.add(winnerPoints, BorderLayout.SOUTH);
        winnerPanel.add(banner, BorderLayout.CENTER);
        mainPanel.add(winnerPanel,"Winner");
        cardLayout.show(mainPanel,"Winner");
    }
}
