package de.thecoder.main;

import java.awt.*;

public class HUD {

    //IS the amount of ships that still exist Player One.
    public static int shipsAliveOne = 5;

    //IS the amount of ships that still exist Player Two.
    public static int shipsAliveTwo = 5;
    //Is the message String for the first player.
    public static String messageOne;
    //Is the message String for the second player.
    public static String messageTwo;

    public static void setMessageOne(String message) {
        HUD.messageOne = message;
    }

    public static void setMessageTwo(String message) {
        HUD.messageTwo = message;
    }


    public void setSipsAliveOne(int shipsAliveOne) {
        HUD.shipsAliveOne = shipsAliveOne;
    }

    public int getShipsAliveOne() {
        return shipsAliveOne;
    }

    public void setSipsAliveTwo(int shipsAliveTwo) {
        HUD.shipsAliveTwo = shipsAliveTwo;
    }

    public int getShipsAliveTwo() {
        return shipsAliveTwo;
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
        g.drawString("Ships Alive: " + shipsAliveOne, 15, 40);

        g.setColor(Color.GRAY);
        g.fillRect(1050, 15, 200, 32);
        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString("Ships Alive: " + shipsAliveTwo, 1050, 40);


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
