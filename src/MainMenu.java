import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu(Application app) {

        JButton play = new JButton("Play");
        JButton exit = new JButton("Exit");
        JButton help = new JButton("Help");
        JButton addWords = new JButton("Add Words");
        play.addActionListener(_ -> app.playerSelect());
        exit.addActionListener(_ -> System.exit(0));
        help.addActionListener(_ -> app.helpMenu());
        addWords.addActionListener(_ -> app.wordAddView());
        Logo logo = new Logo();
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        setPreferredSize(new Dimension(800, 600));
        this.add(logo);
        this.add(play);
        this.add(addWords);
        this.add(help);
        this.add(exit);

    }
}
