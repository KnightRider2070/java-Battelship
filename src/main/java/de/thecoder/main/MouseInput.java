package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    // ---------------------------------------- Global Variables ---------------------------------------- //

    //Game object handler reference. Final cuz it should never be changed so that it is always listening to the same handler.
    private final Handler handler;

    private final Game game;
    //Game object player reference to use a methode without setting this to static.
    Player player;
    public int arrayX = 0, arrayY = 0;
    public int shipType = 1;
    public int field = 0;
    public int rotation;
    MouseEvent e;

    // ---------------------------------------- Initialising Methods ---------------------------------------- //

    /*
     * MouseInput is the constructor for the MouseInput class
     * The Methode assigns the used values.
     * @param handler        Is the handler that is assigned it should be the handler that is initialised in the Game class.
     * @param player         Is the player that is assigned it should be the player that is initialised in the Game class.
     */
    public MouseInput(Handler handler, Game game, Player player) {
        //Assigns the handler from the Game class to the handler in the MouseInput class.
        this.handler = handler;
        //Assigns the player from the Game class to the player in the MouseInput class.
        this.player = player;

        this.game = game;
    }

    // ---------------------------------------- Logic Methods ---------------------------------------- //

    private boolean mouseOver(int mouseX, int mouseY, int pixelX, int pixelY, int width, int height) {
        if (mouseX > pixelX && mouseX < pixelX + width) {
            return mouseY > pixelY && mouseY < pixelY + height;
        } else return false;
    }

    public void startup(int arrayX, int arrayY) {
        shipType++;
        player.setShip(arrayX - 1, arrayY - 1, 0, shipType, rotation);
        rotation = 0;
        arrayX = 0;
        arrayY = 0;
    }

    /*
     * mouseClicked is a methode that executes if a mouse is clicked belongs to the MouseAdapter class
     * The Methode contains all actions that should happen if a button at the mouse is clicked.
     */
    //FIXME: Rotation 0
    public void mouseClicked(MouseEvent e) {
        //Assigns button the value from the clicked button.
        int button = e.getButton();
        //Assigns mouseX the position value from the clicked button X axis.
        int mouseX = e.getX();
        //Assigns mouseY the position value from the clicked button Y axis.
        int mouseY = e.getY();
        int field = 0;

        if (game.gameState == Game.STATE.Startup) {
            if (mouseOver(mouseX, mouseY, 600, 150, 50, 50)) {
                if (arrayX == 0)
                    arrayX = 1;
                else if (arrayY == 0)
                    arrayY = 1;
            } else if (mouseOver(mouseX, mouseY, 680, 150, 50, 50)) {
                if (arrayX == 0)
                    arrayX = 2;
                else if (arrayY == 0)
                    arrayY = 2;
            } else if (mouseOver(mouseX, mouseY, 760, 150, 50, 50)) {
                if (arrayX == 0)
                    arrayX = 3;
                else if (arrayY == 0)
                    arrayY = 3;
            } else if (mouseOver(mouseX, mouseY, 600, 220, 50, 50)) {
                if (arrayX == 0)
                    arrayX = 4;
                else if (arrayY == 0)
                    arrayY = 4;
            } else if (mouseOver(mouseX, mouseY, 680, 220, 50, 50)) {
                if (arrayX == 0)
                    arrayX = 5;
                else if (arrayY == 0)
                    arrayY = 5;
            } else if (mouseOver(mouseX, mouseY, 760, 220, 50, 50)) {
                if (arrayX == 0)
                    arrayX = 6;
                else if (arrayY == 0)
                    arrayY = 6;
            } else if (mouseOver(mouseX, mouseY, 600, 290, 50, 50)) {
                if (arrayX == 0)
                    arrayX = 7;
                else if (arrayY == 0)
                    arrayY = 7;
            } else if (mouseOver(mouseX, mouseY, 680, 290, 50, 50)) {
                if (arrayX == 0)
                    arrayX = 8;
                else if (arrayY == 0)
                    arrayY = 8;
            } else if (mouseOver(mouseX, mouseY, 760, 290, 50, 50)) {
                if (arrayX == 0)
                    arrayX = 9;
                else if (arrayY == 0)
                    arrayY = 9;
            } else if (mouseOver(mouseX, mouseY, 600, 360, 95, 50)) {
                if (rotation == 0) {
                    rotation = 0;
                }
            } else if (mouseOver(mouseX, mouseY, 715, 360, 95, 50)) {
                if (rotation == 0) {
                    rotation = 90;
                }
            }
            if (arrayX != 0 && arrayY != 0 && rotation != 0)
                startup(arrayX, arrayY);
        }

        //If statement that checks if the pressed button was the left one.
        if (button == 1 && !game.startup && game.gameState == Game.STATE.Game)
            //Executes a methode from the player class to attack.
            player.theAttackOrder(mouseX, mouseY);
    }
}
