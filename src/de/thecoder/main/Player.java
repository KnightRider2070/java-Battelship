package de.thecoder.main;

import java.awt.*;

public class Player extends GameObject {

    Handler handler;

    int[][] shipPosition = new int[10][10];

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }


    public void tick() {
        x += velX;
        y += velY;
    }

    public void setShipPosition(int x, int y, int shipType) {

    }

    public void render(Graphics g) {

    }


}
