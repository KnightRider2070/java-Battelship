package de.thecoder.main;

import java.awt.*;

public class Destroyer extends GameObject {

    private final int HEALTH = 2;
    Handler handler;

    public Destroyer(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;
    }

    //Checks for collision with the missile
    private void collision() {

    }


    public void render(Graphics g) {
        if (id == ID.Destroyer) g.setColor(Color.GREEN);
        g.fillRect(x, y, 100, 50);
    }


}
