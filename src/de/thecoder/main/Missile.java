package de.thecoder.main;

import java.awt.*;

public class Missile extends GameObject {

    Handler handler;
    double durationOfAnimation = 0.02;
    private float alpha = 1;

    public Missile(int x, int y, ID id, Handler handler) {

        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        if (alpha > durationOfAnimation) {
            alpha -= (durationOfAnimation - 0.0001f);
        } else handler.removeObject(this);
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 25, 25);
    }
}
