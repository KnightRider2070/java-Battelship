package de.thecoder.main;


import java.awt.Color;
import java.awt.Graphics;

public class Battleship extends GameObject {


private final int pixelX;  /*Is a integer in pixel which should contain the X axis cord.*/
private final int pixelY;    /*Is an integer in pixel which should contain the Y axis cord.*/
private final int rotation; /*Is an integer which should the rotation of the ship possible is 0 and 90.*/


/**
 * The Battleship constructor which creates a Battleship at a specific position.
 *
 * @param pixelX   Is an integer in pixel which should contain the X axis cord.
 * @param pixelY   Is an integer in pixel which should contain the Y axis cord.
 * @param rotation Is an integer which contains the rotation of the ship possible is 0 and 90.
 * @param id       Is the id that is assigned to the Player contained int the ID.java file.
 */
public Battleship(final int pixelX, final int pixelY, final int rotation, final ID id) {

	super(pixelX, pixelY, id); this.pixelX = pixelX; this.pixelY = pixelY; this.rotation = rotation;
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

	g.setColor(Color.magenta); if(rotation == 0) {
		g.fillRect(pixelX, pixelY, 200, 50);
	} else if(rotation == 90) {
		g.fillRect(pixelX, pixelY, 50, 200);
	}
}

}
