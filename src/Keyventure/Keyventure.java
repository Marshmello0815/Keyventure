package Keyventure;

import Keyventure.world.Button;
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
    List<Button> mainMenuButtons;
    List<Button> controlsAndRulesButtons;
    List<Button> winLoseButtons;

    Button gameStartButton;
    Button controlsAndRulesButton;
    Button gameExitButton;
    Button gameRestartButton;
    Button backToMainMenuButton;
    Button rulesToMainMenuButton;

    boolean clicked = false;

    public Keyventure() {
        world = new GameWorld();
        creator = new GameWorldCreator(world);
        keyLastPressed = new ArrayList<>();
        keyPressedDevMode = new ArrayList<>();
        mainMenuButtons = new ArrayList<>();
        controlsAndRulesButtons = new ArrayList<>();
        winLoseButtons = new ArrayList<>();
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

        gameStartButton = new Button((float) (0.305 * displayWidth), (float) (0.3 * displayHeight), (float) (0.4 * displayWidth), (float) (0.08 * displayHeight), "Spiel starten", (float) (0.02 * displayWidth));
        controlsAndRulesButton = new Button((float) (0.305 * displayWidth), (float) (0.43 * displayHeight), (float) (0.4 * displayWidth), (float) (0.08 * displayHeight), "Steuerung und Regeln", (float) (0.016 * displayWidth));
        gameExitButton = new Button((float) (0.305 * displayWidth), (float) (0.56 * displayHeight), (float) (0.4 * displayWidth), (float) (0.08 * displayHeight), "Spiel Beenden", (float) (0.02 * displayWidth));
        mainMenuButtons.add(gameStartButton);
        mainMenuButtons.add(controlsAndRulesButton);
        mainMenuButtons.add(gameExitButton);
        if (world.getMainMenuButtons().size() == 0) {
            world.setMainMenuButtons(mainMenuButtons);
        }

        gameRestartButton = new Button((float) (0.305 * displayWidth), (float) (0.63 * displayHeight), (float) (0.4 * displayWidth), (float) (0.08 * displayHeight), "Spiel neustarten", (float) (0.02 * displayWidth));
        backToMainMenuButton = new Button((float) (0.305 * displayWidth), (float) (0.76 * displayHeight), (float) (0.4 * displayWidth), (float) (0.08 * displayHeight), "Zurück zum Hauptmenü", (float) (0.016 * displayWidth));
        winLoseButtons.add(gameRestartButton);
        winLoseButtons.add(backToMainMenuButton);
        if (world.getWinLoseButtons().size() == 0) {
            world.setWinLoseButtons(winLoseButtons);
        }

        rulesToMainMenuButton = new Button((float) (0.305 * displayWidth), (float) (0.8 * displayHeight), (float) (0.4 * displayWidth), (float) (0.08 * displayHeight), "Zurück zum Hauptmenü", (float) (0.016 * displayWidth));
        controlsAndRulesButtons.add(rulesToMainMenuButton);
        if (world.getControlsAndRulesButtons().size() == 0) {
            world.setControlsAndRulesButtons(controlsAndRulesButtons);
        }
    }

    @Override
    public void draw() {
        processInput();
        world.move(this);
        world.draw(this);
    }

    /**
     * Eingaben werden verarbeitet
     */
    private void processInput() {
        if(world.isMainMenu()) {
            if (isMouseOverButton(gameStartButton)) {
                if (clicked) {
                    gameStartButton.setPressed(true);
                }
                if (!clicked && gameStartButton.isPressed()) {
                    world.setMainMenu(false);
                    if (world.getPassiveObjects().size() == 0 && world.getActiveObjects().size() == 0) {
                        creator.initGameWorld();
                    }
                } else {
                    clicked = false;
                }
            } else {
                gameStartButton.setPressed(false);
            }

            if (isMouseOverButton(controlsAndRulesButton)) {
                if (clicked) {
                    controlsAndRulesButton.setPressed(true);
                }
                if (!clicked && controlsAndRulesButton.isPressed()) {
                    world.setControlsAndRules(true);
                } else {
                    clicked = false;
                }
            } else {
                controlsAndRulesButton.setPressed(false);
            }

            if (isMouseOverButton(gameExitButton)) {
                if (clicked) {
                    gameExitButton.setPressed(true);
                }
                if (!clicked && gameExitButton.isPressed()) {
                    exit();
                } else {
                    clicked = false;
                }
            } else {
                gameExitButton.setPressed(false);
            }
        }

        if(world.isGameWon() || world.isGameLose()) {
            if (isMouseOverButton(gameRestartButton)) {
                if (clicked) {
                    gameRestartButton.setPressed(true);
                }
                if (!clicked && gameRestartButton.isPressed()) {
                    world.restart();
                    creator.initGameWorld();
                } else {
                    clicked = false;
                }
            } else {
                gameRestartButton.setPressed(false);
            }

            if (isMouseOverButton(backToMainMenuButton)) {
                if (clicked) {
                    backToMainMenuButton.setPressed(true);
                }
                if (!clicked && backToMainMenuButton.isPressed()) {
                    world.restart();
                    world.setMainMenu(true);
                } else {
                    clicked = false;
                }
            } else {
                backToMainMenuButton.setPressed(false);
            }
        }

        if(world.isControlsAndRules()) {
            if (isMouseOverButton(rulesToMainMenuButton)) {
                if (clicked) {
                    rulesToMainMenuButton.setPressed(true);
                }
                if (!clicked && rulesToMainMenuButton.isPressed()) {
                    world.setControlsAndRules(false);
                } else {
                    clicked = false;
                }
            } else {
                rulesToMainMenuButton.setPressed(false);
            }
        }

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

    public boolean isMouseOverButton(Button button) {
        boolean mouseOverButton;
        mouseOverButton = (mouseX >= button.getX() && mouseX <= button.getX() + button.getWidth()) && (mouseY >= button.getY() && mouseY <= button.getY() + button.getHeight());
        return mouseOverButton;
    }

    public void keyPressed(processing.event.KeyEvent event) {
        KeyEvent nativeEvent = (KeyEvent) event.getNative();

        if (keyCode == VK_UP || keyCode == VK_W || keyCode == VK_LEFT || keyCode == VK_A || keyCode == VK_DOWN || keyCode == VK_S || keyCode == VK_RIGHT || keyCode == VK_D) {
            keyLastPressed.add(0, keyCode);
        }

        if (keyCode == VK_CONTROL && nativeEvent.getKeyLocation() == KEY_LOCATION_LEFT || keyCode == VK_F1) {
            if (!keyPressedDevMode.contains(keyCode)) {
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

    public void mousePressed() {
        clicked = mouseButton == LEFT;
    }

    public void mouseReleased() {
        clicked = false;
    }
}

