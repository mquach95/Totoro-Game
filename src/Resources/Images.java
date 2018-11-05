package Resources;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Images {

    public static BufferedImage[] blocks;
    public static BufferedImage[] totoroIdle;
    public static BufferedImage[] totoroWalk;
    public static BufferedImage[] chibiBig;
    public static BufferedImage[] chibiSmall;
    public static BufferedImage[] busStop;
    public static BufferedImage[] hudStuff;

    public Images() {

        blocks = new BufferedImage[3];
        totoroIdle = new BufferedImage[1];
        totoroWalk = new BufferedImage[6];
        chibiBig = new BufferedImage[2];
        chibiSmall = new BufferedImage[2];
        busStop = new BufferedImage[1];
        hudStuff = new BufferedImage[2];

        try {
            blocks[0] = ImageIO.read(getClass().getResourceAsStream("/GRASS.png"));
            blocks[1] = ImageIO.read(getClass().getResourceAsStream("/GRASSTOP.png"));
            blocks[2] = ImageIO.read(getClass().getResourceAsStream("/GRASSTOP-ROUND.png"));

            totoroIdle[0] = ImageIO.read(getClass().getResourceAsStream("/IDLE_R.png"));

            totoroWalk[0] = ImageIO.read(getClass().getResourceAsStream("/WALK_R1.png"));
            totoroWalk[1] = ImageIO.read(getClass().getResourceAsStream("/WALK_R2.png"));
            totoroWalk[2] = ImageIO.read(getClass().getResourceAsStream("/WALK_R3.png"));
            totoroWalk[3] = ImageIO.read(getClass().getResourceAsStream("/WALK_R4.png"));
            totoroWalk[4] = ImageIO.read(getClass().getResourceAsStream("/WALK_R5.png"));
            totoroWalk[5] = ImageIO.read(getClass().getResourceAsStream("/WALK_R6.png"));

            chibiBig[0] = ImageIO.read(getClass().getResourceAsStream("/CHIBI1-1.png"));
            //chibiBig[1] = ImageIO.read(getClass().getResourceAsStream("/CHIBI1-2.png"));

            chibiSmall[0] = ImageIO.read(getClass().getResourceAsStream("/CHIBI2-1.png"));
            //chibiSmall[1] = ImageIO.read(getClass().getResourceAsStream("/CHIBI2-2.png"));

            busStop[0] = ImageIO.read(getClass().getResourceAsStream("/BUS-STOP.png"));

            hudStuff[0] = ImageIO.read(getClass().getResourceAsStream("/LIVES.png"));
            hudStuff[1] = ImageIO.read(getClass().getResourceAsStream("/CHIBI-count.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
