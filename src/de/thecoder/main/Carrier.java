package de.thecoder.main;

import java.awt.*;

public class Carrier extends GameObject {

    private static final int HEALTH = 5;

    Handler handler;

    public Carrier(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public static int getHealth() {
        return HEALTH;
    }

    public void tick() {
        x += velX;
        y += velY;
    }

    public void render(Graphics g) {
        if (id == ID.Carrier) g.setColor(Color.PINK);
        g.fillRect(x, y, 250, 50);
    }

}

