package de.thecoder.main;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {


public static int    shipType = 1;
public static int    arrayX;/*Is an integer in array value which should contain the X axis cord.*/
public static int    arrayY;/*Is an integer in array value which should contain the Y axis cord.*/
public static int    rotation;/*Is an integer which should the rotation of the ship possible is 0 and 90.*/
public static int    field    = 0;/*Is an integer which should be 0(Player One) or 1(Player Two) is the layer with
ship cords.*/
private final Game   game; /*Is a reference to the game that was initialised when lunching the program.*/
private final Player player;/*Is a reference to the player that was initialised when calling Game() methode.*/


/**
 * MouseInput is the constructor for the MouseInput.
 *
 * @param player Is the player that is assigned it should be the player that is initialised in the Game class.
 * @param game   Is a reference to the game, that was initialised when lunching the program.
 */
public MouseInput(final Game game, final Player player) {

	this.player = player;
	this.game   = game;
}


/**
 * The Methode will calculate if a field is clicked.
 *
 * @param mouseX Is an integer in pixel that contains the mouse position X axis.
 * @param mouseY Is an integer in pixel that contains the mouse position Y axis.
 * @param pixelX Is an integer in pixel which should contain the X axis cord.
 * @param pixelY Is an integer in pixel which should contain the Y axis cord.
 * @param width  Is an integer in pixel which should be the width of the field to be checked.
 * @param height Is an integer in pixel which should be the height of the field to be checked.
 */
private boolean mouseOver(int mouseX, int mouseY, int pixelX, int pixelY, int width, int height) {

	if(mouseX > pixelX && mouseX < pixelX + width) {
		return mouseY > pixelY && mouseY < pixelY + height;
	} else
		return false;
}

/**
 * mouseClicked is a methode that executes if a mouse is clicked belongs to the MouseAdapter class The Methode contains
 * all actions that should happen if a button at the mouse is clicked.
 */
public void mouseClicked(MouseEvent e) {

	int button = e.getButton();
	int mouseX = e.getX();
	int mouseY = e.getY();

	if(Game.STARTUP) {/*If statement check if game is on STARTUP*/
		HUD.setMessageAll("Set X,Y,Dir. shipType: " + shipType);
		/*If statement will check if a drawn button is clicked.*/
		if(mouseOver(mouseX, mouseY, 645, 150, 50, 50)) {
			if(arrayX == 0) {
				arrayX = 1;
				HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 1;
				HUD.setMessageAll("Y set.");
			}
		} else if(mouseOver(mouseX, mouseY, 725, 150, 50, 50)) {
			if(arrayX == 0) {
				arrayX = 2;
				HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 2;
				HUD.setMessageAll("Y set.");
			}
		} else if(mouseOver(mouseX, mouseY, 805, 150, 50, 50)) {
			if(arrayX == 0) {
				arrayX = 3;
				HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 3;
				HUD.setMessageAll("Y set.");
			}
		} else if(mouseOver(mouseX, mouseY, 645, 220, 50, 50)) {
			if(arrayX == 0) {
				arrayX = 4;
				HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 4;
				HUD.setMessageAll("Y set.");
			}
		} else if(mouseOver(mouseX, mouseY, 725, 220, 50, 50)) {
			if(arrayX == 0) {
				arrayX = 5;
				HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 5;
				HUD.setMessageAll("Y set.");
			}
		} else if(mouseOver(mouseX, mouseY, 805, 220, 50, 50)) {
			if(arrayX == 0) {
				arrayX = 6;
				HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 6;
				HUD.setMessageAll("Y set.");
			}
		} else if(mouseOver(mouseX, mouseY, 645, 290, 50, 50)) {
			if(arrayX == 0) {
				arrayX = 7;
				HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 7;
				HUD.setMessageAll("Y set.");
			}
		} else if(mouseOver(mouseX, mouseY, 725, 290, 50, 50)) {
			if(arrayX == 0) {
				arrayX = 8;
				HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 8;
				HUD.setMessageAll("Y set.");
			}
		} else if(mouseOver(mouseX, mouseY, 805, 290, 50, 50)) {
			if(arrayX == 0) {
				arrayX = 9;
				HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 9;
				HUD.setMessageAll("Y set.");
			}
		} else if(mouseOver(mouseX, mouseY, 645, 360, 210, 50)) {
			if(arrayX == 0) {
				arrayX = 10;
				HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 10;
				HUD.setMessageAll("Y set.");
			}
		} else if(mouseOver(mouseX, mouseY, 645, 430, 95, 50)) {
			if(rotation == 0) {
				rotation = 1;
				HUD.setMessageAll("Rotation set.");
			}
		} else if(mouseOver(mouseX, mouseY, 760, 430, 95, 50)) {
			if(rotation == 0) {
				rotation = 90;
				HUD.setMessageAll("Rotation set.");
			}
		}

		/*Position status fields.*/
		HUD.setMessageArrayX(arrayX);
		HUD.setMessageArrayY(arrayY);
		HUD.setMessageRotation(rotation);
		HUD.setMessageShipType(shipType);


		if(arrayX != 0 && arrayY != 0 && rotation != 0) {/*If statement check if required integers are entered.*/
			/*Changes values that they are usable.*/
			if(rotation == 1)
				rotation = 0;
			arrayX--;
			arrayY--;

			/*If statement will check if it is possible to set a ship.*/
			if(player.setShip(arrayX, arrayY, field, shipType, rotation)) {
				HUD.setMessageAll(" ");
				rotation = 0;
				arrayX   = 0;
				arrayY   = 0;
				shipType++;
				if(shipType > 5 && field == 0) {
					HUD.setMessageAll("Player 2 pls");
					shipType       = 1;
					field          = 1;
					Game.gameState = STATE.GamePlayer2;
				} else if(shipType > 5 && field == 1) {
					HUD.setMessageAll("Player 1 attack");
					Game.STARTUP   = false;
					Game.gameState = STATE.GamePlayer1;
				}
			} else {
				HUD.setMessageAll("Non valid Cords");
				rotation = 0;
				arrayX   = 0;
				arrayY   = 0;
			}
		}

	}

	/*If statement that checks if the pressed button was the left one.*/
	if(button == 1 && ! Game.STARTUP) {
		/*Checks if the values at that you clicked are in the field*/
		if(mouseX > 1000 && mouseX < 1500 && mouseY > 100 && mouseY < 600 && Game.gameState == STATE.GamePlayer1) {
			player.theGameAttackManager(mouseX, mouseY);
		} else if(mouseX > 0 && mouseX < 500 && mouseY > 100 && mouseY < 600 && Game.gameState == STATE.GamePlayer2) {
			player.theGameAttackManager(mouseX, mouseY);
		} else
			HUD.setMessageAll("Click in field!PLS");
	}
}

}
