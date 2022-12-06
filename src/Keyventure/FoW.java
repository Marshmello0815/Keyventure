package Keyventure;

import processing.core.PApplet;

public class FoW {
    private int radius = 42;

    Player player;


    public void draw(PApplet app){
        app.ellipse(player.getX()+player.getWidth()/2, player.getY()+player.getHeight()/2, 60, 60 );
    }
}
