import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Logo extends JPanel {
    private Image img;

    public Logo() {
        try {
            String path = "resources/logo.png";
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Could not read logo image");
            img = null;
        }
        setPreferredSize(new Dimension(500, 125));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            g.drawImage(img, 0, 0, 500, 125, this);
        }
    }
}
