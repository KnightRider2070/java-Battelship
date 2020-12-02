package de.thecoder.main.Game;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {


private final Game   game;  /*Is a reference to the game that was initialised when lunching the program.*/
private final Player player; /*player reference final cuz shouldn't be possible to change.*/


/**
 * KeyInput is the constructor.
 *
 * @param game   Is a reference to the game that was initialised when lunching the program.
 * @param player Is a reference to the player that was initialised when calling Game() methode.
 */
public KeyInput(Game game, Player player) {

	this.player = player;
	this.game   = game;
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
		HUD.setMessageAll("Set X,Y,Dir. shipType: " + MouseInput.shipType);

		if(key == KeyEvent.VK_NUMPAD1 || key == KeyEvent.VK_1) {
			if(MouseInput.arrayX == 0) {
				MouseInput.arrayX = 1;
				HUD.setMessageAll("X set.");
			} else if(MouseInput.arrayY == 0) {
				MouseInput.arrayY = 1;
				HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD2 || key == KeyEvent.VK_2) {
			if(MouseInput.arrayX == 0) {
				MouseInput.arrayX = 2;
				HUD.setMessageAll("X set.");
			} else if(MouseInput.arrayY == 0) {
				MouseInput.arrayY = 2;
				HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD3 || key == KeyEvent.VK_3) {
			if(MouseInput.arrayX == 0) {
				MouseInput.arrayX = 3;
				HUD.setMessageAll("X set.");
			} else if(MouseInput.arrayY == 0) {
				MouseInput.arrayY = 3;
				HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD4 || key == KeyEvent.VK_4) {
			if(MouseInput.arrayX == 0) {
				MouseInput.arrayX = 4;
				HUD.setMessageAll("X set.");
			} else if(MouseInput.arrayY == 0) {
				MouseInput.arrayY = 4;
				HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD5 || key == KeyEvent.VK_5) {
			if(MouseInput.arrayX == 0) {
				MouseInput.arrayX = 5;
				HUD.setMessageAll("X set.");
			} else if(MouseInput.arrayY == 0) {
				MouseInput.arrayY = 5;
				HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD6 || key == KeyEvent.VK_6) {
			if(MouseInput.arrayX == 0) {
				MouseInput.arrayX = 6;
				HUD.setMessageAll("X set.");
			} else if(MouseInput.arrayY == 0) {
				MouseInput.arrayY = 6;
				HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD7 || key == KeyEvent.VK_7) {
			if(MouseInput.arrayX == 0) {
				MouseInput.arrayX = 7;
				HUD.setMessageAll("X set.");
			} else if(MouseInput.arrayY == 0) {
				MouseInput.arrayY = 7;
				HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD8 || key == KeyEvent.VK_8) {
			if(MouseInput.arrayX == 0) {
				MouseInput.arrayX = 8;
				HUD.setMessageAll("X set.");
			} else if(MouseInput.arrayY == 0) {
				MouseInput.arrayY = 8;
				HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD9 || key == KeyEvent.VK_9) {
			if(MouseInput.arrayX == 0) {
				MouseInput.arrayX = 9;
				HUD.setMessageAll("X set.");
			} else if(MouseInput.arrayY == 0) {
				MouseInput.arrayY = 9;
				HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_NUMPAD0 || key == KeyEvent.VK_0) {
			if(MouseInput.arrayX == 0) {
				MouseInput.arrayX = 10;
				HUD.setMessageAll("X set.");
			} else if(MouseInput.arrayY == 0) {
				MouseInput.arrayY = 10;
				HUD.setMessageAll("Y set.");
			}
		} else if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
			if(MouseInput.rotation == 0) {
				MouseInput.rotation = 1;
				HUD.setMessageAll("Rotation set.");
			}
		} else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			if(MouseInput.rotation == 0) {
				MouseInput.rotation = 90;
				HUD.setMessageAll("Rotation set.");
			}
		}

		/*Position status fields.*/
		HUD.setMessageArrayX(MouseInput.arrayX);
		HUD.setMessageArrayY(MouseInput.arrayY);
		HUD.setMessageRotation(MouseInput.rotation);
		HUD.setMessageShipType(MouseInput.shipType);

		if(MouseInput.arrayX != 0 && MouseInput.arrayY != 0 && MouseInput.rotation != 0) { /*If statement check if
		required integers are entered.*/
			/*Changes values that they are usable.*/
			if(MouseInput.rotation == 1)
				MouseInput.rotation = 0;
			MouseInput.arrayX--;
			MouseInput.arrayY--;

			/*If statement will check if it is possible to set a ship.*/
			if(player.setShip(MouseInput.arrayX, MouseInput.arrayY, MouseInput.field, MouseInput.shipType,
			                  MouseInput.rotation)) {
				HUD.setMessageAll(" ");
				MouseInput.rotation = 0;
				MouseInput.arrayX   = 0;
				MouseInput.arrayY   = 0;
				MouseInput.shipType++;
				if(MouseInput.shipType > 5 && MouseInput.field == 0) {
					HUD.setMessageAll("Player 2 pls");
					MouseInput.shipType = 1;
					MouseInput.field    = 1;
					Game.gameState      = STATE.GamePlayer2;
				} else if(MouseInput.shipType > 5 && MouseInput.field == 1) {
					HUD.setMessageAll("Player 1 attack");
					Game.STARTUP   = false;
					Game.gameState = STATE.GamePlayer1;
				}
			} else {
				HUD.setMessageAll("Non valid Cords");
				MouseInput.rotation = 0;
				MouseInput.arrayX   = 0;
				MouseInput.arrayY   = 0;
			}
		}
	}

}

}


