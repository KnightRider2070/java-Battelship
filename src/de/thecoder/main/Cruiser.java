package de.thecoder.main;

import java.awt.*;

public class Cruiser extends GameObject {

    Handler handler;
    private final int pixelX;
    private final int pixelY;
    private final int rotation;

    public Cruiser(int pixelX, int pixelY, int rotation, ID id, Handler handler) {
        super(pixelX, pixelY, id);
        this.handler = handler;
        this.pixelX = pixelX;
        this.pixelY = pixelY;
        this.rotation = rotation;
    }


    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        if (rotation == 0) {
            g.fillRect(pixelX, pixelY, 150, 50);
        } else if (rotation == 90) {
            g.fillRect(pixelX, pixelY, 50, 150);
        }
    }
}
