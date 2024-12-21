import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Application extends JFrame {
    private JPanel mainPanel;
    private List<Player> players;
    private CardLayout cardLayout;
    private PlayingField playingField;
    public Application() {
        setTitle("Scrabble Game");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(25, 25, 950, 750);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        players = new ArrayList<>();
        mainPanel.add(new MenuPanel(this), "Menu");
        playingField=new PlayingField(this.players);
        mainPanel.add(playingField, "Game");

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
        cardLayout.show(mainPanel, "Game");
        new GameManager(playingField);
    }


}
