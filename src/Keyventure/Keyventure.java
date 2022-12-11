package Keyventure;

import Keyventure.world.GameWorld;
import Keyventure.world.GameWorldCreator;
import processing.core.PApplet;

import static java.awt.event.KeyEvent.*;

public class Keyventure extends PApplet {

    GameWorld world;
    GameWorldCreator creator;
    boolean up, left, down, right;
    boolean[] keys;

    public Keyventure() {
        world = new GameWorld();
        creator = new GameWorldCreator(world);
        keys = new boolean[65535];
        creator.initGameWorld();
    }

    @Override
    public void settings() {
        width = displayWidth;
        height = displayHeight;
    }

    @Override
    public void draw() {
        processInput();
        world.draw(this);
    }

    private void processInput() {
        if (up) {
            world.playerUp();
        }
        if (left) {
            world.playerLeft();
        }
        if (down) {
            world.playerDown();
        }
        if (right) {
            world.playerRight();
        }
    }

    public void keyPressed() {
        keys[keyCode] = true;
        if (!left && !down && !right) {
            if (keys[VK_W] || keys[VK_UP]) {
                up = true;
            }
        }
        if (!up && !down && !right) {
            if (keys[VK_A] || keys[VK_LEFT]) {
                left = true;
            }
        }
        if (!left && !up && !right) {
            if (keys[VK_S] || keys[VK_DOWN]) {
                down = true;
            }
        }
        if (!left && !down && !up) {
            if (keys[VK_D] || keys[VK_RIGHT]) {
                right = true;
            }
        }
        if (!keys[VK_W] && !keys[VK_UP] && !keys[VK_A] && !keys[VK_LEFT] && !keys[VK_S] && !keys[VK_DOWN] && !keys[VK_D] && !keys[VK_RIGHT]) {
            up = false;
            left = false;
            down = false;
            right = false;
        }
    }

    public void keyReleased() {
        keys[keyCode] = false;
        if (!left && !down && !right) {
            if (!keys[VK_W] && !keys[VK_UP]) {
                up = false;
            }
        }
        if (!up && !down && !right) {
            if (!keys[VK_A] && !keys[VK_LEFT]) {
                left = false;
            }
        }
        if (!left && !up && !right) {
            if (!keys[VK_S] && !keys[VK_DOWN]) {
                down = false;
            }
        }
        if (!left && !down && !up) {
            if (!keys[VK_D] && !keys[VK_RIGHT]) {
                right = false;
            }
        }
        if (!keys[VK_W] && !keys[VK_UP] && !keys[VK_A] && !keys[VK_LEFT] && !keys[VK_S] && !keys[VK_DOWN] && !keys[VK_D] && !keys[VK_RIGHT]) {
            up = false;
            left = false;
            down = false;
            right = false;
        }
    }

}

