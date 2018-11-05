package Objects;

import java.awt.*;

import GameState.GameState;
import Resources.Images;

public class Block extends Rectangle {

    public static final int blockSize = 40;
    private int id;

    public Block(int x, int y, int id) {

        setBounds(x, y, blockSize, blockSize);
        this.id = id;

    }

    public void tick() {}

    public void draw(Graphics g) {
        /*if(id != 0) {
            g.setColor(new Color(79, 185, 22));
            g.fillRect(x - (int)GameState.xOffset,
                    y - (int)GameState.yOffset,
                    width,
                    height);
        }*/

        if(id != 0) {
            g.drawImage(Images.blocks[id-1],
                    x - (int)GameState.xOffset,
                    y - (int)GameState.yOffset,
                    width,
                    height, null);
        }
    }

    // Getters and Setters
    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

}
