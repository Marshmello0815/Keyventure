package Keyventure;

import Keyventure.world.GameWorld;
import Keyventure.world.GameWorldCreator;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import static java.awt.event.KeyEvent.*;

public class Keyventure extends PApplet {

    GameWorld world;
    GameWorldCreator creator;
    List<Integer> keyLastPressed;

    public Keyventure() {
        world = new GameWorld();
        creator = new GameWorldCreator(world);
        creator.initGameWorld();
        keyLastPressed = new ArrayList<>();
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
        if (keyLastPressed.size() > 0) {
            if (keyLastPressed.get(0) == VK_UP || keyLastPressed.get(0) == VK_W) {
                world.playerUp();
            }
            if (keyLastPressed.get(0) == VK_LEFT || keyLastPressed.get(0) == VK_A) {
                world.playerLeft();
            }
            if (keyLastPressed.get(0) == VK_DOWN || keyLastPressed.get(0) == VK_S) {
                world.playerDown();
            }
            if (keyLastPressed.get(0) == VK_RIGHT || keyLastPressed.get(0) == VK_D) {
                world.playerRight();
            }
        }
    }

    public void keyPressed() {
        if (keyCode == VK_UP || keyCode == VK_W || keyCode == VK_LEFT || keyCode == VK_A || keyCode == VK_DOWN || keyCode == VK_S || keyCode == VK_RIGHT || keyCode == VK_D) {
            if (keyLastPressed.size() > 0) {
                keyLastPressed.remove(0);
            }
            keyLastPressed.add(keyCode);
        }
    }

    public void keyReleased() {
        if (keyCode == VK_UP || keyCode == VK_W || keyCode == VK_LEFT || keyCode == VK_A || keyCode == VK_DOWN || keyCode == VK_S || keyCode == VK_RIGHT || keyCode == VK_D) {
            keyLastPressed.removeIf(keytime -> keytime == keyCode);
        }
    }
}

