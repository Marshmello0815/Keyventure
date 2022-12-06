package Keyventure;

public class Player extends GameObject {

    public Player(int x, int y) {
        super(x, y, 20, 35);

    }

    public void up(){
        y -= 3;
    }

    public void down(){
        y += 3;
    }

    public void right(){
        x += 3;
    }

    public void left(){
        x -= 3;
    }



}