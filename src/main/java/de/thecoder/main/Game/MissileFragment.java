package de.thecoder.main.Game;


import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.imageio.ImageIO;


public class MissileFragment extends GameObject {

private final int         pixelX;/* Is a integer in pixel which should contain the X axis cord.*/
private final int         pixelY;/* Is a integer in pixel which should contain the Y axis cord.*/
private final String      color;/*Is a String which should contain the color, possible green, red.*/
private       InputStream inputStream;
private       Image       image;


public MissileFragment(int pixelX, int pixelY, String color, ID id) {

	super(pixelX, pixelY, id);
	this.pixelX = pixelX;
	this.pixelY = pixelY;
	this.color  = color;
}

/**
 * The methode tick called every tick the tick is defined in Game.run(). If it should be used it needs to be added to
 * Game.tick().
 */
public void tick() {

}

/**
 * The methode will get the image.
 *
 * @param color This is the color of the cross.
 */
private void getImage(String color) {

	if(color.equals("red"))
		inputStream = getClass().getClassLoader().getResourceAsStream("images/ship_status/hit.png");
	if(color.equals("green"))
		inputStream = getClass().getClassLoader().getResourceAsStream("images/ship_status/miss.png");

	try {
		image = ImageIO.read(Objects.requireNonNull(inputStream));
	} catch(IOException exception) {
		exception.printStackTrace();
	}
}

/**
 * The methode render is called every run the run is defined in Game.run().
 */
public void render(Graphics g) {

	if(color.equals("red"))
		getImage("red");
	if(color.equals("green"))
		getImage("green");

	g.drawImage(image, pixelX, pixelY, null);
}

}
