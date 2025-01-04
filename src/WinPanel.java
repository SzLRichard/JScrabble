import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinPanel extends JPanel {
    private Application app;
    private JButton backButton;
    private Player winner;
    public WinPanel(Application app,Player winner) {
        this.winner=winner;
        this.app = app;
        setLayout(new BorderLayout());
        WinBanner banner=new WinBanner();
        JLabel winnerName=new JLabel(winner.getName());
        JLabel winnerPoints=new JLabel(String.valueOf(winner.getScore()));
        add(winnerName, BorderLayout.NORTH);
        add(winnerPoints, BorderLayout.SOUTH);
        add(banner, BorderLayout.CENTER);
        backButton=new JButton("Back to menu");
        backButton.addMouseListener(
                new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        app.mainMenu();
                    }
                }
        );
    }
}
