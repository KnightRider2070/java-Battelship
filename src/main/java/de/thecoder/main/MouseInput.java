package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    // ---------------------------------------- Global Variables ---------------------------------------- //

    //Game object handler reference. Final cuz it should never be changed so that it is always listening to the same handler.
    private final Handler handler;
    //Game object player reference to use a methode without setting this to static.
    Player player;

    // ---------------------------------------- Initialising Methods ---------------------------------------- //

    /*
     * MouseInput is the constructor for the MouseInput class
     * The Methode assigns the used values.
     * @param handler        Is the handler that is assigned it should be the handler that is initialised in the Game class.
     * @param player         Is the player that is assigned it should be the player that is initialised in the Game class.
     */
    public MouseInput(Handler handler, Player player) {
        //Assigns the handler from the Game class to the handler in the MouseInput class.
        this.handler = handler;
        //Assigns the player from the Game class to the player in the MouseInput class.
        this.player = player;
    }

    // ---------------------------------------- Logic Methods ---------------------------------------- //

    /*
     * mouseClicked is a methode that executes if a mouse is clicked belongs to the MouseAdapter class
     * The Methode contains all actions that should happen if a button at the mouse is clicked.
     */
    public void mouseClicked(MouseEvent e) {
        //Assigns button the value from the clicked button.
        int button = e.getButton();
        //Assigns pixelX the position value from the clicked button X axis.
        int pixelX = e.getX();
        //Assigns pixelY the position value from the clicked button Y axis.
        int pixelY = e.getY();

        //If statement that checks if the pressed button was the left one.
        if (button == 1)
            //Executes a methode from the player class to attack.
            player.theAttackOrder(pixelX, pixelY);
    }

}
