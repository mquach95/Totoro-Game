package Mapping;

import Entities.Player;
import Objects.Block;
import Objects.Chibi;
import Objects.Goal;
import Objects.MovingBlock;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Map {

    private String path;
    private String line;
    private int width, height;

    private Block[][] blocks;
    private ArrayList<MovingBlock> movingBlocks;
    private Chibi[] chibi;
    private Goal goal;

    Player player;

    public Map(String loadPath, Player player) {
        path = loadPath;
        loadMap();

        this.player = player;
    }

    public void draw(Graphics g) {
        for(int i = 0; i < blocks.length; i++) {
            for(int j = 0; j < blocks[0].length; j++) {
                blocks[i][j].draw(g);
            }
        }

        for(int i = 0; i < movingBlocks.size(); i++) {
            movingBlocks.get(i).draw(g);
        }

        for(int i = 0; i < chibi.length; i++) {
            chibi[i].draw(g);
        }

        goal.draw(g);
    }

    public void tick() {
        for(int i = 0; i < movingBlocks.size(); i++) {
            movingBlocks.get(i).tick();
        }
    }

    public void loadMap() {
        InputStream is = this.getClass().getResourceAsStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // Blocks

        try {
            width = Integer.parseInt(br.readLine());
            height = Integer.parseInt(br.readLine());

            blocks = new Block[height][width];

            for (int y = 0; y < height; y++){
                line = br.readLine();
                String[] tokens = line.split("\\s+");

                for(int x = 0; x < width; x++) {
                    blocks[y][x] = new Block(x*Block.blockSize, y*Block.blockSize, Integer.parseInt(tokens[x]));
                }
            }

            // Moving Blocks

            line = br.readLine();
            line = br.readLine();
            int length = Integer.parseInt(line);
            movingBlocks = new ArrayList<MovingBlock>();

            for(int i = 0; i < length; i++) {
                line = br.readLine();
                String[] tokens = line.split("\\s+");

                movingBlocks.add(new MovingBlock(Integer.parseInt(tokens[0]) * Block.blockSize,
                        Integer.parseInt(tokens[1]) * Block.blockSize,
                        Integer.parseInt(tokens[2]),
                        Integer.parseInt(tokens[3]) * Block.blockSize,
                        Integer.parseInt(tokens[4]) * Block.blockSize));
            }

            // Chibis
            line = br.readLine();
            line = br.readLine();

            int amt = Integer.parseInt(line);
            chibi = new Chibi[amt];

            for(int i = 0; i < amt; i++) {
                line = br.readLine();
                String[] tokens = line.split("\\s+");

                chibi[i] = new Chibi(Double.parseDouble(tokens[0]) * Block.blockSize,
                        Double.parseDouble(tokens[1]) * Block.blockSize,
                        amt,
                        true,
                        i,
                        player);

            }

            // Goal

            line = br.readLine();
            line = br.readLine();

            String[] tokens = line.split("\\s+");
            goal = new Goal(Double.parseDouble(tokens[0]) * Block.blockSize,
                    Double.parseDouble(tokens[1]) * Block.blockSize);

        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public ArrayList<MovingBlock> getMovingBlocks() {
        return movingBlocks;
    }

    public Chibi[] getChibi() { return chibi; }

    public Goal getGoal() { return goal; }
}
