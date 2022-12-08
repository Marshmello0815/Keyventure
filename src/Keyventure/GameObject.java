package Keyventure;

import processing.core.PApplet;

public class GameObject {
    int x;
    int y;
    int width;
    int height;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public GameObject() {
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
