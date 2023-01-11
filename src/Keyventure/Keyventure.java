package Keyventure;

import Keyventure.world.GameWorld;
import Keyventure.world.GameWorldCreator;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

/**
 * Keyventure
 *
 * @author Louisa Veeck, Serkan Erdogan
 */

public class Keyventure extends PApplet {

    GameWorld world;
    GameWorldCreator creator;
    List<Integer> keyLastPressed;
    List<Integer> keyPressedDevMode;


    public Keyventure() {
        world = new GameWorld();
        creator = new GameWorldCreator(world);
        keyLastPressed = new ArrayList<>();
        keyPressedDevMode = new ArrayList<>();
    }

    @Override
    public void setup() {
        surface.setResizable(true);
    }

    @Override
    public void settings() {
        width = displayWidth;
        height = displayHeight;
        size(width, height - 120);
        creator.setEnv(width, height);
        creator.initGameWorld();
    }

    @Override
    public void draw() {
        processInput();
        world.move(this);
        world.draw(this);
    }

    /**
     * Bewegung des Spielers nach oben/links/unten/rechts in Abhängigkeit der zuletzt gedrückten Taste (w, a, s, d bzw. Pfeiltaste oben, links, unten, rechts)
     */
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

        if (keyPressedDevMode.size() == 2) {
            world.devMode = true;
        }
    }

    public void keyPressed(processing.event.KeyEvent event) {
        KeyEvent nativeEvent = (KeyEvent) event.getNative();

        if (keyCode == VK_UP || keyCode == VK_W || keyCode == VK_LEFT || keyCode == VK_A || keyCode == VK_DOWN || keyCode == VK_S || keyCode == VK_RIGHT || keyCode == VK_D) {
            keyLastPressed.add(0, keyCode);
        }

        if (keyCode == VK_CONTROL && nativeEvent.getKeyLocation() == KEY_LOCATION_LEFT || keyCode == VK_F1) {
            if(!keyPressedDevMode.contains(keyCode)) {
                keyPressedDevMode.add(keyCode);
            }
        }

        if (keyCode == VK_SHIFT && nativeEvent.getKeyLocation() == KEY_LOCATION_LEFT) {
            world.playerRun();
        }

        if (world.isDevMode()) {
            if (keyCode == VK_F1 && keyPressedDevMode.size() == 1) {
                world.devMode = false;
            }

            if (keyCode == VK_F2) {
                world.setFowOn(!world.isFowOn());
            }
        }
    }

    public void keyReleased(processing.event.KeyEvent event) {
        KeyEvent nativeEvent = (KeyEvent) event.getNative();

        if (keyCode == VK_UP || keyCode == VK_W || keyCode == VK_LEFT || keyCode == VK_A || keyCode == VK_DOWN || keyCode == VK_S || keyCode == VK_RIGHT || keyCode == VK_D) {
            keyLastPressed.removeIf(key -> key == keyCode);
        }

        if (keyCode == VK_CONTROL || keyCode == VK_F1) {
            keyPressedDevMode.removeIf(key -> key == keyCode);
        }

        if (keyCode == VK_SHIFT && nativeEvent.getKeyLocation() == KEY_LOCATION_LEFT) {
            world.playerWalk();
        }
    }
}

