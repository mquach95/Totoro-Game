package Main;

import GameState.GameStateManager;
import Resources.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 550;

    private Thread thread;
    private boolean isRunning = false;

    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    private GameStateManager gsm;

    public GamePanel() {

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        addKeyListener(this);
        setFocusable(true);

        new Images();

        start();

    }

    public void start() {

        isRunning = true;
        thread = new Thread(this);
        thread.start();

    }

    public void run() {
        long start, elapsed, wait;

        gsm = new GameStateManager();

        while(isRunning) {

            start = System.nanoTime();

            tick();
            repaint();

            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;

            if (wait < 0) {
                wait = 5;
            }

            try {
                Thread.sleep(wait);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void tick() {
        gsm.tick();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.clearRect(0,0,WIDTH,HEIGHT);

        if(gsm != null) {
            gsm.draw(g);
        }

    }

    public void keyPressed(KeyEvent e) {

        gsm.keyPressed(e.getKeyCode());

    }

    public void keyReleased(KeyEvent e) {

        gsm.keyReleased(e.getKeyCode());

    }

    public void keyTyped(KeyEvent e) {}

}
