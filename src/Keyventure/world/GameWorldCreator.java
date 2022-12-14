package Keyventure.world;

import Keyventure.world.obj.*;

import java.util.Random;

public class GameWorldCreator {
    public static final int MONSTER_COUNT = 8;
    private int gameWidth;
    private int gameHeight;
    private GameWorld gameWorld;
    Random random = new Random();


    public GameWorldCreator(GameWorld world) {
        this.gameWorld = world;//Perfekter Ort für Konstruktor-Dinge
    }

    /**
     * Initiiert das Spiel (Fügt alle Objekte dem Spiel hinzu)
     */
    public void initGameWorld() {
        int wallSize = gameWidth/48;
        Player player = new Player(gameWorld, wallSize * 25 + wallSize / 4, gameHeight - wallSize);
        gameWorld.player = player;


        int numWallX = gameWidth / wallSize;
        int numWallY = gameHeight / wallSize;

        for (int i = 0; i <= numWallX; i++) {
            for (int j = 0; j <= numWallY; j++) {
                for (int k = 0; k < 4; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += numWallX / 4 - 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 15)) {
                        j += numWallY - 15;
                    }
                    if (i == 3 && j == 0) {
                        j++;
                    }
                }
                for (int k = 4; k < 6; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += numWallX / 4 - 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 20)) {
                        j += numWallY - 20;
                    }
                }
                for (int k = 6; k < 8; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += numWallY - 1;
                    }
                }
                for (int k = 8; k < 10; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += numWallX / 4 - 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 15)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 20)) {
                        j += numWallY - 20;
                    }
                }
                for (int k = 10; k < 12; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 9)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 15)) {
                        j += numWallY - 15;
                    }
                }
                for (int k = 12; k < 14; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += numWallX / 4;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 15)) {
                        j += numWallY - 15;
                    }
                }
                for (int k = 14; k < 23; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += numWallX / 8;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 11)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 15)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 19)) {
                        j += numWallY - 19;
                    }
                }
                for (int k = 23; k < 24; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += numWallX / 8;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 11)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 15)) {
                        j += 2;
                    }
                }
                for (int k = 24; k < 26; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 5)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 11)) {
                        j += numWallY - 10;
                    }
                }
                for (int k = 26; k < 28; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 5)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 13)) {
                        j += 2;
                    }
                }
                for (int k = 28; k < 30; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += numWallY - 1;
                    }
                }
                for (int k = 30; k < 34; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 7)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 18)) {
                        j += numWallY - 18;
                    }
                }
                for (int k = 34; k < 36; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += 4;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 7)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 11)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 18)) {
                        j += numWallY - 18;
                    }
                }
                for (int k = 36; k < 39; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 7)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 11)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 18)) {
                        j += numWallY - 18;
                    }
                }
                for (int k = 39; k < 42; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += 8;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 11)) {
                        j += 2;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 18)) {
                        j += numWallY - 18;
                    }
                }
                for (int k = 42; k < 46; k++) {
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 1)) {
                        j += 8;
                    }
                    if (i == numWallX - (numWallX - (1 + k)) && j == numWallY - (numWallY - 11)) {
                        j += numWallY - 11;
                    }
                }
                gameWorld.passiveObject.add(new Wall(gameWorld, wallSize * i, wallSize * j, wallSize));
            }
        }

        gameWorld.passiveObject.add(new Key(gameWorld, (numWallX - 44) * wallSize - wallSize / 3, (numWallY - (numWallY - 16)) * wallSize));
        gameWorld.passiveObject.add(new Weapon(gameWorld, (numWallX - 11) * wallSize - wallSize / 3, (numWallY - (numWallY - 11)) * wallSize));
        gameWorld.addPassiveObject(new Door(gameWorld, wallSize * 3, 0, wallSize));
        gameWorld.fow = new FoW(gameWorld, player);
        gameWorld.lives = new Lives(gameWorld, 0, 0);
        for (int i = 0; i < MONSTER_COUNT; i++) {
            boolean kollision = true;
            boolean noKollisionCheck = true;
            int xPos = random.nextInt(gameWidth);
            int yPos = random.nextInt(gameHeight);
            Monster monster = new Monster(gameWorld, xPos, yPos);
            while (kollision) {
                for (GameObject object : gameWorld.allObjects()) {
                    if (monster.checkKollision(object)) {
                        monster.setX(object.getX() + object.getWidth() + 1);
                        monster.setY(object.getY() + object.getHeight() + 1);
                        if (monster.getX() > gameWidth || monster.getY() > gameHeight) {
                            monster.setX(gameWidth / 10);
                            monster.setY(gameHeight / 10);
                        }
                    }
                }
                for (GameObject object : gameWorld.allObjects()) {
                    if (monster.checkKollision(object)) {
                        noKollisionCheck = false;
                    }
                }
                if (noKollisionCheck) {
                    kollision = false;
                }
            }
            gameWorld.addActiveObject(monster);

        }
        // Initiator
    }

    /**
     * Setzt spielfeldhöhe und Spielfeldbreite
     *
     * @param width  Spielfeldbreite
     * @param height Spielfeldhöhe
     */
    public void setEnv(int width, int height) {
        this.gameWidth = width;
        this.gameHeight = height;
    }

    /**
     * Gibt die Spielfeldbreite zurück
     *
     * @return Spielfeldbreite
     */
    public int getGameWidth() {
        return gameWidth;
    }

    /**
     * Gibt die Spielfeldhöhe zurück
     *
     * @return Spielfeldhöhe
     */
    public int getGameHeight() {
        return gameHeight;
    }

}
