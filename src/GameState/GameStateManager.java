package GameState;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {

    public ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int GAMEOVERSTATE = 1;
    public static final int WINSTATE = 2;
    public static final int LEVEL1STATE = 3;
    public static final int LEVEL2STATE = 4;
    public static final int WIN2STATE = 5;

    public GameStateManager() {

        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new LoseState(this));
        gameStates.add(new WinState(this));
        gameStates.add(new Level1State(this));
        gameStates.add(new Level2State(this));
        gameStates.add(new Win2State(this));

    }

    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }

    public void tick() {
        gameStates.get(currentState).tick();
    }

    public void draw(Graphics g) {
        gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k) {
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);
    }

}

