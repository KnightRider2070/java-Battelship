package de.thecoder.main;

import java.awt.*;

public class Battleship extends GameObject {

    Handler handler;
    private final int pixelX;
    private final int pixelY;
    private final int rotation;


    public Battleship(int pixelX, int pixelY, int rotation, ID id, Handler handler) {
        super(pixelX, pixelY, id);
        this.handler = handler;
        this.pixelX = pixelX;
        this.pixelY = pixelY;
        this.rotation = rotation;
    }


    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.magenta);
        if (rotation == 0) {
            g.fillRect(pixelX, pixelY, 200, 50);
        } else if (rotation == 90) {
            g.fillRect(pixelX, pixelY, 50, 200);
        }
    }

}
