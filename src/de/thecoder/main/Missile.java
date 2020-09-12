package de.thecoder.main;

import java.awt.*;

public class Missile extends GameObject {

    public Missile(int x, int y, ID id) {
        super(x, y, id);
    }


    public void tick() {
        x += velX;
        y += velY;
    }


    public void render(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(x, y, 22, 22);
    }
}
