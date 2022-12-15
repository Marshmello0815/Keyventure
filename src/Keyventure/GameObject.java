package Keyventure;

import processing.core.PApplet;

public abstract class GameObject {

    IGameWorld world;
    int x;
    int y;
    int width;
    int height;

    public GameObject(IGameWorld world, int x, int y, int width, int height) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean checkKollision(GameObject that){
        boolean kollisionX = this.getX() + this.getWidth() >= that.getX() && that.getX() + that.getWidth() >= this.getX();
        boolean kollisionY = this.getY() + this.getHeight() >= that.getY() && that.getY() + that.getHeight() >= this.getY();
        return kollisionX && kollisionY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(PApplet app) {
        app.rect(x, y, width, height);
    }

}
