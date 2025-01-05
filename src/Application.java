import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application extends JFrame {
    private final JPanel mainPanel;
    private final List<Player> players;
    private final CardLayout cardLayout;
    private Clip bgm;

    public Application() {

        setTitle("JScrabble");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(25, 25, 950, 750);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        players = new ArrayList<>();

        HelpMenu helpMenu = new HelpMenu(this);
        mainPanel.add(helpMenu, "helpMenu");

        WordAddMenu wordAddMenu = new WordAddMenu(this);
        mainPanel.add(wordAddMenu, "Dictionary");

        mainPanel.add(new MainMenu(this), "Menu");


        add(mainPanel);
        cardLayout.show(mainPanel, "Menu");
        setVisible(true);
    }

    public void setPlayerNames(List<String> names) {
        this.players.clear();
        names.forEach(name -> {
            if (name != null)
                this.players.add(new Player(name));
        });
        if (players.size() < 2) {
            playerSelect();
        }
    }

    public void mainMenu() {
        cardLayout.show(mainPanel, "Menu");
    }

    public void helpMenu() {
        cardLayout.show(mainPanel, "helpMenu");
    }

    public void wordAddView() {
        cardLayout.show(mainPanel, "Dictionary");
    }

    public void playerSelect() {
        PlayerSelectMenu playerSelectMenuPanel = new PlayerSelectMenu(this);
        mainPanel.add(playerSelectMenuPanel, "PlayerSelect");
        cardLayout.show(mainPanel, "PlayerSelect");
    }

    public void switchToGame() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resources/bgm.wav"));
            bgm = AudioSystem.getClip();
            bgm.open(audioInputStream);
            FloatControl volumeControl = (FloatControl) bgm.getControl(FloatControl.Type.MASTER_GAIN);
            float volume = -30.0f;
            volumeControl.setValue(volume);

            bgm.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            System.out.println("Could not open bgm");
        }
        GameManager gameManager = new GameManager(players);
        PlayingFieldPanel playingFieldPanel = new PlayingFieldPanel(gameManager, this);
        gameManager.setPlayingField(playingFieldPanel);
        mainPanel.add(playingFieldPanel, "Game");
        cardLayout.show(mainPanel, "Game");
    }

    public void loadEndScreen(Player winner) {
        bgm.stop();
        WinPanel winnerPanel = new WinPanel(this, winner);
        mainPanel.add(winnerPanel, "Winner");
        cardLayout.show(mainPanel, "Winner");
    }
}
