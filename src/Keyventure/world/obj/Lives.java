package Keyventure.world.obj;

import Keyventure.world.IGameWorld;
import processing.core.PApplet;
import processing.core.PImage;

public class Lives extends GameObject {

    PImage healthFull = null;
    PImage healthEmpty = null;

    int lives;
    int lostLives;


    public Lives(IGameWorld world, int x, int y, int width, int height) {
        super(world, x, y, width, height);
        lives = 3;
        lostLives = 0;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLostLives() {
        return lostLives;
    }

    public void setLostLives(int lostLives) {
        this.lostLives = lostLives;
    }

    @Override
    public void draw(PApplet app) {
        if (healthFull == null) {
            healthFull = app.loadImage("/resource/health-full.png");
        }
        if (healthEmpty == null) {
            healthEmpty = app.loadImage("/resource/health-empty.png");
        }
        for (int i = 0; i < lives; i++) {
            app.image(healthFull, x + i * width, y, width, height);
        }
        for (int i = lives; i < lives + lostLives; i++) {
            app.image(healthEmpty, x + i * width, y, width, height);
        }
    }
}
