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

    public void setShip(int x, int y, int field, ID id) {
        if (id == ID.Destroyer) {
            shipPosition[x][y] = 5;
            handler.addObject(new Destroyer(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Destroyer, handler));
        }
    }

    public void removeShip(int x, int y, int field, ID id) {

        if (id == ID.Destroyer) {
            shipPosition[x][y] = 0;
            handler.removeObject(new Destroyer(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Destroyer, handler));
        }

    }

    public void render(Graphics g) {

    }


}
