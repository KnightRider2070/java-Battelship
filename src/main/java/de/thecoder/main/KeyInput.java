package de.thecoder.main;


import java.awt.event.*;

public class KeyInput extends KeyAdapter {


private static int    shipType = 1;
private final  Game   game;  /*Is a reference to the game that was initialised when lunching the program.*/
private final  Player player; /*player reference final cuz shouldn't be possible to change.*/
private        int    arrayX; /*Is an integer in array value which should contain the X axis cord.*/
private        int    arrayY; /*Is an integer in array value which should contain the Y axis cord.*/
private        int    field    = 0; /*Is an integer which should be 0 or 1 is the layer with cords.*/
private        int    rotation; /*Is an integer which contains the rotation of the ship possible is 0 and 90.*/

/**
 * KeyInput is the constructor.
 *
 * @param game   Is a reference to the game that was initialised when lunching the program.
 * @param player Is a reference to the player that was initialised when calling Game() methode.
 */
public KeyInput(Game game, Player player) {

	this.player = player; this.game = game;
}


/**
 * keyPressed is a methode that executes if a key is pressed belongs to the KeyAdapter class. The Methode contains all
 * actions that should happen if a button at the keyboard is pressed.
 */
public void keyPressed(KeyEvent e) {

	int key = e.getKeyCode();

	if(key == KeyEvent.VK_ESCAPE)
		System.exit(1); /*If statement check if ESC button pressed if it will exit program.*/

	if(Game.STARTUP) { /*If statement check if game is on STARTUP*/
		HUD.setMessageAll("Set X,Y,Dir. shipType: " + shipType);

		if(key == KeyEvent.VK_NUMPAD1 || key == KeyEvent.VK_1) {
			if(arrayX == 0) {
				arrayX = 1; HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 1; HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD2 || key == KeyEvent.VK_2) {
			if(arrayX == 0) {
				arrayX = 2; HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 2; HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD3 || key == KeyEvent.VK_3) {
			if(arrayX == 0) {
				arrayX = 3; HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 3; HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD4 || key == KeyEvent.VK_4) {
			if(arrayX == 0) {
				arrayX = 4; HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 4; HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD5 || key == KeyEvent.VK_5) {
			if(arrayX == 0) {
				arrayX = 5; HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 5; HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD6 || key == KeyEvent.VK_6) {
			if(arrayX == 0) {
				arrayX = 6; HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 6; HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD7 || key == KeyEvent.VK_7) {
			if(arrayX == 0) {
				arrayX = 7; HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 7; HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD8 || key == KeyEvent.VK_8) {
			if(arrayX == 0) {
				arrayX = 8; HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 8; HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD9 || key == KeyEvent.VK_9) {
			if(arrayX == 0) {
				arrayX = 9; HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 9; HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD0 || key == KeyEvent.VK_0) {
			if(arrayX == 0) {
				arrayX = 10; HUD.setMessageAll("X set.");
			} else if(arrayY == 0) {
				arrayY = 10; HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
			if(rotation == 0) {
				rotation = 1; HUD.setMessageAll("Rotation set.");
			}
		} else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			if(rotation == 0) {
				rotation = 90; HUD.setMessageAll("Rotation set.");
			}
		}

		/*Position status fields.*/
		HUD.setMessageArrayX(arrayX); HUD.setMessageArrayY(arrayY); HUD.setMessageRotation(rotation);
		HUD.setMessageShipType(shipType);

		if(arrayX != 0 && arrayY != 0 && rotation != 0) { /*If statement check if required integers are entered.*/
			/*Changes values that they are usable.*/
			if(rotation == 1)
				rotation = 0; arrayX--; arrayY--;

			/*If statement will check if it is possible to set a ship.*/
			if(player.setShip(arrayX, arrayY, field, shipType, rotation)) {
				HUD.setMessageAll(" "); rotation = 0; arrayX = 0; arrayY = 0; shipType++;
				if(shipType > 5 && field == 0) {
					HUD.setMessageAll("Player 2 pls"); shipType = 1; field = 1; game.gameState = STATE.GamePlayer2;
				} else if(shipType > 5 && field == 1) {
					HUD.setMessageAll("Player 1 attack"); Game.STARTUP = false; game.gameState = STATE.GamePlayer1;
				}
			} else {
				HUD.setMessageAll("Non valid Cords"); rotation = 0; arrayX = 0; arrayY = 0;
			}
		}
	}

}

}


