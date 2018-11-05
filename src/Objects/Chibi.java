package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Animation;
import Entities.Player;
import GameState.GameState;
import Resources.Images;

public class Chibi extends Rectangle {

    private double x, y;
    private int amt, id;
    Player player;
    private static final int width = 30;
    private static final int height = 35;
    public boolean isExist = true;
    Animation animation;
    private ArrayList<BufferedImage[]> chibis = new ArrayList<BufferedImage[]>();


    public Chibi(double x, double y, int amt, boolean isExist, int id, Player player) {
        this.x = x;
        this.y = y;
        this.amt = amt;
        this.player = player;
        this.isExist = isExist;
        this.id = id;
        setBounds((int)x, (int)y, width, height);

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getAmt() {
        return amt;
    }

    public void tick() {
    }

    public void draw(Graphics g) {
        if(isExist) {
            /*g.setColor(Color.BLUE);
            g.fillOval((int)x - (int)GameState.xOffset, (int)y - (int)GameState.yOffset, width, height);*/
            if(id == 1) {
                g.drawImage(Images.chibiBig[0],
                        (int) x - (int) GameState.xOffset,
                        (int) y - (int) GameState.yOffset,
                        width,
                        height,
                        null);
            } else {
                g.drawImage(Images.chibiSmall[0],
                (int) x - (int) GameState.xOffset,
                        (int) y - (int) GameState.yOffset,
                        width,
                        height,
                        null);
            }
        }

    }


}
