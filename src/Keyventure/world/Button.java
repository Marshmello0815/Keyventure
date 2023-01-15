package Keyventure.world;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Button {

    float x;
    float y;
    float width;
    float height;
    boolean pressed = false;
    PImage buttonUnpressed = null;
    PImage buttonPressed = null;
    String buttonText;
    float buttonTextXPosKorrektur = 0;
    float buttonTextYPosKorrektur = 0;
    float buttonTextAnimation = 3;
    float textSize;

    public Button(float x, float y, float width, float height, String buttonText, float textSize) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttonText = buttonText;
        this.textSize = textSize;
    }

    /**
     * Gibt X-Koordinate des Buttons zurück
     *
     * @return X-Koordinate des Objekts
     */
    public float getX() {
        return x;
    }

    /**
     * Gibt Y-Koordinate des Buttons zurück
     *
     * @return Y-Koordinate des Objekts
     */
    public float getY() {
        return y;
    }

    /**
     * Gibt Breite des Buttons zurück
     *
     * @return Breite des Buttons
     */
    public float getWidth() {
        return width;
    }

    /**
     * Gibt Höhe des Buttons zurück
     *
     * @return Höhe des Buttons
     */
    public float getHeight() {
        return height;
    }

    /**
     * Setzt den Parameter "pressed" auf den übergebenen Wert
     * @param pressed Der boolesche Wert, der darüber entscheidet, ob ein Button gedrückt worden ist
     */
    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    /**
     * Gibt an, ob eine Taste gedrückt ist
     */
    public boolean isPressed() {
        return pressed;
    }

    /**
     * Zeichnen der Button und der Texte
     *
     * @param app Übergabe der Klasse PApplet zur Benutzung der Methoden zum Zeichnen
     */
    public void draw(PApplet app) {
        if(buttonPressed == null){
            buttonUnpressed = app.loadImage("/resource/ui/buttonsAndMore/buttonLong_blue.png");
            buttonPressed = app.loadImage("/resource/ui/buttonsAndMore/buttonLong_blue_pressed.png");
        }

        if(!pressed) {
            app.pushStyle();
            app.image(buttonUnpressed, x, y, width, height);
            app.textSize(textSize);
            app.textAlign(PConstants.CENTER, PConstants.BOTTOM);
            app.fill(200);
            app.text(buttonText, (x + width/2) + buttonTextXPosKorrektur, (y + height*2/3) + buttonTextYPosKorrektur);
            app.popStyle();
        }
        else{
            app.pushStyle();
            app.image(buttonPressed, x, y, width, height);
            app.textSize(textSize);
            app.textAlign(PConstants.CENTER, PConstants.BOTTOM);
            app.fill(200);
            app.text(buttonText, (x + width/2) + buttonTextXPosKorrektur, (y + height*2/3) + buttonTextYPosKorrektur + buttonTextAnimation);
            app.popStyle();
        }

    }
}
