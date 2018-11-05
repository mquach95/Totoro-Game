package Main;

import javax.swing.*;

public class Game {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                createAndShowGUI();
            }
        } );
    }

    public static void createAndShowGUI() {

        JFrame window = new JFrame("Totoro's Adventure");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }

}
