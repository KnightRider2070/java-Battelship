package de.thecoder.main;

import java.awt.Graphics;

public class KiPlayer extends GameObject{

/**
 * GameObject is the constructor class for Game objects. It also is the base for all ships.
 *
 * @param pixelX Is a integer in pixel which should contain the X axis cord.
 * @param pixelY Is a integer in pixel which should contain the Y axis cord.
 * @param id     Is the id that is assigned to the Player contained int the ID.java file.
 */
public KiPlayer(int pixelX, int pixelY, ID id) {

	super(pixelX, pixelY, id);
}

public void tick() {

}

public void render(Graphics g) {

}

}
