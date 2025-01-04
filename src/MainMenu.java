import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class MainMenu extends JPanel {

    private Application app;
    private JButton play;
    private JButton exit;
    private JButton help;
    private JButton addWords;
    private JLabel title;
    Logo logo;

    public MainMenu(Application app){

        this.app = app;
        title=new JLabel("JSCRABBLE");
        play = new JButton("Play");
        exit = new JButton("Exit");
        help = new JButton("Help");
        addWords = new JButton("Add Words");
        play.addActionListener(e->app.playerSelect());
        exit.addActionListener(e->System.exit(0));
        help.addActionListener(e->app.helpMenu());
        addWords.addActionListener(e->app.dictionaryManager());
        logo=new Logo();
        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        setPreferredSize(new Dimension(800,600));
        this.add(logo);
        this.add(play);
        this.add(addWords);
        this.add(help);
        this.add(exit);

    }
}
