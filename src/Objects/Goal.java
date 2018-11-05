package Objects;

import GameState.GameState;
import Resources.Images;

import java.awt.*;

public class Goal extends Rectangle {

    private double x;
    private double y;
    private int width;
    private int height;
    private boolean end = false;

    public Goal(double x, double y) {
        this.x = x;
        this.y = y;
        width = 36;
        height = 120;
        setBounds((int)x, (int)y, width, height);
    }

    public void draw(Graphics g) {
        g.drawImage(Images.busStop[0],
                (int)x - (int)GameState.xOffset,
                (int)y - (int)GameState.yOffset,
                width,
                height,
                null);
    }

}
