package Keyventure.world;

import Keyventure.world.obj.*;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameWorld implements IGameWorld {
    List<PassiveObject> passiveObject;
    List<ActiveObject> activeObject;
    public Player player;
    FoW fow;
    Lives lives;

    Random random = new Random();
    public boolean devMode = false;
    public boolean fowOn = true;

    boolean hasKey = false;
    boolean gameWon = false;
    boolean gameLose = false;
    boolean changeDirectionPossible = false;

    int countKollisionWithMonster = 0;
    int invulnerableTime = 90;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    public GameWorld() {
        passiveObject = new ArrayList<>();
        activeObject = new ArrayList<>();
    }

    /**
     * Fügt der Liste von passiven Objekten ein passives Objekt hinzu
     *
     * @param passiveObject das hinzuzufügende passive Objekt
     */
    public void addPassiveObject(PassiveObject passiveObject) {
        this.passiveObject.add(passiveObject);
    }

    /**
     * Fügt der Liste von aktiven Objekten ein aktives Objekt hinzu
     *
     * @param activeObject das hinzuzufügende aktive Objekt
     */
    public void addActiveObject(ActiveObject activeObject) {
        this.activeObject.add(activeObject);
    }

    /**
     * Gibt alle aktiven Objekte in Form einer nicht modifizierbaren Liste zurück
     *
     * @return unmodifizierbare Liste aktiver Objekte
     */
    public List<ActiveObject> getActiveObject() {
        return Collections.unmodifiableList(this.activeObject);
    }

    /**
     * Gibt alle passiven Objekte in Form einer nicht modifizierbaren Liste zurück
     *
     * @return unmodifizierbare Liste passiver Objekte
     */
    public List<PassiveObject> getPassiveObject() {
        return Collections.unmodifiableList(this.passiveObject);
    }

    /**
     * Erstellt eine Liste aller Objekte des Spiels und gibt diese anschließend zurück
     *
     * @return Liste aller Objekte des Spiels
     */
    List<GameObject> allObjects() {
        List<GameObject> objects = new ArrayList<>();
        objects.addAll(passiveObject);
        objects.addAll(activeObject);
        objects.add(player);
        objects.add(lives);
        return objects;
    }


    /**
     * Zeichnet Hintergrund und alle Objekte des Spiels und prüft darüber hinaus alle möglichen Kollisionen
     *
     * @param app Übergabe der Klasse PApplet zur Benutzung der Methoden zum Zeichnen
     */
    public void draw(PApplet app) {

        if (!gameLose && !gameWon) {
            app.background(0, 0, 0);
            for (GameObject object : this.allObjects()) {
                object.draw(app);
            }

            fow.draw(app);
            lives.draw(app);

            for (PassiveObject pObject : this.passiveObject) {
                if (this.player.checkKollision(pObject)) {
                    pObject.kollisionWithPlayer();
                }
            }

            for (ActiveObject aObject : this.activeObject) {
                if (this.player.checkKollision(aObject)) {
                    if (countKollisionWithMonster == 0 && lives.getLives() > 0) {
                        lives.setLives(lives.getLives() - 1);
                        lives.setLostLives(lives.getLostLives() + 1);
                        makePlayerInvisible();
                        countKollisionWithMonster += 1;
                    }
                    if (lives.getLives() == 0) {
                        gameLose = true;
                    }
                }
            }

            if (countKollisionWithMonster > 0) {
                countKollisionWithMonster += 1;
                if (countKollisionWithMonster == invulnerableTime) {
                    countKollisionWithMonster = 0;
                }
            }
        }

        if (gameLose) {
            app.pushStyle();
            app.fill(0, 0, 0);
            app.rect(0, 0, app.displayWidth, app.displayHeight);
            app.fill(120, 120, 120);
            app.text("Game Over", (float) (app.displayWidth/10), (float) app.displayHeight/2);
            app.textSize(300);
            app.popStyle();
        }

        if (gameWon) {

            if (app.millis() / 1000 % 8 == 0) {
                app.pushStyle();
                app.fill(200, 30, 30);
                app.rect(0, 0, app.displayWidth, app.displayHeight);
                app.popStyle();
            }
            if (app.millis() / 1000 % 8 == 1) {
                app.pushStyle();
                app.fill(200, 150, 30);
                app.rect(0, 0, app.displayWidth, app.displayHeight);
                app.popStyle();
            }
            if (app.millis() / 1000 % 8 == 2) {
                app.pushStyle();
                app.fill(240, 240, 20);
                app.rect(0, 0, app.displayWidth, app.displayHeight);
                app.popStyle();
            }
            if (app.millis() / 1000 % 8 == 3) {
                app.pushStyle();
                app.fill(30, 240, 30);
                app.rect(0, 0, app.displayWidth, app.displayHeight);
                app.popStyle();
            }
            if (app.millis() / 1000 % 8 == 4) {
                app.pushStyle();
                app.fill(20, 240, 240);
                app.rect(0, 0, app.displayWidth, app.displayHeight);
                app.popStyle();
            }
            if (app.millis() / 1000 % 8 == 5) {
                app.pushStyle();
                app.fill(30, 30, 240);
                app.rect(0, 0, app.displayWidth, app.displayHeight);
                app.popStyle();
            }
            if (app.millis() / 1000 % 8 == 6) {
                app.pushStyle();
                app.fill(140, 20, 240);
                app.rect(0, 0, app.displayWidth, app.displayHeight);
                app.popStyle();
            }
            if (app.millis() / 1000 % 8 == 7) {
                app.pushStyle();
                app.fill(240, 20, 240);
                app.rect(0, 0, app.displayWidth, app.displayHeight);
                app.popStyle();
            }
            app.pushStyle();
            app.fill(120, 120, 120);
            app.text("Game Won", (float) (app.displayWidth / 10), (float) app.displayHeight / 2);
            app.textSize(300);
            app.popStyle();


        }
    }

    /**
     * Macht den Spieler unsichtbar
     */
    @Override
    public void makePlayerInvisible() {
        this.player.makeInvisible();
    }

    /**
     * Bewegung aller aktiven Objekte und Änderung der Bewegungsrichtung der Monster alle 3 Sekunden
     *
     * @param app Übergabe der Klasse PApplet zur Benutzung der Methoden um die Zeit seit Spielbeginn zu erhalten
     */
    public void move(PApplet app) {
        moveMonster();
        if ((app.millis() / 1000) % 3 == 0) {
            if (changeDirectionPossible) {
                changeDirectionPossible = false;
                for (ActiveObject aObject : this.activeObject) {
                    if (aObject instanceof Monster) {
                        aObject.changeDirection();
                    }
                }
            }
        } else {
            changeDirectionPossible = true;
        }
    }

    /**
     * Entfernen des Schlüssels aus der Liste passiver Objekten und setzen des Parameters "hasKey" auf "true"
     *
     * @param key Der Schlüssel der aus der Liste entfernt werden soll
     */
    @Override
    public void pickKey(Key key) {
        this.passiveObject.remove(key);
        hasKey = true;
    }

    /**
     * Bewegt Spieler um einen Schritt zurück, bei Kollision mit einer Wand
     *
     * @param wall Die Wand mit welcher der Spieler kollidiert
     */
    @Override
    public void playerTouchWall(Wall wall) {
        movePlayerBack();
    }

    /**
     * Prüft ob Spieler den Schlüssel besitzt und lässt Spieler bei Kollision mit Tür ggf. gewinnen
     *
     * @param door Die Tür mit welcher der Spieler kollidiert
     */
    @Override
    public void enterDoor(Door door) {
        if (!hasKey) {
            movePlayerBack();
        }
        if (hasKey && this.player.checkKollision(door)) {
            gameWon = true;
        }
    }

    /**
     * Bewegt Monster um zwei Einheiten zurück, bei Kollision mit einer Wand und verändert anschließend die Bewegungsrichtung in eine zufällige andere Richtung
     *
     * @param monster Das Monster welches mit einer Wand kollidiert
     */
    @Override
    public void monsterTouchWall(Monster monster) {
        int oldDirection = monster.getDirection();
        int direction = random.nextInt(4);
        if (oldDirection == 0) { //right
            monster.setX(monster.getX() - 2);
        }
        if (oldDirection == 1) { //left
            monster.setX(monster.getX() + 2);
        }
        if (oldDirection == 2) { //down
            monster.setY(monster.getY() - 2);
        }
        if (oldDirection == 3) { //up
            monster.setY(monster.getY() + 2);
        }
        monster.setDirection(direction);
    }

    /**
     * Bewegt alle Monster und macht dabei Kollisionsprüfung der Monster mit allen Objekten außer dem Spieler
     */
    private void moveMonster() {
        for (ActiveObject aObject : this.activeObject) {
            aObject.move();
            for (PassiveObject pObject : this.passiveObject) {
                if (aObject.checkKollision(pObject)) {
                    if (pObject instanceof Wall || pObject instanceof Door) {
                        aObject.kollisionWithNotPlayerObject();
                    }
                }
            }
        }
        for (ActiveObject aObject1 : this.activeObject) {
            for (ActiveObject aObject2 : this.activeObject) {
                if (aObject1.equals(aObject2)) {
                    break;
                } else if (aObject1.checkKollision(aObject2)) {
                    aObject1.kollisionWithNotPlayerObject();
                    aObject2.kollisionWithNotPlayerObject();
                }
            }
        }
    }

    /**
     * Bewegt Spieler um einen Schritt zurück
     */
    private void movePlayerBack() {
        if (up) {
            this.player.down();
        }
        if (down) {
            this.player.up();
        }
        if (left) {
            this.player.right();
        }
        if (right) {
            this.player.left();
        }
    }

    /**
     * Bewegt Spieler nach oben und setzt nur den dementsprechenden Parameter auf "ture"
     */
    public void playerUp() {
        this.player.up();
        up = true;
        down = false;
        left = false;
        right = false;
    }

    /**
     * Bewegt Spieler nach unten und setzt nur den dementsprechenden Parameter auf "ture"
     */
    public void playerDown() {
        this.player.down();
        down = true;
        up = false;
        left = false;
        right = false;
    }

    /**
     * Bewegt Spieler nach links und setzt nur den dementsprechenden Parameter auf "ture"
     */
    public void playerLeft() {
        this.player.left();
        left = true;
        down = false;
        up = false;
        right = false;
    }

    /**
     * Bewegt Spieler nach links und setzt nur den dementsprechenden Parameter auf "ture"
     */
    public void playerRight() {
        this.player.right();
        right = true;
        down = false;
        left = false;
        up = false;
    }

    /**
     * Gibt zurück, ob sich das Spiel im Developer-Modus befindet
     *
     * @return Status über Developer-Modus (true - Developer-Modus. false - normaler Spielmodus)
     */
    @Override
    public boolean isDevMode() {
        return devMode;
    }

    /**
     * Setzt den Developer-Modus auf den übergebenen Wert
     *
     * @param devMode Der boolesche Wert auf den der Developer-Modus gesetzt werden soll
     */
    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }

    /**
     * Gibt zurück, ob der Fog of War angeschaltet ist
     *
     * @return Status über Fog of War (true - Fog of War an. false - Fog of war aus)
     */
    @Override
    public boolean isFowOn() {
        return fowOn;
    }

    /**
     * Aktiviert / Deaktiviert den Fog of War
     *
     * @param fowOn Der boolesche Wert, der darüber entscheidet ob der Fog of War aktiviert/deaktiviert wird
     */
    public void setFowOn(boolean fowOn) {
        this.fowOn = fowOn;
    }
}
