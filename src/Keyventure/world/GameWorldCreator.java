package Keyventure.world;

import Keyventure.*;

public class GameWorldCreator {
    private GameWorld gameWorld;

    public GameWorldCreator(GameWorld world) {
        this.gameWorld = world;//Perfekter Ort für Konstruktor-Dinge
    }

    public void initGameWorld (){
        gameWorld.player = new Player(300, 300);
        gameWorld.passiveObject.add(new Key(42, 42));


        // Initiator
    }
}
