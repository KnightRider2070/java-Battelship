package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //

import java.awt.*;

public class MissileFragment extends GameObject {

    // ---------------------------------------- Global Variables ---------------------------------------- //

    //pixelX is final because it shouldn't be possible to change it after signing. It contains the X axis cords in pixel.
    private final int pixelX;
    //pixelY is final because it shouldn't be possible to change it after signing. It contains the Y axis cords in pixel.
    private final int pixelY;
    //Game object handler reference
    Handler handler;

    // ---------------------------------------- Initialising Methods ---------------------------------------- //


    public MissileFragment(int pixelX, int pixelY, ID id, Handler handler) {
        //Is the super constructed defined in the GameObject class.
        super(pixelX, pixelY, id);
        //Assigns the handler from the Game class to the handler in the Player class.
        this.handler = handler;
        //Assigns the pixelX from the constructor to the pixelX parameter from the MissileFragment class.
        this.pixelX = pixelX;
        //Assigns the pixelY from the constructor to the pixelY parameter from the MissileFragment class.
        this.pixelY = pixelY;
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
        g.setColor(Color.red);
        g.drawLine(pixelX, pixelY, pixelX + 50, pixelY + 50);
    }
}
