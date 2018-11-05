package GameState;


import Entities.HUD;
import Entities.Player;
import Main.GamePanel;
import Mapping.Map;
import Objects.Goal;
import Resources.Background;

import java.awt.*;

public class Level1State extends GameState {

    private Player player;
    private Map map;
    private Background bg;
    private Goal goal;
    private HUD hud;

    public Level1State(GameStateManager gsm) {

        super(gsm);

    }

    public void init() {
        player = new Player();
        map = new Map("/map1.map", player);
        bg = new Background("/BACKGROUND.PNG", 0.1);
        hud = new HUD(player);

        xOffset = 0;
        yOffset = -100;
    }

    public void tick() {

        player.tick(map.getBlocks(), map.getMovingBlocks(), map.getChibi(), map.getGoal());
        map.tick();
        if(player.getDeath()) {
            gsm.setState(GameStateManager.GAMEOVERSTATE);
        }
        if(player.getWin()) {
            gsm.setState(GameStateManager.WINSTATE);
        }

    }

    public void draw(Graphics g) {
        bg.draw(g);
        player.draw(g);
        map.draw(g);
        hud.draw(g);
    }

    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    public void keyReleased(int k) {
        player.keyReleased(k);
    }

}
