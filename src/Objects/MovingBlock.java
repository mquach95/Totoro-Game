package Objects;


import Resources.Images;
import GameState.GameState;

import java.awt.*;

public class MovingBlock extends Rectangle {

    private int leftBound, rightBound;
    private int move = 1;
    private int id;

    public MovingBlock(int x, int y, int id, int leftBound, int rightBound) {
        setBounds(x, y, Block.blockSize, Block.blockSize);
        this.id = id;
        this.rightBound = rightBound;
        this.leftBound = leftBound;
    }

    public void tick() {

        if(x + width - GameState.xOffset >= rightBound - GameState.xOffset && move != -1) {
            move *= -1;
        }
        if(x - GameState.xOffset <= leftBound - GameState.xOffset && move != 1) {
            move *= -1;
        }
        x += move;
    }

    public void draw(Graphics g) {

        if(id != 0) {
            /*g.setColor(new Color(79, 185, 22));
            g.fillRect(
                    x - (int)GameState.xOffset,
                    y - (int)GameState.yOffset,
                    width,
                    height); */
            g.drawImage(Images.blocks[2],
                    x - (int)GameState.xOffset,
                    y - (int)GameState.yOffset,
                    width,
                    height,
                    null);
        }

    }

    public int getMove() {
        return move;
    }

    public int getID() {
        return id;
    }


}
