import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {

    public MainMenu(Application app){

        JLabel title = new JLabel("JSCRABBLE");
        JButton play = new JButton("Play");
        JButton exit = new JButton("Exit");
        JButton help = new JButton("Help");
        JButton addWords = new JButton("Add Words");
        play.addActionListener(e->app.playerSelect());
        exit.addActionListener(e->System.exit(0));
        help.addActionListener(e->app.helpMenu());
        addWords.addActionListener(e->app.dictionaryManager());
        Logo logo = new Logo();
        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        setPreferredSize(new Dimension(800,600));
        this.add(logo);
        this.add(play);
        this.add(addWords);
        this.add(help);
        this.add(exit);

    }
}
