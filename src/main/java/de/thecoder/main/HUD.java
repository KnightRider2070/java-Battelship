package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //

import java.awt.*;

public class HUD {

// ---------------------------------------- Global Variables ---------------------------------------- //

    //Is the message String for the first player.
    private static String messageOne;
    //Is the message String for the second player.
    private static String messageTwo;
    //Is the message String for all players during startup.
    private static String messageAll;


// ---------------------------------------- Initialising Methods ---------------------------------------- //

    /* setMessageOne is a setter methode.
     * The Methode sets the message for player ones Display
     * @param message            Is a string that should contain the message to display
     */
    public static void setMessageOne(String message) {
        HUD.messageOne = message;
    }


    // ---------------------------------------- Setter Methods ---------------------------------------- //

    /* setMessageTwo is a setter methode.
     * The Methode sets the message for player twos Display
     * @param message            Is a string that should contain the message to display
     */
    public static void setMessageTwo(String message) {
        HUD.messageTwo = message;
    }

    /* setMessageAll is a setter methode.
     * The Methode sets the message for all players during startup.
     * @param message            Is a string that should contain the message to display
     */
    public static void setMessageAll(String message) {
        HUD.messageAll = message;
    }

    /*
     *The methode tick called every tick the tick is defined in Game.run().
     */
    public void tick() {
        if (messageOne == null)
            messageOne = " ";
        else if (messageTwo == null)
            messageTwo = " ";
        else if (messageAll == null)
            messageAll = " ";
    }


    // ---------------------------------------- Initialising Methods ---------------------------------------- //

    /*
     *The methode render is called every run the run is defined in Game.run().
     */
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(15, 15, 200, 32);
        g.setColor(Color.BLUE);
        Font font = new Font("Ariel", Font.PLAIN, 25);
        g.setFont(font);
        g.drawString("Ships Alive: " + Game.shipsAliveOne, 15, 40);

        g.setColor(Color.GRAY);
        g.fillRect(1050, 15, 200, 32);
        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString("Ships Alive: " + Game.shipsAliveTwo, 1050, 40);


        g.setColor(Color.CYAN);
        g.fillRect(15, 630, 330, 32);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Message: " + messageOne, 15, 655);

        g.setColor(Color.GREEN);
        g.fillRect(1050, 630, 330, 32);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Message: " + messageTwo, 1050, 655);

        g.setColor(Color.ORANGE);
        g.fillRect(530, 430, 370, 40);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Info: " + HUD.messageAll, 550, 460);

    }
}
