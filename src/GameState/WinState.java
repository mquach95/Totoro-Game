package GameState;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class WinState extends GameState {

    BufferedImage img;


    public WinState(GameStateManager gsm) {
        super(gsm);
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/CATBUS.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {}
    public void tick() { }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        g.setColor(new Color(122, 255, 149));
        g.setFont(new Font("Century Gothic", Font.PLAIN, 28));
        g.drawString("NEXT LEVEL", 380, 200);

        g.drawImage(img, GamePanel.WIDTH/2 - img.getWidth()/2, 240, null);
    }


    public void keyPressed(int k) {
        if(k == KeyEvent.VK_ENTER || k == KeyEvent.VK_SPACE) {
                gsm.setState(GameStateManager.LEVEL2STATE);
        }
    }
    public void keyReleased(int k) {}
}
