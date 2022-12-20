package Keyventure;

import processing.core.PApplet;

public class Labyrinth {

    public static final int KACHELBREITE = 50;
    public static final int KACHELHOEHE = 50;

    enum KachelTyp {
            WAND, BODEN
    }

    KachelTyp[][] kacheln = new KachelTyp[10][10];

    public Labyrinth() {
        for (int i = 0; i < kacheln.length; i++) {
            KachelTyp[] zeile = kacheln[i];
            for (int j = 0; j < zeile.length; j++) {
                if (i == j && i != 5) {
                    zeile[j] = KachelTyp.WAND;
                } else {
                    zeile[j] = KachelTyp.BODEN;
                }
            }
        }
    }

    public void zeichne(PApplet app) {
        for (int i = 0; i < kacheln.length; i++) {
            for (int j = 0; j < kacheln[i].length; j++) {
                app.pushStyle();
                if (kacheln[i][j] == KachelTyp.WAND) {
                    app.fill(0, 0, 255, 127);
                }
                app.rect(KACHELBREITE* i, KACHELHOEHE * j, KACHELBREITE, KACHELHOEHE);
                app.popStyle();
            }
        }
    }




}
