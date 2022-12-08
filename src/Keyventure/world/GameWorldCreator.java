package Keyventure.world;

import Keyventure.*;

public class GameWorldCreator {
    private GameWorld gameWorld;

    public GameWorldCreator(GameWorld world) {
        this.gameWorld = world;//Perfekter Ort für Konstruktor-Dinge
    }

    public void initGameWorld (){
        Player player = new Player(300, 300);
        gameWorld.player = player;
        gameWorld.passiveObject.add(new Key(42, 42));
        gameWorld.passiveObject.add(new Door(142, 142));
        gameWorld.fow = new FoW(player);

        // Initiator
    }
}
