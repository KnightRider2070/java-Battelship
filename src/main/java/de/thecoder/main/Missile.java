package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //

import java.awt.*;

public class Missile extends GameObject {

    // ---------------------------------------- Global Variables ---------------------------------------- //

    //Game object handler reference.
    Handler handler;
    //The amount how long the missile should be visible.
    double durationOfAnimation = 0.02;
    //The amount the missile should be visible.
    private float alpha = 1;

    // ---------------------------------------- Initialising Methods ---------------------------------------- //

    /*
     * The Missile constructor which creates a missile at a specific position.
     * The parameter handler gets assigned and the parameter hud.
     * @param pixelX         Is an integer in pixel which should contain the X axis cord.
     * @param pixelY         Is an integer in pixel which should contain the Y axis cord.
     * @param id             Is the id that is assigned to the Player contained int the ID.java file.
     * @param handler        Is the handler that is assigned it should be the handler that is initialised in the Game class.
     */
    public Missile(int pixelX, int pixelY, ID id, Handler handler) {
        //Is the super constructed defined in the GameObject class.
        super(pixelX, pixelY, id);
        //Assigns the handler from the Game class to the handler in the Player class.
        this.handler = handler;
    }

    /*
     *The methode tick called every tick the tick is defined in Game.run().
     */
    public void tick() {
        if (alpha > durationOfAnimation) {
            alpha -= (durationOfAnimation - 0.0001f);
        } else handler.removeObject(this);
    }

    // ---------------------------------------- Logic Methods ---------------------------------------- //

    /*
     * makeTransparent is used to control the visibility.
     * @param alpha          Is a float that contains the amount that the object should be made transparent.
     */
    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    // ---------------------------------------- Initialising Methods ---------------------------------------- //

    /*
     *The methode render is called every run the run is defined in Game.run().
     */
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 25, 25);
    }
}
