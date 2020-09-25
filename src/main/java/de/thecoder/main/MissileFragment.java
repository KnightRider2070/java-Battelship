package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //

import java.awt.*;

public class MissileFragment extends GameObject {

    // ---------------------------------------- Global Variables ---------------------------------------- //

    private final int hitX;
    private final int hitY;
    Handler handler;

    // ---------------------------------------- Initialising Methods ---------------------------------------- //

    public MissileFragment(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.hitX = x;
        this.hitY = y;
    }


    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.drawLine(hitX, hitY, hitX + 50, hitY + 50);
    }
}
