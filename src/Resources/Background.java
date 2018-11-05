package Resources;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Background {
    private BufferedImage img;

    private double x, y, dx, dy;
    private double moveScale;

    public Background(String s, double ms) {
        try {
            img = ImageIO.read(getClass().getResourceAsStream(s));
            moveScale = ms;
        }
        catch(Exception e) {

        }
    }

    /*public void setPosition(double x, double y) {
        this.x = (x * moveScale) % GamePanel.WIDTH;
        this.y = y;
    }*/

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void tick() {
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        g.drawImage(img, (int)x, (int)y, null);
        if(x < 0) {
            g.drawImage(img, (int)x + GamePanel.WIDTH, (int)y, null);
        }
        if(x > 0) {
            g.drawImage(img, (int)x - GamePanel.WIDTH, (int)y, null);
        }
    }

}
