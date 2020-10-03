package de.thecoder.main;


import java.awt.*;

public class Submarine extends GameObject {


/*pixelX is final because it shouldn't be possible to change it after signing. It contains the X axis cords in
pixel.*/
private final int pixelX;
/*pixelY is final because it shouldn't be possible to change it after signing. It contains the Y axis cords in pixel.*/
private final int pixelY;
/*rotation is final because it shouldn't be possible to change it after signing. It contains the rotation of the ship
 possible is 0 and 90.*/
private final int rotation;
/*Game object handler reference*/ Handler handler;


/*
 * The Submarine constructor which creates a submarine at a specific position.
 * The parameter handler gets assigned and the parameter hud.
 * @param pixelX         Is an integer in pixel which should contain the X axis cord.
 * @param pixelY         Is an integer in pixel which should contain the Y axis cord.
 * @param rotation       Is an integer which contains the rotation of the ship possible is 0 and 90.
 * @param id             Is the id that is assigned to the Player contained int the ID.java file.
 * @param handler        Is the handler that is assigned it should be the handler that is initialised in the Game class.
     */
    public Submarine(int pixelX, int pixelY, int rotation, ID id, Handler handler) {
        /*Is the super constructed defined in the GameObject class.*/
        super(pixelX, pixelY, id);
        /*Assigns the handler from the Game class to the handler in the Player class.*/
        this.handler = handler;
        /*Assigns the pixelX from the constructor to the pixelX parameter from the Submarine class.*/
        this.pixelX = pixelX;
        /*Assigns the pixelY from the constructor to the pixelY parameter from the Submarine class.*/
        this.pixelY = pixelY;
        /*Assigns the rotation from the constructor to the rotation parameter from the Submarine class.*/
        this.rotation = rotation;
    }

    /*
     *The methode tick called every tick the tick is defined in Game.run().
     * If it should be used it needs to be added to Game.tick().
     */
    public void tick() {

    }

    /*
     *The methode render is called every run the run is defined in Game.run().
     */
    public void render(Graphics g) {
        g.setColor(Color.PINK);
        if (rotation == 0) {
            g.fillRect(pixelX, pixelY, 150, 50);
        } else if (rotation == 90) {
            g.fillRect(pixelX, pixelY, 50, 150);
        }
    }

}
