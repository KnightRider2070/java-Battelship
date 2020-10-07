package de.thecoder.main;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

public static String messageOne;  /*Is the message String for the first player.*/
public static String messageTwo; /*Is the message String for the second player.*/
public static String messageAll; /*Is the message String for all players during STARTUP.*/
public static int    arrayXset; /*Is the message String that shows if X is set.*/
public static int    arrayYset; /*Is the message String that shows if Y is set.*/
public static int    rotationSet; /*Is the message String that shows if rotation is set.*/
public static int    shipTypeNow; /*Is the message String that shows if the ship type.*/

public HUD() {}


/**
 * setMessageOne is a setter methode. The Methode sets the message for player ones Display
 *
 * @param message Is a string that should contain the message to display
 */
public static void setMessageOne(String message) {

	messageOne = message;
}

/**
 * setMessageTwo is a setter methode. The Methode sets the message for player twos Display
 *
 * @param message Is a string that should contain the message to display
 */
public static void setMessageTwo(String message) {

	messageTwo = message;
}

/**
 * setMessageAll is a setter methode. The Methode sets the message for all players during STARTUP.
 *
 * @param message Is a string that should contain the message to display
 */
public static void setMessageAll(String message) {

	messageAll = message;
}

/**
 * setMessageArrayX is a setter methode. The Methode sets the status message for all players during STARTUP.
 *
 * @param message Is a string that should contain the message to display
 */
public static void setMessageArrayX(int message) {

	arrayXset = message;
}

/**
 * setMessageArrayY is a setter methode. The Methode sets the status message for all players during STARTUP.
 *
 * @param message Is a string that should contain the message to display
 */
public static void setMessageArrayY(int message) {

	arrayYset = message;
}

/**
 * setMessageAll is a setter methode. The Methode sets the status message for all players during STARTUP.
 *
 * @param message Is a string that should contain the message to display
 */
public static void setMessageRotation(int message) {

	rotationSet = message;
}

/**
 * setMessageShipType is a setter methode. The Methode sets the status message for all players during STARTUP.
 *
 * @param message Is a string that should contain the message to display
 */
public static void setMessageShipType(int message) {

	shipTypeNow = message;
}


/**
 * The methode tick called every tick the tick is defined in Game.run().
 */
public void tick() {

	if(messageOne == null)
		messageOne = " ";
	if(messageTwo == null)
		messageTwo = " ";
	if(messageAll == null)
		messageAll = " ";
}


/**
 * The methode render is called every run the run is defined in Game.run().
 */
public void render(Graphics g) {

	/*Player one info how many ships alive*/
	g.setColor(Color.GRAY);
	g.fillRect(50, 15, 170, 32);
	g.setColor(Color.BLUE);
	Font font = new Font("Ariel", Font.PLAIN, 25);
	g.setFont(font);
	g.drawString("Ships Alive: " + Game.getShipsAlivePlayerOne(), 55, 40);

	/*Player two info how many ships alive*/
	g.setColor(Color.GRAY);
	g.fillRect(1050, 15, 170, 32);
	g.setColor(Color.BLUE);
	g.setFont(font);
	g.drawString("Ships Alive: " + Game.getShipsAlivePlayerTwo(), 1055, 40);

	/*Player one message*/
	g.setColor(Color.CYAN);
	g.fillRect(50, 630, 400, 32);
	g.setFont(font);
	g.setColor(Color.BLACK);
	g.drawString("Message: " + messageOne, 55, 655);

	/*Player two message*/
	g.setColor(Color.GREEN);
	g.fillRect(1050, 630, 400, 32);
	g.setFont(font);
	g.setColor(Color.BLACK);
	g.drawString("Message: " + messageTwo, 1055, 655);

	/*All player message*/
	g.setColor(Color.ORANGE);
	g.fillRect(550, 630, 400, 40);
	g.setFont(font);
	g.setColor(Color.BLACK);
	g.drawString("Info: " + messageAll, 555, 660);


	if(Game.STARTUP) {
		/*ArrayX status message*/
		if(HUD.arrayXset >= 1)
			g.setColor(Color.green);
		else
			g.setColor(Color.red);
		g.fillRect(610, 520, 130, 30);
		g.setFont(font);
		g.setColor(Color.ORANGE);
		g.drawRect(610, 520, 130, 30);
		g.setColor(Color.BLACK);
		g.drawString("arrayX: " + arrayXset, 615, 542);

		/*ArrayY status message*/
		if(HUD.arrayYset >= 1)
			g.setColor(Color.green);
		else
			g.setColor(Color.red);
		g.fillRect(610, 570, 130, 30);
		g.setFont(font);
		g.setColor(Color.ORANGE);
		g.drawRect(610, 570, 130, 30);
		g.setColor(Color.BLACK);
		g.drawString("arrayY: " + arrayYset, 615, 592);

		/*Rotation status message*/
		if(HUD.rotationSet >= 1)
			g.setColor(Color.green);
		else
			g.setColor(Color.red);
		g.fillRect(760, 520, 130, 30);
		g.setFont(font);
		g.setColor(Color.ORANGE);
		g.drawRect(760, 520, 130, 30);
		g.setColor(Color.BLACK);
		g.drawString("rotation: " + rotationSet, 765, 542);

		/*ShipType status message*/
		if(HUD.shipTypeNow >= 1)
			g.setColor(Color.green);
		else
			g.setColor(Color.red);
		g.fillRect(760, 570, 130, 30);
		g.setFont(font);
		g.setColor(Color.ORANGE);
		g.drawRect(760, 570, 130, 30);
		g.setColor(Color.BLACK);
		g.drawString("ship: " + shipTypeNow, 765, 592);
	}
}

}
