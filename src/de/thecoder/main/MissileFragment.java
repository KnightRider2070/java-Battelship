package de.thecoder.main;

import java.awt.*;

public class MissileFragment extends GameObject {

    private final int hitX;
    private final int hitY;
    Handler handler;


    public MissileFragment(int x, int y, ID id) {
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
