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
    public static int shipType = 1;
    public int arrayX;
    public int arrayY;
    public int field = 0;
    public int rotation;
    int shipsSetOne = 0;
    int shipsSetTwo = 0;


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

    /*
     * mouseOver is a query Methode.
     * The Methode will calculate if a field is checked.
     * @param mouseX            Is an integer in pixel that contains the mouse position X axis.
     * @param mouseY            Is an integer in pixel that contains the mouse position Y axis.
     * @param pixelX            Is an integer in pixel which should contain the X axis cord.
     * @param pixelY            Is an integer in pixel which should contain the Y axis cord.
     * @param width             Is an integer in pixel which should be the width of the field to be checked.
     * @param height            Is an integer in pixel which should be the height of the field to be checked.
     */

    private boolean mouseOver(int mouseX, int mouseY, int pixelX, int pixelY, int width, int height) {
        if (mouseX > pixelX && mouseX < pixelX + width) {
            return mouseY > pixelY && mouseY < pixelY + height;
        } else return false;
    }

    /*
     * startup is a logic methode.
     * The Methode contains the actions that should been taken to create a ship.
     * @param arrayX             Is an integer in array value which should contain the X axis cord.
     * @param arrayY             Is an integer in array value which should contain the Y axis cord.
     */
    public void startup(int arrayX, int arrayY) {

        if (shipType != 5) {
            //Adds shipType plus one.
            shipType++;
        } else if (shipsSetOne > 4 && shipsSetTwo == 0) {
            shipType = 1;
        }

        //If statement will check if rotation is one this means 0.
        if (rotation == 1)
            //Rotation -1
            rotation--;
        if (shipsSetOne < 4) {
            shipsSetOne++;
            //Creates a ship.
            player.setShip(arrayX - 1, arrayY - 1, 0, shipType, rotation);

        } else if (shipsSetTwo < 5) {
            shipsSetTwo++;
            //Creates a ship.
            player.setShip(arrayX - 1, arrayY - 1, 1, shipType, rotation);
        } else if (shipsSetTwo == 5 && shipsSetOne == 5) {
            game.startup = false;
            game.gameState = Game.STATE.Game;
        }
        //Sets the cords to 0 to set them again.s
        this.arrayX = 0;
        this.arrayY = 0;
        rotation = 0;


    }

    /*
     * mouseClicked is a methode that executes if a mouse is clicked belongs to the MouseAdapter class
     * The Methode contains all actions that should happen if a button at the mouse is clicked.
     */
    public void mouseClicked(MouseEvent e) {
        //Assigns button the value from the clicked button.
        int button = e.getButton();
        //Assigns mouseX the position value from the clicked button X axis.
        int mouseX = e.getX();
        //Assigns mouseY the position value from the clicked button Y axis.
        int mouseY = e.getY();
        int field = 0;

        //If statement will check if the game is in startup mode.
        if (game.gameState == Game.STATE.Startup) {
            HUD.setMessageAll("Set X,Y,Rotation shipType: " + shipType);
            //If statement will check if a button is clicked.
            if (mouseOver(mouseX, mouseY, 600, 150, 50, 50)) {
                //If statement will check if the X cords are empty.
                if (arrayX == 0) {                    //When Empty X Cords will write into them.
                    arrayX = 1;
                    HUD.setMessageAll("X set.");
                }
                //If statement will check if the Y cords are empty.
                else if (arrayY == 0) {
                    //When empty Y Cords will write into them.
                    arrayY = 1;
                    HUD.setMessageAll("Y set.");
                }
            } else if (mouseOver(mouseX, mouseY, 680, 150, 50, 50)) {
                if (arrayX == 0) {
                    arrayX = 2;
                    HUD.setMessageAll("X set.");
                } else if (arrayY == 0) {
                    arrayY = 2;
                    HUD.setMessageAll("Y set.");
                }
            } else if (mouseOver(mouseX, mouseY, 760, 150, 50, 50)) {
                if (arrayX == 0) {
                    arrayX = 3;
                    HUD.setMessageAll("X set.");
                } else if (arrayY == 0) {
                    arrayY = 3;
                    HUD.setMessageAll("Y set.");
                }
            } else if (mouseOver(mouseX, mouseY, 600, 220, 50, 50)) {
                if (arrayX == 0) {
                    arrayX = 4;
                    HUD.setMessageAll("X set.");
                } else if (arrayY == 0) {
                    arrayY = 4;
                    HUD.setMessageAll("Y set.");
                }
            } else if (mouseOver(mouseX, mouseY, 680, 220, 50, 50)) {
                if (arrayX == 0) {
                    arrayX = 5;
                    HUD.setMessageAll("X set.");
                } else if (arrayY == 0) {
                    arrayY = 5;
                    HUD.setMessageAll("Y set.");
                }
            } else if (mouseOver(mouseX, mouseY, 760, 220, 50, 50)) {
                if (arrayX == 0) {
                    arrayX = 6;
                    HUD.setMessageAll("X set.");
                } else if (arrayY == 0) {
                    arrayY = 6;
                    HUD.setMessageAll("Y set.");
                }
            } else if (mouseOver(mouseX, mouseY, 600, 290, 50, 50)) {
                if (arrayX == 0) {
                    arrayX = 7;
                    HUD.setMessageAll("X set.");
                } else if (arrayY == 0) {
                    arrayY = 7;
                    HUD.setMessageAll("Y set.");
                }
            } else if (mouseOver(mouseX, mouseY, 680, 290, 50, 50)) {
                if (arrayX == 0) {
                    arrayX = 8;
                    HUD.setMessageAll("X set.");
                } else if (arrayY == 0) {
                    arrayY = 8;
                    HUD.setMessageAll("Y set.");
                }
            } else if (mouseOver(mouseX, mouseY, 760, 290, 50, 50)) {
                if (arrayX == 0) {
                    arrayX = 9;
                    HUD.setMessageAll("X set.");
                } else if (arrayY == 0) {
                    arrayY = 9;
                    HUD.setMessageAll("Y set.");
                }
            } else if (mouseOver(mouseX, mouseY, 600, 360, 95, 50)) {
                if (rotation == 0) {
                    rotation = 1;
                    HUD.setMessageAll("Rotation set.");
                }
            } else if (mouseOver(mouseX, mouseY, 715, 360, 95, 50)) {
                if (rotation == 0) {
                    rotation = 90;
                    HUD.setMessageAll("Rotation set.");
                }
            }
            //If statement will check if all required integers are filled.
            if (arrayX != 0 && arrayY != 0 && rotation != 0)
                //Starts the startup.
                startup(arrayX, arrayY);
        }

        //If statement that checks if the pressed button was the left one.
        if (button == 1 && !game.startup && game.gameState == Game.STATE.Game)
            //Executes a methode from the player class to attack.
            player.theAttackOrder(mouseX, mouseY);
    }
}
