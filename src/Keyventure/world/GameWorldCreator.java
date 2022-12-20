package Keyventure.world;

import Keyventure.*;

public class GameWorldCreator {
    private int gameWidth;
    private int gameHeight;
    private GameWorld gameWorld;

    public GameWorldCreator(GameWorld world) {
        this.gameWorld = world;//Perfekter Ort für Konstruktor-Dinge
    }

    public void initGameWorld (){
        Player player = new Player(gameWorld, 300, 300);
        gameWorld.player = player;

        /*gameWorld.passiveObject.add(new Wall(gameWorld, 1, 1));
        gameWorld.passiveObject.add(new Wall(gameWorld, 1, 1));
        gameWorld.passiveObject.add(new Wall(gameWorld, 1, 1));
        gameWorld.passiveObject.add(new Wall(gameWorld, 1, 1));
        gameWorld.passiveObject.add(new Wall(gameWorld, 1, 1));
        */
        int wallSize = 40;
        //gameWorld.passiveObject.add(new Wall(gameWorld, 0, 1, wallSize));
        int amountWallBlocks = gameWidth/wallSize;

        for(int i = 0; i <=gameWidth/wallSize; i++){
            gameWorld.passiveObject.add(new Wall(gameWorld, i+(i*wallSize), 0, wallSize));
            gameWorld.passiveObject.add(new Wall(gameWorld, i+(i*wallSize), gameHeight-wallSize, wallSize));
        }
        for(int i = 0; i <=gameHeight/wallSize; i++){
            gameWorld.passiveObject.add(new Wall(gameWorld, 0, i+(i*wallSize), wallSize));
            gameWorld.passiveObject.add(new Wall(gameWorld, gameWidth-wallSize, i+(i*wallSize), wallSize));
        }



        gameWorld.passiveObject.add(new Key(gameWorld, 42, 42));
        gameWorld.passiveObject.add(new Door(gameWorld,142, 142));
        gameWorld.fow = new FoW(player);

        // Initiator
    }

    public void setEnv(int width, int height) {
        this.gameWidth = width;
        this.gameHeight = height;
    }
}
