package Objects;

import java.awt.*;

public class Collision {

    public static boolean playerBlock(Point p, Block b) {
        return b.contains(p);
    }

    public static boolean playerMovingBlock(Point p, MovingBlock b) {
        return b.contains(p);
    }

    public static boolean playerChibi(Point p, Chibi b) { return b.contains(p); }

    public static boolean playerGoal(Point p, Goal b) { return b.contains(p); }

}
