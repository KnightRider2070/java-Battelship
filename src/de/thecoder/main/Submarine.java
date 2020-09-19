package de.thecoder.main;

import java.awt.*;

public class Submarine extends GameObject {

    private static final int HEALTH = 3;

    Handler handler;

    public Submarine(int x, int y, ID id, Handler handler) {
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
        if (id == ID.Submarine) g.setColor(Color.PINK);
        g.fillRect(x, y, 150, 50);
    }

}
