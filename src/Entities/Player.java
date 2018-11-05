package Entities;

import GameState.GameState;
import Main.GamePanel;
import Objects.*;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player {

    // Player Stuffs

    private int health;
    private int points;
    private boolean dead;
    private boolean win;

    private double x, y;
    private double initialX, initialY;
    private int width, height;

    private boolean idle = false;
    private boolean walking = false;
    private boolean right = false;
    private boolean left = false;
    private boolean jumping = false;
    private boolean falling = false;
    private boolean topCollision = false;

    private ArrayList<BufferedImage[]> sprites = new ArrayList<BufferedImage[]>();

    private static final int IDLE = 0;
    private static final int WALKING = 1;

    int currentAction;
    private boolean isRight = true;

    private double moveSpeed = 2.5;

    private double jumpSpeed = 5.5;
    private double currentJumpSpeed = jumpSpeed;

    private double maxFallSpeed = 5;
    private double currentFallSpeed = 0.1;

    Animation animation;

    public Player() {
        x = GamePanel.WIDTH/4;
        y = 200;
        initialX = x;
        initialY = y;
        width = 35;
        height = 45;
        health = 3;
        points = 0;
        dead = false;
        win = false;

        sprites.add(Images.totoroIdle);
        sprites.add(Images.totoroWalk);

        animation = new Animation();
        currentAction = IDLE;
        animation.setFrames(sprites.get(IDLE));
        animation.setDelay(400);


    }

    public int getHealth() {
        return health;
    }

    public int getPoints() { return points; }

    public boolean getDeath() { return dead; }

    public boolean getWin() { return win; }

    public void respawn() {
        GameState.xOffset = 0;
        x = initialX-GameState.xOffset;
        y = initialY;
    }

    public void tick(Block[][] b, ArrayList<MovingBlock> movingBlocks, Chibi[] c, Goal goal) {

        int iX = (int)x;
        int iY = (int)y;

        // Block

        for(int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {

                if(b[i][j].getID() != 0) {

                    // Right
                    if (Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset, iY + height + (int) GameState.yOffset - 1), b[i][j])) {
                        right = false;
                    }

                    // Left
                    if (Collision.playerBlock(new Point(iX + (int) GameState.xOffset - 1, iY + (int) GameState.yOffset + 2), b[i][j])
                            || Collision.playerBlock(new Point(iX + (int) GameState.xOffset - 1, iY + height + (int) GameState.yOffset - 1), b[i][j])) {
                        left = false;
                    }

                    // Top
                    if (Collision.playerBlock(new Point(iX + (int) GameState.xOffset + 1, iY + (int) GameState.yOffset + 10), b[i][j])
                            || Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset - 2, iY + (int) GameState.yOffset + 10), b[i][j])) {
                        jumping = false;
                        falling = true;
                    }

                    // Bottom
                    if (Collision.playerBlock(new Point(iX + (int) GameState.xOffset + 2, iY + height + (int) GameState.yOffset + 1), b[i][j])
                            || Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset - 2, iY + height + (int) GameState.yOffset + 1), b[i][j])) {
                        y = b[i][j].getY() - height - GameState.yOffset;
                        falling = false;
                        topCollision = true;
                    } else {
                        if (!topCollision && !jumping) {
                            falling = true;
                        }
                    }
                }
            }
        }

        // Moving Block

        for(int i = 0; i < movingBlocks.size(); i++) {
            if(movingBlocks.get(i).getID() != 0) {
                // Right
                if (Collision.playerMovingBlock(new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset + 2), movingBlocks.get(i))
                        || Collision.playerMovingBlock(new Point(iX + width + (int) GameState.xOffset, iY + height + (int) GameState.yOffset - 1), movingBlocks.get(i))) {
                    right = false;
                }

                // Left
                if (Collision.playerMovingBlock(new Point(iX + (int) GameState.xOffset - 1, iY + (int) GameState.yOffset + 2), movingBlocks.get(i))
                        || Collision.playerMovingBlock(new Point(iX + (int) GameState.xOffset - 1, iY + height + (int) GameState.yOffset - 1), movingBlocks.get(i))) {
                    left = false;
                }

                // Top
                if (Collision.playerMovingBlock(new Point(iX + (int) GameState.xOffset + 1, iY + (int) GameState.yOffset), movingBlocks.get(i))
                        || Collision.playerMovingBlock(new Point(iX + width + (int) GameState.xOffset - 2, iY + (int) GameState.yOffset), movingBlocks.get(i))) {
                    jumping = false;
                    falling = true;
                }

                // Bottom
                if (Collision.playerMovingBlock(new Point(iX + (int) GameState.xOffset + 2, iY + height + (int) GameState.yOffset + 1), movingBlocks.get(i))
                        || Collision.playerMovingBlock(new Point(iX + width + (int) GameState.xOffset - 2, iY + height + (int) GameState.yOffset + 1), movingBlocks.get(i))) {
                    y = movingBlocks.get(i).getY() - height - GameState.yOffset;
                    falling = false;
                    topCollision = true;

                    GameState.xOffset += movingBlocks.get(i).getMove();

                } else {
                    if (!topCollision && !jumping) {
                        falling = true;
                    }
                }
            }
        }

        topCollision = false;

        // Chibi

        for(int i = 0; i < c.length; i++) {
            // Right
            if ((Collision.playerChibi(new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset + 2), c[i])
                    || Collision.playerChibi(new Point(iX + width + (int) GameState.xOffset, iY + height + (int) GameState.yOffset - 1), c[i]))
                    && c[i].isExist) {
                points++;
                c[i].isExist = false;
            }

            // Left
            if ((Collision.playerChibi(new Point(iX + (int) GameState.xOffset - 1, iY + (int) GameState.yOffset + 2), c[i])
                    || Collision.playerChibi(new Point(iX + (int) GameState.xOffset - 1, iY + height + (int) GameState.yOffset - 1), c[i]))
                    && c[i].isExist) {
                points++;
                c[i].isExist = false;
            }

            // Top
            if ((Collision.playerChibi(new Point(iX + (int) GameState.xOffset + 1, iY + (int) GameState.yOffset), c[i])
                    || Collision.playerChibi(new Point(iX + width + (int) GameState.xOffset - 2, iY + (int) GameState.yOffset), c[i]))
                    && c[i].isExist) {
                points++;
                c[i].isExist = false;
            }

            // Bottom
            if ((Collision.playerChibi(new Point(iX + (int) GameState.xOffset + 2, iY + height + (int) GameState.yOffset + 1), c[i])
                    || Collision.playerChibi(new Point(iX + width + (int) GameState.xOffset - 2, iY + height + (int) GameState.yOffset + 1), c[i]))
                    && c[i].isExist) {
                points++;
                c[i].isExist = false;
            }
        }

        // Goal
        // Right
        if ((Collision.playerGoal(new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset + 2), goal)
                || Collision.playerGoal(new Point(iX + width + (int) GameState.xOffset, iY + height + (int) GameState.yOffset - 1), goal))) {
            right = false;
            if(points == c.length) {
                win = true;
            }
        }

        // Left
        if ((Collision.playerGoal(new Point(iX + (int) GameState.xOffset - 1, iY + (int) GameState.yOffset + 2), goal)
                || Collision.playerGoal(new Point(iX + (int) GameState.xOffset - 1, iY + height + (int) GameState.yOffset - 1), goal))) {
            left = false;
        }

        /*// Top
        if ((Collision.playerGoal(new Point(iX + (int) GameState.xOffset + 1, iY + (int) GameState.yOffset), goal)
                || Collision.playerGoal(new Point(iX + width + (int) GameState.xOffset - 2, iY + (int) GameState.yOffset), goal))) {
            jumping = false;
            falling = true;
        }*/

        // Movement

        if(x < 0) {
            x = 0;
        }

        if(right) {
            if(x >= GamePanel.WIDTH/2 && GameState.xOffset >= 0 && GameState.xOffset <= 1495) {
                GameState.xOffset += moveSpeed;
            } else if(GameState.xOffset < 0) {
                GameState.xOffset = 0;
            } else {
                    x += moveSpeed;
            }
        }

        if(left) {
            if(x >= GamePanel.WIDTH/2 && GameState.xOffset >= 0 && GameState.xOffset <= 1495) {
                GameState.xOffset -= moveSpeed;
            } else if(GameState.xOffset > 1495) {
                GameState.xOffset = 1495;
                x -= moveSpeed;
            } else {
                x -= moveSpeed;
            }
        }

        System.out.println(x + " " + GameState.xOffset);

        if(jumping) {
            y -= currentJumpSpeed;

            currentJumpSpeed -= .1;

            if(currentJumpSpeed <= 0) {
                currentJumpSpeed = jumpSpeed;
                jumping = false;
                falling = true;
            }
        }

        if(falling) {
            y += currentFallSpeed;

            if(currentFallSpeed < maxFallSpeed) {
                currentFallSpeed += .1;
            }
        }

        if(!falling) {
            currentFallSpeed = .1;
        }

        // is Dead
        if(y > GamePanel.HEIGHT) {
            health--;
            if(health > 0) {
                respawn();
            } else {
                health = 0;
                dead = true;
            }
        }

        // Animations
        if(left || right) {
            if(currentAction != WALKING) {
                currentAction = WALKING;
                animation.setFrames(sprites.get(WALKING));
                animation.setDelay(150);
            }
        } else {
            if(currentAction != IDLE) {
                currentAction = IDLE;
                animation.setFrames(sprites.get(IDLE));
                animation.setDelay(400);
            }
        }
        animation.update();
        if(right) isRight = true;
        if(left) isRight = false;
        System.out.println(right + " " + left);

    }

    public void draw(Graphics g) {

        if(isRight) {
            g.drawImage(animation.getImage(),
                (int)x,
                (int)y,
                width,
                height,
                null
            );
        } else {
            g.drawImage(animation.getImage(),
                    (int)x + width,
                    (int)y,
                    -width,
                    height,
                    null
            );
        }
    }

    public void keyPressed(int k) {
        if(k == KeyEvent.VK_D) {
            if(x <= 865) {
                right = true;
            } else {
                right = false;
            }
        }
        if(k == KeyEvent.VK_A) {
            if(x >= 5) {
                left = true;
            } else
                {
                left = false;
            }
        }
        if(k == KeyEvent.VK_SPACE && !jumping && !falling) jumping = true;
    }

    public void keyReleased(int k) {
        if(k == KeyEvent.VK_D) right = false;
        if(k == KeyEvent.VK_A) left = false;
    }

}
