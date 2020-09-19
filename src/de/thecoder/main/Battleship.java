package de.thecoder.main;

import java.awt.*;

public class Battleship extends GameObject {

    private static final int HEALTH = 4;

    Handler handler;

    public Battleship(int x, int y, ID id, Handler handler) {
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
        if (id == ID.Battleship) g.setColor(Color.PINK);
        g.fillRect(x, y, 200, 50);
    }

}
