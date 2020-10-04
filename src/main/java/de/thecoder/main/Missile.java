package de.thecoder.main;


import java.awt.*;

public class Missile extends GameObject {

private final Handler handler; /*Is the handler that is assigned it should be the handler that is initialized in the
Game class.*/
double durationOfAnimation = 0.02; /*The amount how long the missile should be visible.*/
private float alpha = 1;/*The amount the missile should be visible.*/


/**
 * The Missile constructor which creates a missile at a specific position.
 *
 * @param pixelX  Is an integer in pixel which should contain the X axis cord.
 * @param pixelY  Is an integer in pixel which should contain the Y axis cord.
 * @param id      Is the id that is assigned to the Player contained int the ID.java file.
 * @param handler Is the handler that is assigned it should be the handler that is initialised in the Game class.
 */
public Missile(int pixelX, int pixelY, ID id, Handler handler) {

	super(pixelX, pixelY, id); this.handler = handler;
}

/*
 *The methode tick called every tick the tick is defined in Game.run().
 */
public void tick() {

	/*This lets the missile disappear.*/
	if(alpha > durationOfAnimation) {
		alpha -= (durationOfAnimation - 0.0001f);
	} else
		handler.removeObject(this);
}


/**
 * The methode is used to control the visibility.
 *
 * @param alpha Is a float that contains the amount that the object should be made transparent.
 */
private AlphaComposite makeTransparent(float alpha) {

	int type = AlphaComposite.SRC_OVER; return (AlphaComposite.getInstance(type, alpha));
}


/**
 * The methode render is called every run the run is defined in Game.run().
 */
public void render(Graphics g) {

	g.setColor(Color.red); g.fillRect(pixelX, pixelY, 25, 25);
}

}
