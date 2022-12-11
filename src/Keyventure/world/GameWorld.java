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
        app.beginShape();
        app.fill(0,0,0);
        app.vertex(0, 0);
        app.vertex(0,app.height);
        app.vertex(app.width, app.height);
        app.vertex(app.width, 0);
        app.beginContour();
        app.vertex(player.getX()+player.getWidth()/2-100, player.getY()+player.getHeight()/2-100);
        app.vertex(player.getX()+player.getWidth()/2+100,player.getY()+player.getHeight()/2-100);
        app.vertex(player.getX()+player.getWidth()/2+100, player.getY()+player.getHeight()/2+100);
        app.vertex(player.getX()+player.getWidth()/2-100, player.getY()+player.getHeight()/2+100);
        app.endContour();
        app.endShape();
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
