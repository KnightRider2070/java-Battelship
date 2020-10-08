package de.thecoder.main;


import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * The enum contains possible games states that effect the game differently.
 */
enum STATE {
	/**
	 * GameEnd is set when one player has won.
	 */
	GameEnd,
	/**
	 * GamePlayer1 is set when player one plays the game.
	 */
	GamePlayer1,
	/**
	 * GamePlayer2 is set when player two plays the game.
	 */
	GamePlayer2,
	/**
	 * Menu that shows before the gameplay
	 */
	Menu,
	/**
	 * Help screen that shows up
	 */
	Help,
}

public class Game extends Canvas implements Runnable {


public static  int[][][] shipPosition        = new int[10][10][2]; /*shipPosition, ship location of players.*/
public static  boolean   STARTUP             = true; /*STARTUP to check if game is in STARTUP state.*/
public static  STATE     gameState           = STATE.Menu; /*Game state. Player one begins.*/
private static int       shipsAlivePlayerOne = 5;
private static int       shipsAlivePlayerTwo = 5;
private final  int       WIDTH               = 1550, HEIGHT = 750; /*HEIGHT,WIDTH of the game window.*/
private final Handler handler; /*References the handler to a Handler.*/
private final HUD     hud; /*References the hud to a Hud.*/
private final Help    help; /*Reference the help page to Help*/
private final Menu    menu;/*Reference the menu page to Menu*/
private       Thread  thread; /*References the thread to a Thread.*/
private       boolean running = false; /*running to check of the game is in running state.*/

/**
 * The Methode is the constructor that initialises all necessary objects for the gameplay.
 */
public Game() {

	help    = new Help(); /*Assigns the help page to a new Help.*/
	menu    = new Menu();
	handler = new Handler(this);   /*Assigns the handler a new Handler.*/
	hud     = new HUD(); /*Assigns the hud a new Hud.*/
	Player player = new Player(1, 1, ID.Player, handler, this); /*Adds the player to the game.*/
	this.addKeyListener(new KeyInput(this, player)); /*Creating listener for Key input.*/
	this.addMouseListener(new MouseInput(this, player)); /*Creating listener for Mouse input.*/
	this.addMouseListener(menu);/*Creating listener for menu Mouse input.*/
	this.addMouseListener(help);/*Creating listener for help Mouse input.*/

	new Window(WIDTH, HEIGHT, "Battelship", this); /*Creating the Window with the game in it.*/

}

/**
 * The methode is to start the game in Java.
 */
public static void main(String[] args) {

	new Game();
}


/**
 * The methode converts pixels to cords that can be used when referencing to the shipPosition array and access it.
 *
 * @param pixelX Is an integer in pixel which should contain the X axis cord.
 * @param pixelY Is an integer in pixel which should contain the Y axis cord.
 *
 * @return Is an array that contains 3 values x,y,field.
 */
public static int[] pixelsToCord(int pixelX, int pixelY) {

	int[] tempXYF = new int[3]; /*tempXYF array which contains x,y,field for return.*/
	int   field   = 0;    /*field is an integer which should contain the field for return.*/
	int   convX   = 0;    /*convX is an integer which should contain the array X axis cord for return.*/
	int   convY   = 0;    /*convY is an integer which should contain the array Y axis cord for return.*/


	if(pixelX < 500) {  /*If statement will checks if the pixelX is smaller that 500 this means it is field 0.*/

		/* Start i, end z, j array cords. 50 = one field.*/
		for(int i = 0, z = 50, j = 0; z <= 500; i += 50, z += 50, j++) {

			if(pixelX >= i && pixelX <= z) { /*If statement will check if the cords are in the possible field.*/
				convX = j;
				break;
			}
		}

		/*Equals code above only the field beginning has changed.*/
		for(int i = 100, z = 150, j = 0; z <= 600; i += 50, z += 50, j++) {
			if(pixelY >= i && pixelY <= z) {
				convY = j;
				break;
			}
		}

		/*Assigns the tempXYF array the calculated coordinates. No field assignment its already 0.*/
		tempXYF[0] = clamp(convX, 0, 10);
		tempXYF[1] = clamp(convY, 0, 10);

		return tempXYF;

		/*Equals code above only the field has changed.*/
	} else if(pixelX > 1000) {
		field = 1;

		for(int i = 1000, z = 1050, j = 0; z <= 1500; i += 50, z += 50, j++) {
			if(pixelX >= i && pixelX <= z) {
				convX = j;
				break;
			}
		}

		for(int i = 100, z = 150, j = 0; z <= 600; i += 50, z += 50, j++) {
			if(pixelY >= i && pixelY <= z) {
				convY = j;
				break;
			}
		}

		tempXYF[0] = clamp(convX, 0, 10);
		tempXYF[1] = clamp(convY, 0, 10);
		tempXYF[2] = field;

		return tempXYF;
	} else {
		System.out.println("Error when converting pixels to cords! Not in the field.");
		System.exit(1);
		return null;
	}
}

/**
 * The methode converts array coordinates to pixel. This allows you to create objects ore perform actions in the JFrame
 * with cords.
 *
 * @param arrayX Is an integer in array value which should contain the X axis cord.
 * @param arrayY Is an integer in array value which should contain the Y axis cord.
 * @param field  Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
 *
 * @return Is an array that contains 2 values x,y
 */
public static int[] cordsToPixels(int arrayX, int arrayY, int field) {

	int[] tempXY = new int[2];   /*tempXY array contains x,y pixel for return.*/

	if(field == 0) {
		tempXY[0] = clamp(arrayX * 50, 0, 500); /*Multiplied by 50, cuz field 50 pixel big.*/

		tempXY[1] = clamp(arrayY * 50 + 100, 100, 600); /* 100 cuz the field starts after 100 pixel.*/

		return tempXY;
		/*Equals code above only the field has changed.*/
	} else if(field == 1) {
		tempXY[0] = clamp(arrayX * 50 + 1000, 1000, 1500);
		tempXY[1] = clamp(arrayY * 50 + 100, 100, 600);
		return tempXY;
	} else {
		System.out.println("ERROR: Entered wrong field. Methode: cordsToPixels");
		System.exit(1);
		return null;
	}
}

/**
 * The methode allows it to check if the entered shipType is a valid ship type.
 *
 * @param shipType Is an integer which should be 1/2/3/4/5 for each ship type.
 */
public static boolean validateShipType(int shipType) {

	return shipType >= 1 && shipType <= 5;
}

/**
 * The methode converts a ship type to an object so that you can create a ship with only the shipType and cords.
 *
 * @param arrayX   Is an integer in array value which should contain the X axis cord.
 * @param arrayY   Is an integer in array value which should contain the Y axis cord.
 * @param field    Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
 * @param shipType Is an integer which should be 1/2/3/4/5 for each ship Type.
 * @param rotation Is an integer which should the rotation of the ship possible is 0 and 90.
 * @param handler  Is the handler that is assigned it should be the handler that is initialized in the Game class.
 *
 * @return Returns a new ship that is created at given cords. It hasn't been added to the handler.
 */
public static GameObject shipTypeToObject(int arrayX, int arrayY, int field, int shipType, int rotation,
                                          Handler handler) {


	int[] arrayCords = Game.cordsToPixels(arrayX, arrayY, field); /*arrayCords contain the x,y pixel cords*/

	assert arrayCords != null;
	int pixelX = arrayCords[0];  /*pixelX value from array assigned which contains the pixel X axis value.*/
	int pixelY = arrayCords[1];  /*pixelY value from array assigned which contains the pixel Y axis value.*/


	if(shipType == 1)   /*If statement will checks which ship type is given.*/
		return new Carrier(pixelX, pixelY, rotation, ID.Carrier);
	if(shipType == 2)
		return new Battleship(pixelX, pixelY, rotation, ID.Battleship);
	if(shipType == 3)
		return new Cruiser(pixelX, pixelY, rotation, ID.Cruiser);
	if(shipType == 4)
		return new Submarine(pixelX, pixelY, rotation, ID.Submarine);
	if(shipType == 5)
		return new Destroyer(pixelX, pixelY, rotation, ID.Destroyer);

	System.out.println("ERROR: Entered the wrong shipType Methode: shipTypeToObject");
	return null;
}

/**
 * The methode converts the ship type to the size of the ship. That could be used if u want to know how long your ship
 * is.
 *
 * @param shipType Is an integer which should be 1/2/3/4/5 for each ship Type.
 *
 * @return Returns the size of the ship depends on the ship type.
 */
public static int shipTypeToSize(int shipType) {

	if(shipType == 1 || shipType == 11) /*If statement will check if the ship is a ship or destroyed ship.*/
		return 5;
	if(shipType == 2 || shipType == 22)
		return 4;
	if(shipType == 3 || shipType == 33)
		return 3;
	if(shipType == 4 || shipType == 44)
		return 3;
	if(shipType == 5 || shipType == 55)
		return 2;

	System.out.println("ERROR: Entered the wrong shipType Methode: shipTypeToSize");
	return 0;
}

/**
 * The methode allows it to check if a value exceed a specific value. As example if your value exceeds the max then it
 * will be set to the max possible value. Helpful for a health bar as example.
 *
 * @param var Is an integer that should contain the value to check.
 * @param min Is an integer that should contain the minimal possible value.
 * @param max Is an integer that should contain the maximal possible value.
 *
 * @return Returns the checked car and maybe corrected var.
 */
public static int clamp(int var, int min, int max) {

	if(var >= max)  /*If statement will check if the var is bigger or equals the max value.*/
		return var = max;  /*Returns the max value cuz var exceeded it or was equal.*/
	else if(var <= min)    /*If statement will check if the var is smaller or equals the mun value.*/
		return var = min;   /*Returns the min value cuz var exceeded it or was equal.*/
	else  /*If the value is not equal min or max and do not exceed those you will get it back.*/
		return var; /*Returns the var untouched.*/
}

/**
 * The methode allows it to get the value of the shipsAlivePlayerOne integer.
 *
 * @return Returns the shipsAlivePlayerOne integer value.
 */
public static int getShipsAlivePlayerOne() {

	return Game.shipsAlivePlayerOne;
}

/**
 * The methode allows it to set the value of shipsAlivePlayerOne integer.
 *
 * @param shipsAlivePlayerOne Is an integer that should be assigned to the int.
 */
public static void setShipsAlivePlayerOne(int shipsAlivePlayerOne) {

	Game.shipsAlivePlayerOne = clamp(shipsAlivePlayerOne, 0, 5);
}

/**
 * The methode allows it to get the value of the shipsAlivePlayerTwo integer.
 *
 * @return Returns the shipsAlivePlayerTwo integer value.
 */
public static int getShipsAlivePlayerTwo() {

	return Game.shipsAlivePlayerTwo;
}

/**
 * The methode allows it to set the value of shipsAlivePlayerTwo integer.
 *
 * @param shipsAlivePlayerTwo Is an integer that should be assigned to the int.
 */
public static void setShipsAlivePlayerTwo(int shipsAlivePlayerTwo) {

	Game.shipsAlivePlayerTwo = clamp(shipsAlivePlayerTwo, 0, 5);
}

/**
 * The methode starts the Thread this means the game is able to run in one thread.
 */
public synchronized void start() {

	thread = new Thread(this); /*Creates a new Thread and targets this Game class.*/
	thread.start();
	running = true;
}

/**
 * The methode stops the Thread this means the game is able to stop when called.
 */
public synchronized void stop() {

	try {  /*Tries to stop the thread excepting errors.*/

		thread.join(); /*Kills the Thread.*/

		running = false;

	} catch(Exception e) { /*Catches Exceptions before it will effect the process.*/

		e.printStackTrace(); /*Prints the error out.*/
	}
}

/**
 * The methode contains some super secret calculations for fps and game time. Isn't being explained maybe figure it out
 * yourself.
 */
public void run() {

	this.requestFocus();
	long   lastTimeCode  = System.nanoTime();
	double amountOfTicks = 60.0;
	double ns            = 1000000000 / amountOfTicks;
	double delta         = 0;
	long   timer         = System.currentTimeMillis();
	int    frames        = 0;
	while(running) {
		long now = System.nanoTime();
		delta += (now - lastTimeCode) / ns;
		lastTimeCode = now;
		while(delta >= 1) {
			tick();
			delta--;
		}
		if(running)
			render();
		frames++;

		if(System.currentTimeMillis() - timer > 1000) {
			timer += 1000;
			System.out.println("FPS: " + frames);
			frames = 0;
		}
	}
	stop();
}

/**
 * The methode is the tick methode that will be executed every tick. This tick methode u can call the super tick
 * methode, cuz only if you write your thick methode. Objects that are created through the handler will be ticked and
 * rendered without naming them cuz it's done in the handler class.
 */
private void tick() {

	handler.tick();
	hud.tick();
}

/**
 * The methode allows it the game to show all graphics and render them it can be found in every object class. Those
 * object render classes has to be added to this super render class if they should be executed. The objects that are
 * created through the handler shouldn't be added cuz they are rendered through the handler class render methode.
 */
private void render() {

	BufferStrategy bs = this.getBufferStrategy();  /*Is a buffer stores drawn graphics.*/
	if(bs == null) {
		this.createBufferStrategy(3);
		return;
	}

	Graphics g = bs.getDrawGraphics();

	g.setColor(Color.BLACK); /*Background*/

	g.fillRect(0, 0, WIDTH, HEIGHT); /*Where to start painting black.*/

	if(gameState != STATE.Menu && gameState != STATE.Help) {
		/*The STARTUP input interface.*/
		if(STARTUP) { /*If statement will check if the program is in STARTUP.*/
			/*Fonts that are use.*/
			Font font  = new Font("Ariel", Font.PLAIN, 25);
			Font font2 = new Font("Ariel", Font.PLAIN, 50);
			/*Headline*/
			g.setFont(font);
			g.setColor(Color.ORANGE);
			g.drawRect(645, 95, 210, 30);
			g.setColor(Color.white);
			g.drawString("Setup Input", 685, 120);


			/*First Line*/
			g.setFont(font2);
			g.setColor(Color.orange);
			g.drawRect(645, 150, 50, 50);
			g.drawRect(725, 150, 50, 50);
			g.drawRect(805, 150, 50, 50);
			g.setColor(Color.white);
			g.drawString("1", 655, 195);
			g.drawString("2", 735, 195);
			g.drawString("3", 815, 195);

			/*Second Line*/
			g.setColor(Color.orange);
			g.drawRect(645, 220, 50, 50);
			g.drawRect(725, 220, 50, 50);
			g.drawRect(805, 220, 50, 50);
			g.setColor(Color.white);
			g.drawString("4", 655, 265);
			g.drawString("5", 735, 265);
			g.drawString("6", 815, 265);
			/*Third Line*/
			g.setColor(Color.orange);
			g.drawRect(645, 290, 50, 50);
			g.drawRect(725, 290, 50, 50);
			g.drawRect(805, 290, 50, 50);
			g.setColor(Color.white);
			g.drawString("7", 655, 335);
			g.drawString("8", 735, 335);
			g.drawString("9", 815, 335);
			/*Fourth Line*/
			g.setColor(Color.ORANGE);
			g.drawRect(645, 360, 210, 50);
			g.setColor(Color.white);
			g.drawString("10", 720, 405);


			/*Fifth Line*/
			g.setFont(font);
			g.setColor(Color.ORANGE);
			g.drawString("Rotations:", 645, 440);
			g.setColor(Color.ORANGE);
			g.drawRect(645, 450, 95, 50);
			g.drawRect(760, 450, 95, 50);
			g.setColor(Color.white);
			g.setFont(font2);
			g.drawString("0", 680, 495);
			g.drawString("90", 780, 495);


		}

		/*First Field*/
		/*Size of one cell.*/
		int cellSize = 50;
		for(int lx = 0; lx < 500; lx += 50) { /*Width 500px starts at 0px ends at 500px*/
			for(int ly = 100; ly < 600; ly += 50) { /*Height 500px but starts at 100px so ends at 600px*/
				g.setColor(Color.cyan);
				g.drawRect(lx, ly, cellSize, cellSize);
			}
		}
		/*Second Field*/
		for(int lx = 1000; lx < 1500; lx += 50) {  /*Width 500px starts at 1500px ends at 1000px*/
			for(int ly = 100; ly < 600; ly += 50) { /*Height 500px but starts at 100px so ends at 600px*/
				g.setColor(Color.green);
				g.drawRect(lx, ly, cellSize, cellSize);
			}
		}

		/*Calls render Methods from the other classes so that they will be rendered as well.*/
		handler.render(g);
		hud.render(g);

	}
	if(gameState == STATE.Help) {
		help.render(g);
	}
	if(gameState == STATE.Menu) {
		menu.render(g);
	}
	/*Shows the Objects in the buffer.*/
	g.dispose();
	bs.show();


}

}
