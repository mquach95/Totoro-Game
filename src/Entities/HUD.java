package Entities;

import Resources.Images;

import java.awt.*;

public class HUD {

    private Player player;
    private Font font;

    public HUD(Player p) {
        player = p;
        try {
            font = new Font("Arial", Font.PLAIN, 20);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(Images.hudStuff[0], 30, 20, 35, 35, null);
        g.drawImage(Images.hudStuff[1], 30, 60, 35, 35, null);

        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(player.getHealth() + "", 42, 45);
        g.drawString(player.getPoints() + "", 42, 90);

    }

}
