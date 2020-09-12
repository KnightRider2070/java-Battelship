package de.thecoder.main;

import java.awt.*;

public class HUD {

    public static int shipsAlive = 5;

    public void tick() {


    }

    public void setSipsAlive(int shipsAlive) {
        HUD.shipsAlive = shipsAlive;
    }

    public int getShipsAlive() {
        return shipsAlive;
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, 200, 32);
        g.setColor(Color.BLUE);
        Font font = new Font("Ariel", Font.PLAIN, 25);
        g.setFont(font);
        g.drawString("Ships Alive: " + shipsAlive, 15, 40);
    }
}
