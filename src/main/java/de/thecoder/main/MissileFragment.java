package de.thecoder.main;


import java.awt.*;

public class MissileFragment extends GameObject {

private final int     pixelX;/* Is a integer in pixel which should contain the X axis cord.*/
private final int     pixelY;/* Is a integer in pixel which should contain the Y axis cord.*/
private final String  color;/*Is a String which should contain the color, possible green, red.*/
private final Handler handler;/* Is the handler that is assigned it should be the handler that is initialized in the
Game class.*/


public MissileFragment(int pixelX, int pixelY, String color, ID id, Handler handler) {

	super(pixelX, pixelY, id); this.handler = handler; this.pixelX = pixelX; this.pixelY = pixelY; this.color = color;
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

	if(color.equals("red"))
		g.setColor(Color.red); if(color.equals("green"))
		g.setColor(Color.green); g.drawLine(pixelX, pixelY, pixelX + 50, pixelY + 50);
}

}
