package Keyventure;

public class Player extends GameObject {
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);

    }

    public void up(){
        y += 1;
    }

    public void down(){
        y -= 1;
    }

    public void right(){
        x += 1;
    }

    public void left(){
        x -= 1;
    }

    public void move() {
        System.out.println("Move it");
    }

    public void draw() {
        System.out.println("Drawing PLayer ...");
    }

}