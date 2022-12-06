package Keyventure.world;

import Keyventure.*;
import processing.core.PApplet;
import processing.core.PConstants;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    List<PassiveObject> passiveObject;
    List<ActiveObject> activeObject;
    Player player;

    FoW fow;

    public GameWorld() {
        passiveObject = new ArrayList<>();
        activeObject = new ArrayList<>();
    }

    void addPassiveObject(PassiveObject passiveObject){
        this.passiveObject.add(passiveObject);
    }
    void addActiveObject(ActiveObject activeObject){
        this.activeObject.add(activeObject);
    }

    List<GameObject> allObjects(){
        List<GameObject> objects = new ArrayList<>();
        objects.addAll(passiveObject);
        objects.addAll(activeObject);
        objects.add(player);
        return objects;
    }

    public void draw(PApplet app){
        app.background(200, 200, 200);
        app.pushStyle();
        app.rect(20, 20, app.width-40, app.height-40);
        for(GameObject object : this.allObjects()){
            object.draw(app);
        }
        app.noFill();
        app.ellipse(player.getX()+player.getWidth()/2, player.getY()+player.getHeight()/2, 60, 60 );
        app.popStyle();

        //zeichnen uiiiiiiii
    }

    public void moveActiveObject(){
        //Monster Bewegungsmuster
    }

    public void playerUp(){
        this.player.up();
    }

    public void playerDown(){
        this.player.down();
    }

    public void playerLeft(){
        this.player.left();
    }

    public void playerRight(){
        this.player.right();
    }

}
