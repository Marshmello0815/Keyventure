package Keyventure;

import processing.core.PApplet;
import Keyventure.world.GameWorld;
import Keyventure.world.GameWorldCreator;

public class Game extends PApplet {

    GameWorld world;
    GameWorldCreator creator;

    public Game() {
        world = new GameWorld();
        creator = new GameWorldCreator(world);
        creator.initGameWorld();
    }

    @Override
    public void settings() {
        width = 600;
        height = 600;
    }

    @Override
    public void draw() {
        processInput();
        world.move();
        world.draw(this);
    }

    private void processInput() {
        if (keyPressed) {
            if (key == 'w' || keyCode == UP) {
                world.playerUp();
            }
            if (key == 'a' || keyCode == LEFT) {
                world.playerLeft();
            }
            if (key == 's' || keyCode == DOWN) {
                world.playerDown();
            }

            if (key == 'd' || keyCode == RIGHT) {
                world.playerRight();
            }
        }
    }


}
