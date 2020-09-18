package de.thecoder.main;

import java.awt.*;

public class HUD {

    //IS the amount of ships that still exist.
    //TODO: USE IT
    public static int shipsAlive = 5;
    //Is the message String for the first player.
    public static String messageOne;
    //Is the message String for the second player.
    public static String messageTwo;

    public static void setMessageOne(String message) {
        HUD.messageOne = message;
    }

    public void setSipsAlive(int shipsAlive) {
        HUD.shipsAlive = shipsAlive;
    }

    public int getShipsAlive() {
        return shipsAlive;
    }

    public static void setMessageTwo(String message) {
        HUD.messageTwo = message;
    }

    public void tick() {
        if (messageOne == null)
            messageOne = " ";
        else if (messageTwo == null)
            messageTwo = " ";


    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, 200, 32);
        g.setColor(Color.BLUE);
        Font font = new Font("Ariel", Font.PLAIN, 25);
        g.setFont(font);
        g.drawString("Ships Alive: " + shipsAlive, 15, 40);


        g.setColor(Color.CYAN);
        g.fillRect(15, 630, 330, 32);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Message:" + messageOne, 15, 655);

        g.setColor(Color.GREEN);
        g.fillRect(1050, 630, 330, 32);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Message:" + messageTwo, 1050, 655);
    }
}
