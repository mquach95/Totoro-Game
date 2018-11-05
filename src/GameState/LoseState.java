package GameState;

import Main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LoseState extends GameState {

    private String[] options = {
            "Retry",
            "Exit"
    };
    private int currentChoice = 0;

    public LoseState(GameStateManager gsm) { super(gsm); }

    public void init() {}
    public void tick() {}
    public void draw(Graphics g) {
        g.setColor(new Color(0,0,0));
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Century Gothic", Font.PLAIN, 28));
        g.drawString("Game Over", 380, 200);

        for(int i = 0; i < options.length; i++) {
            if(i == currentChoice) {
                g.setColor(new Color(122, 255, 149));
            } else {
                g.setColor(Color.WHITE);
            }

            g.setFont(new Font("Century Gothic", Font.PLAIN, 22));
            g.drawString(options[i], 400, 250 + i * 30);
        }

    }

    public void keyPressed(int k) {
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if(currentChoice >= options.length) {
                currentChoice = 0;
            }
        }
        else if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if(currentChoice < 0) {
                currentChoice = options.length - 1;
            }
        }
        if(k == KeyEvent.VK_ENTER) {
            if(currentChoice == 0) {
                gsm.setState(GameStateManager.LEVEL1STATE);
            }
            else if (currentChoice == 1) {
                System.exit(0);
            }
        }
    }

    public void keyReleased(int k) {}

}
