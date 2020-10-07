package de.thecoder.main;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Carrier extends GameObject {


private final int           pixelX;  /*Is a integer in pixel which should contain the X axis cord.*/
private final int           pixelY;    /*Is an integer in pixel which should contain the Y axis cord.*/
private final int           rotation; /*Is an integer which should the rotation of the ship possible is 0 and 90.*/
private       InputStream   inputStream;
private       BufferedImage image;

/**
 * The Carrier constructor which creates a Carrier at a specific position.
 *
 * @param pixelX   Is an integer in pixel which should contain the X axis cord.
 * @param pixelY   Is an integer in pixel which should contain the Y axis cord.
 * @param rotation Is an integer which contains the rotation of the ship possible is 0 and 90.
 * @param id       Is the id that is assigned to the Player contained int the ID.java file.
 */
public Carrier(final int pixelX, final int pixelY, final int rotation, final ID id) {

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
		inputStream = getClass().getClassLoader().getResourceAsStream("images/ship_images/ship_carrier.png");
	if(rotated.equals("90"))
		inputStream = getClass().getClassLoader().getResourceAsStream("images/ship_images/ship_carrier_rotated.png");

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


