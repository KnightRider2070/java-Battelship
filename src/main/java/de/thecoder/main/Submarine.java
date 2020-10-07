package de.thecoder.main;


import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Submarine extends GameObject {


private final int           pixelX; /*Is a integer in pixel which should contain the X axis cord.*/
private final int           pixelY; /*Is a integer in pixel which should contain the Y axis cord.*/
private final int           rotation; /*Is an integer which should the rotation of the ship possible is 0 and 90.*/
private       InputStream   inputStream;
private       BufferedImage image;


/**
 * The Submarine constructor which creates a submarine at a specific position. The parameter handler gets assigned and
 * the parameter hud.
 *
 * @param pixelX   Is an integer in pixel which should contain the X axis cord.
 * @param pixelY   Is an integer in pixel which should contain the Y axis cord.
 * @param rotation Is an integer which contains the rotation of the ship possible is 0 and 90.
 * @param id       Is the id that is assigned to the Player contained int the ID.java file.
 */
public Submarine(int pixelX, int pixelY, int rotation, ID id) {

	super(pixelX, pixelY, id);
	this.pixelX   = pixelX;
	this.pixelY   = pixelY;
	this.rotation = rotation;
}


/**
 * The methode will get the image.
 *
 * @param rotated The rotation of the ship.
 */
private void getImage(String rotated) {

	if(rotated.equals("0"))
		inputStream = getClass().getClassLoader().getResourceAsStream("images/ship_images/ship_submarine.png");
	if(rotated.equals("90"))
		inputStream = getClass().getClassLoader().getResourceAsStream("images/ship_images/ship_submarine_rotated.png");

	try {
		image = ImageIO.read(inputStream);
	} catch(IOException exception) {
		exception.printStackTrace();
	}
}


/**
 * The methode tick called every tick the tick is defined in Game.run(). If it should be used it needs to be added to
 * Game.tick().
 */
public void tick() {

}

/**
 * The methode render is called every run the run is defined in Game.run().
 */
public void render(Graphics g) {

	if(rotation == 0) {
		getImage("0");
		g.drawImage(image, pixelX, pixelY, null);
	} else if(rotation == 90) {
		getImage("90");
		g.drawImage(image, pixelX, pixelY, null);
	}

}

}
