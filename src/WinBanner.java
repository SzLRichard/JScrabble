import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class WinBanner extends JPanel {
    private Image img;

    public WinBanner() {
        try {
            Random rng = new Random();
            String path = "resources/win" + (1 + rng.nextInt(2)) + ".jpeg";
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Could not read win image");
            img = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }
}