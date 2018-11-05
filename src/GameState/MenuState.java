package GameState;

import Resources.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

    private Background bg;

    private String[] options = {
            "Start",
            "Quit"
    };
    private int currentChoice = 0;

    public MenuState(GameStateManager gsm) {

        super(gsm);

        bg = new Background("/BACKGROUND.PNG", 1);
        bg.setVector(-0.1, 0);

    }



    public void init() {}

    public void tick() {
        bg.tick();
    }

    public void draw(Graphics g) {
        bg.draw(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Century Gothic", Font.BOLD, 36));
        g.drawString("Totoro's Adventure", 145, 170);

        for (int i = 0; i < options.length; i++) {
            if(i == currentChoice) {
                g.setColor(new Color(122, 255, 149));
            } else {
                g.setColor(Color.WHITE);
            }

            g.setFont(new Font("Century Gothic", Font.PLAIN, 22));
            g.drawString(options[i], 145, 240 + i * 30);
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
