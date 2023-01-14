package Keyventure.world;

import Keyventure.world.obj.*;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameWorld implements IGameWorld {
    public static final int INVULNERABLE_TIME = 45;
    List<PassiveObject> passiveObject;
    List<ActiveObject> activeObject;

    List<ActiveObject> killedMonster;
    public Player player;
    FoW fow;
    Lives lives;

    public boolean devMode = false;
    public boolean fowOn = true;

    boolean hasKey = false;
    List<Key> collectedKeys;
    boolean gameWon = false;
    boolean gameLose = false;
    boolean changeDirectionPossible = false;

    int countKollisionWithMonster = 0;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    boolean hasWeapon = false;
    List<Weapon> collectedWeapons;

    boolean mainMenu = true;
    boolean controlsAndRules = false;

    PImage menuBackground = null;
    PImage wButton = null;
    PImage aButton = null;
    PImage sButton = null;
    PImage dButton = null;
    PImage arrowUp = null;
    PImage arrowLeft = null;
    PImage arrowDown = null;
    PImage arrowRight = null;
    PImage leftShift = null;
    PImage key = null;
    PImage sword = null;
    PImage playerImage = null;
    PImage skeletton = null;
    PFont menuFont = null;
    List<Button> mainMenuButtons;
    List<Button> controlsAndRulesButtons;
    List<Button> winLoseButtons;

    public GameWorld() {
        passiveObject = new ArrayList<>();
        activeObject = new ArrayList<>();
        killedMonster = new ArrayList<>();
        mainMenuButtons = new ArrayList<>();
        controlsAndRulesButtons = new ArrayList<>();
        winLoseButtons = new ArrayList<>();
        collectedKeys = new ArrayList<>();
        collectedWeapons = new ArrayList<>();
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
     * Gibt alle passiven Objekte in Form einer nicht modifizierbaren Liste zurück
     *
     * @return unmodifizierbare Liste aller passiven Objekte
     */
    public List<PassiveObject> getPassiveObjects() {
        return Collections.unmodifiableList(this.passiveObject);
    }

    /**
     * Gibt alle aktiven Objekte in Form einer nicht modifizierbaren Liste zurück
     *
     * @return unmodifizierbare Liste aller aktiven Objekte
     */
    public List<ActiveObject> getActiveObjects() {
        return Collections.unmodifiableList(this.activeObject);
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
        if (menuBackground == null) {
            menuBackground = app.loadImage("/resource/ui/background/background.png");
            wButton = app.loadImage("/resource/ui/keyboard/kb_w.png");
            aButton = app.loadImage("/resource/ui/keyboard/kb_a.png");
            sButton = app.loadImage("/resource/ui/keyboard/kb_s.png");
            dButton = app.loadImage("/resource/ui/keyboard/kb_d.png");
            arrowUp = app.loadImage("/resource/ui/keyboard/kb_arrow_up.png");
            arrowLeft = app.loadImage("/resource/ui/keyboard/kb_arrow_left.png");
            arrowDown = app.loadImage("/resource/ui/keyboard/kb_arrow_down.png");
            arrowRight = app.loadImage("/resource/ui/keyboard/kb_arrow_right.png");
            leftShift = app.loadImage("/resource/ui/keyboard/kb_shift_left.png");
            key = app.loadImage("/resource/key/key.png");
            sword = app.loadImage("/resource/weapons/sword.png");
            playerImage = app.loadImage("/resource/player/PlayerFront_3.png");
            skeletton = app.loadImage("/resource/monster/MonsterFront_3.jpg");
            menuFont = app.createFont("/resource/ui/font/PublicPixel.otf", 16);
            app.textFont(menuFont);
        }

        if (mainMenu) {
            drawMainMenu(app);
            if (controlsAndRules) {
                drawControlsAndRules(app);
            }
        }

        if (!gameLose && !gameWon && !mainMenu) {
            app.background(0, 0, 0);
            for (GameObject object : this.allObjects()) {
                object.draw(app);
            }

            fow.draw(app);
            lives.draw(app);

            for (PassiveObject pObject : this.passiveObject) {
                if (this.player.checkKollision(pObject)) {
                    pObject.collisionWithPlayer();
                }
            }

            for (ActiveObject aObject : this.activeObject) {
                if (this.player.checkKollision(aObject) && hasWeapon) {
                    killedMonster.add(aObject);
                } else if (this.player.checkKollision(aObject) && !hasWeapon) {
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

            activeObject.removeAll(killedMonster);
            passiveObject.removeAll(collectedKeys);
            passiveObject.removeAll(collectedWeapons);

            if (countKollisionWithMonster > 0) {
                countKollisionWithMonster += 1;
                if (countKollisionWithMonster == INVULNERABLE_TIME) {
                    countKollisionWithMonster = 0;
                }
            }
        }
        if (gameLose) {
            app.image(menuBackground, 0, 0, app.displayWidth, app.displayHeight);
            app.pushStyle();
            app.fill(120, 120, 120);
            app.textAlign(PConstants.CENTER);
            app.textSize(200);
            app.text("Game Over", (float) (app.displayWidth / 2), (float) 0.4*app.displayHeight);
            app.popStyle();
            for (Button button: this.winLoseButtons) {
                button.draw(app);
            }
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
            app.textAlign(PConstants.CENTER);
            app.textSize(200);
            app.text("Game Won", (float) (app.displayWidth / 2), (float) 0.4 * app.displayHeight);
            app.popStyle();

            for (Button button: this.winLoseButtons) {
                button.draw(app);
            }
        }
    }

    /**
     * Zeichnet den Hauptmenüscreen
     * @param app Übergabe der Klasse PApplet zur Benutzung der Methoden zum Zeichnen
     */
    private void drawMainMenu(PApplet app) {
        app.image(menuBackground, 0, 0, app.displayWidth, app.displayHeight);

        app.pushStyle();
        app.textAlign(0 ,PConstants.TOP);
        app.textSize(120);
        app.fill(140);
        app.text("-VENTURE", (float) (0.335*app.displayWidth), (float) (0.1*app.displayHeight));
        app.image(key,(float) (0.292*app.displayWidth),(float) (0.105*app.displayHeight),140,140);
        app.popStyle();

        for (Button button: this.mainMenuButtons) {
            button.draw(app);
        }
    }

    /**
     * Zeichnet den Regel- und Steurungscreen
     * @param app Übergabe der Klasse PApplet zur Benutzung der Methoden zum Zeichnen
     */
    private void drawControlsAndRules(PApplet app) {
        app.image(menuBackground, 0, 0, app.displayWidth, app.displayHeight);
        app.pushStyle();
        app.textAlign(PConstants.LEFT);
        app.textSize(16);
        app.fill(140);
        app.text("Ziel des Spiels: Finde den Schlüssel und entkomme dem Labyrinth ohne von den Skeletten getötet zu werden.", (float) (0.175*app.displayWidth), (float) (0.1*app.displayHeight));
        app.popStyle();

        app.image(wButton, (float) (0.2*app.displayWidth), (float) (0.2*app.displayHeight), (float) (0.03*app.displayWidth), (float) (0.03*app.displayWidth));
        app.image(aButton, (float) ((0.2*app.displayWidth) - (0.03*app.displayWidth)), (float) (0.26*app.displayHeight), (float) (0.03*app.displayWidth), (float) (0.03*app.displayWidth));
        app.image(sButton, (float) (0.2*app.displayWidth), (float) (0.26*app.displayHeight), (float) (0.03*app.displayWidth), (float) (0.03*app.displayWidth));
        app.image(dButton, (float) ((0.2*app.displayWidth) + (0.03*app.displayWidth)), (float) (0.26*app.displayHeight), (float) (0.03*app.displayWidth), (float) (0.03*app.displayWidth));
        app.pushStyle();
        app.textSize(180);
        app.fill(140);
        app.text("/", (float) (0.27*app.displayWidth), (float) (0.31*app.displayHeight));
        app.popStyle();
        app.image(arrowUp, (float) (0.35*app.displayWidth), (float) (0.2*app.displayHeight), (float) (0.03*app.displayWidth), (float) (0.03*app.displayWidth));
        app.image(arrowLeft, (float) ((0.35*app.displayWidth) - (0.03*app.displayWidth)), (float) (0.26*app.displayHeight), (float) (0.03*app.displayWidth), (float) (0.03*app.displayWidth));
        app.image(arrowDown, (float) (0.35*app.displayWidth), (float) (0.26*app.displayHeight), (float) (0.03*app.displayWidth), (float) (0.03*app.displayWidth));
        app.image(arrowRight, (float) ((0.35*app.displayWidth) + (0.03*app.displayWidth)), (float) (0.26*app.displayHeight), (float) (0.03*app.displayWidth), (float) (0.03*app.displayWidth));
        app.pushStyle();
        app.textSize(16);
        app.fill(140);
        app.text("Spieler bewegen.", (float) (0.44*app.displayWidth), (float) (0.26*app.displayHeight));
        app.popStyle();

        app.image(leftShift, (float) (0.63*app.displayWidth), (float) (0.23*app.displayHeight), (float) (0.03*app.displayWidth), (float) (0.03*app.displayWidth));
        app.pushStyle();
        app.textSize(16);
        app.fill(140);
        app.text("Spieler läuft schneller.", (float) (0.68*app.displayWidth), (float) (0.26*app.displayHeight));
        app.popStyle();

        app.image(key, (float) (0.25*app.displayWidth), (float) (0.37*app.displayHeight), (float) (0.03*app.displayWidth), (float) (0.03*app.displayWidth));
        app.pushStyle();
        app.textSize(16);
        app.fill(140);
        app.text("Das ist der Schlüssel den du finden musst, um aus dem Labyrinth zu kommen.", (float) (0.295*app.displayWidth), (float) (0.395*app.displayHeight));
        app.popStyle();

        app.image(sword, (float) (0.25*app.displayWidth), (float) (0.47*app.displayHeight), (float) (0.03*app.displayWidth), (float) (0.03*app.displayWidth));
        app.pushStyle();
        app.textSize(16);
        app.fill(140);
        app.text("Mit diesem Schwert bis du unbesiegbar. \nAlleine der Kontakt mit einem Skelett reicht aus um es zu töten.", (float) (0.295*app.displayWidth), (float) (0.495*app.displayHeight));
        app.popStyle();

        app.image(playerImage, (float) (0.25*app.displayWidth), (float) (0.57*app.displayHeight), (float) (0.03*app.displayWidth), (float) (0.04*app.displayWidth));
        app.pushStyle();
        app.textSize(16);
        app.fill(140);
        app.text("Das bist du. (Keine Ahnung wie du es geschafft hast in diesem Labyrinth zu landen...)", (float) (0.295*app.displayWidth), (float) (0.61*app.displayHeight));
        app.popStyle();

        app.image(skeletton, (float) (0.25*app.displayWidth), (float) (0.67*app.displayHeight), (float) (0.03*app.displayWidth), (float) (0.04*app.displayWidth));
        app.pushStyle();
        app.textSize(16);
        app.fill(140);
        app.text("Das ist ein Skelett (Ohne Schwert würde ich mich davon fern halten - \nund ja der schwarze Hintergrund ist 'absicht')", (float) (0.295*app.displayWidth), (float) (0.702*app.displayHeight));
        app.popStyle();

        for (Button button: this.controlsAndRulesButtons) {
            button.draw(app);
        }
    }

    /**
     * Setzt die boolesche Variable "mainMenu" auf den übergebenen Wert
     * @param mainMenu Der boolesche Wert auf den "mainMenu" gesetzt werden soll
     */
    public void setMainMenu(boolean mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**
     * Gibt zurück ob, sich das Spiel im Hauptmenü befindet
     * @return Variable, die sagt obs, ich das Spiel im Hauptmenü befindet
     */
    public boolean isMainMenu() {
        return mainMenu;
    }

    /**
     * Gibt zurück ob, sich das Spiel im Steurung- und Regeln-Menüs befindet
     * @return Variable, die sagt ob, sich das Spiel im Hauptmenü befindet
     */
    public boolean isControlsAndRules() {
        return controlsAndRules;
    }

    /**
     * Gibt zurück ob, dass Spiel gewonnen wurde
     * @return Variable, die sagt ob, dass Spiel gewonnen wurde
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Gibt zurück ob, dass Spiel verloren wurde
     * @return Variable, die sagt ob, dass Spiel verloren wurde
     */
    public boolean isGameLose() {
        return gameLose;
    }

    /**
     * Setzt die boolesche Variable "controlsAndRules" auf den übergebenen Wert
     * @param controlsAndRules Der boolesche Wert auf den "controlsAndRules" gesetzt werden soll
     */
    public void setControlsAndRules(boolean controlsAndRules) {
        this.controlsAndRules = controlsAndRules;
    }

    /**
     * Gibt eine Liste mit allen Buttons des Hauptmenüs zurück
     * @return Liste mit allen Buttons des Hauptmenüs
     */
    public List<Button> getMainMenuButtons() {
        return mainMenuButtons;
    }

    /**
     * Setzt die Liste "mainMenuButtons" auf den übergebenen Wert
     * @param mainMenuButtons Der Wert auf den "mainMenuButtons" gesetzt werden soll
     */
    public void setMainMenuButtons(List<Button> mainMenuButtons) {
        this.mainMenuButtons = mainMenuButtons;
    }

    /**
     * Gibt eine Liste mit allen Buttons des Steuerung- und Regeln-Menüs zurück
     * @return Liste mit allen Buttons des Steuerung- und Regeln-Menüs
     */
    public List<Button> getControlsAndRulesButtons() {
        return controlsAndRulesButtons;
    }

    /**
     * Setzt die Liste "controlsAndRulesButtons" auf den übergebenen Wert
     * @param controlsAndRulesButtons Der Wert auf den "controlsAndRulesButtons" gesetzt werden soll
     */
    public void setControlsAndRulesButtons(List<Button> controlsAndRulesButtons) {
        this.controlsAndRulesButtons = controlsAndRulesButtons;
    }

    /**
     * Gibt eine Liste mit allen Buttons des Gewonnen-/Verloren-Screens zurück
     * @return Liste mit allen Buttons des Gewonnen-/Verloren-Screens
     */
    public List<Button> getWinLoseButtons() {
        return winLoseButtons;
    }

    /**
     * Setzt die Liste "winLoseButtons" auf den übergebenen Wert
     * @param winLoseButtons Der Wert auf den "winLoseButtons" gesetzt werden soll
     */
    public void setWinLoseButtons(List<Button> winLoseButtons) {
        this.winLoseButtons = winLoseButtons;
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
        this.collectedKeys.add(key);
        hasKey = true;
    }

    /**
     * Entfernen der Waffe aus der Liste passiver Objekten und setzen des Parameters "hasWeapon" auf "true"
     *
     * @param weapon Die Waffe, die aus der Liste entfernt werden soll
     */
    @Override
    public void pickWeapon(Weapon weapon) {
        this.collectedWeapons.add(weapon);
        hasWeapon = true;
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
    public void monsterTouchNotPlayerObject(Monster monster) {
        Direction oldDirection = monster.getDirection();
        Direction newDirection = monster.getDirection();
        while (oldDirection == newDirection) {
            newDirection = Direction.getRandomDirection();
        }
        if (oldDirection == Direction.RIGHT) {
            monster.setX(monster.getX() - Monster.STEP_SIZE);
        }
        if (oldDirection == Direction.LEFT) {
            monster.setX(monster.getX() + Monster.STEP_SIZE);
        }
        if (oldDirection == Direction.DOWN) {
            monster.setY(monster.getY() - Monster.STEP_SIZE);
        }
        if (oldDirection == Direction.UP) {
            monster.setY(monster.getY() + Monster.STEP_SIZE);
        }
        monster.setDirection(newDirection);
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
                        aObject.collisionWithNotPlayerObject();
                    }
                }
            }
        }
        for (ActiveObject aObject1 : this.activeObject) {
            for (ActiveObject aObject2 : this.activeObject) {
                if (aObject1.equals(aObject2)) {
                    break;
                } else if (aObject1.checkKollision(aObject2)) {
                    aObject1.collisionWithNotPlayerObject();
                    aObject2.collisionWithNotPlayerObject();
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
     * Lässt den Spieler rennen durch Ändern der Schrittgröße
     */
    public void playerRun() {
        this.player.setStepSize(Player.RUN_STEP_SIZE);
    }

    /**
     * Lässt den Spieler normal gehen durch Ändern der Schrittgröße
     */
    public void playerWalk() {
        this.player.setStepSize(Player.NORMAL_STEP_SIZE);
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

    /**
     * Startet das Spiel neu
     */
    public void restart(){
        passiveObject.clear();
        activeObject.clear();
        this.gameLose = false;
        this.gameWon = false;
    }
}
