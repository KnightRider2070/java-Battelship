package de.thecoder.main;

import java.awt.*;

public abstract class GameObject {

public int pixelX, pixelY; /*pixelX, pixelY  is a integer in pixel which should contain the X,Y axis cord.*/
protected ID  id; /*Is the id that is assigned to the Player contained int the ID.java file.*/
protected int velX, velY; /*velX, velY velocity means how fast object is moving. Needs to be used in tick methode.*/

/**
 * GameObject is the constructor class for Game objects. It also is the base for all ships.
 *
 * @param pixelX Is a integer in pixel which should contain the X axis cord.
 * @param pixelY Is a integer in pixel which should contain the Y axis cord.
 * @param id     Is the id that is assigned to the Player contained int the ID.java file.
 */
public GameObject(int pixelX, int pixelY, ID id) {

	this.pixelX = pixelX; this.pixelY = pixelY; this.id = id;
}


/**
 * getX is a getter method. The Methode will get the X axis cords.
 *
 * @return Is an integer with the X axis pixel cords.
 */
public int getX() {

	return pixelX;
}

/**
 * setX is a setter method. The Methode will set the X axis cords.
 *
 * @param pixelX Is an integer with the X axis pixel cords.
 */
public void setX(int pixelX) {

	this.pixelX = pixelX;
}

/**
 * getY is a getter method. The Methode will get the Y axis cords.
 *
 * @return Is an integer with the Y axis pixel cords.
 */
public int getY() {

	return pixelY;
}

/**
 * getY is a setter method. The Methode will set the Y axis cords.
 *
 * @param pixelY Is an integer with the Y axis pixel cords.
 */
public void setY(int pixelY) {

	this.pixelY = pixelY;
}

/**
 * getId is a getter method. The Methode will get the id.
 *
 * @return Is an integer with id.
 */
public ID getId() {

	return id;
}


/**
 * setId is a setter method. The Methode will set the id.
 *
 * @param id Is an integer with id.
 */
public void setId(ID id) {

	this.id = id;
}

/**
 * getVelX is a getter method. The Methode will get the velX.
 *
 * @return Is an integer with velX the X axis acceleration.
 */
public int getVelX() {

	return velX;
}

/**
 * setVelX is a setter method. The Methode will set the velX.
 *
 * @param velX Is an integer with velX the X axis acceleration.
 */
public void setVelX(int velX) {

	this.velX = velX;
}

/**
 * setVelY is a getter method. The Methode will get the velY.
 *
 * @return Is an integer with velX the Y axis acceleration.
 */
public int getVelY() {

	return velY;
}

/**
 * setVelY is a setter method. The Methode will set the velY.
 *
 * @param velY Is an integer with velX the Y axis acceleration.
 */
public void setVelY(int velY) {

	this.velY = velY;
}

/**
 * The methode tick called every tick the tick is defined in Game.run(). If it should be used it needs to be added to
 * Game.tick().
 */
public abstract void tick();

/**
 * The methode render is called every run the run is defined in Game.run().
 */
public abstract void render(Graphics g);

}
