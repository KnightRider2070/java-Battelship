package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //

import java.awt.*;

public abstract class GameObject {

    // ---------------------------------------- Global Variables ---------------------------------------- //

    //pixelX, pixelY are integers that should contain the position of an GlobalObject.
    protected int pixelX, pixelY;
    //id is the ID of an GlobalObject those are maintained in the ID class. (See GameDoc)
    protected ID id;
    //velX, velY is the velocity which means how fast an object is moving. Needs to be used in tick methode.
    protected int velX, velY;

    // ---------------------------------------- Initialising Methods ---------------------------------------- //

    /*
     * GameObject is the constructor class for Game objects.
     * @param pixelX         Is a integer in pixel which should contain the X axis cord.
     * @param pixelY         Is a integer in pixel which should contain the Y axis cord.
     * @param id             Is the id that is assigned to the Player contained int the ID.java file.
     */
    public GameObject(int pixelX, int pixelY, ID id) {
        //Assigns the pixelX from the constructor to the pixelX parameter from the GameObject class.
        this.pixelX = pixelX;
        //Assigns the pixelY from the constructor to the pixelY parameter from the GameObject class.
        this.pixelY = pixelY;
        //Assigns the id from the constructor to the id parameter from the GameObject class.
        this.id = id;
    }

    // ---------------------------------------- Setter Methods ---------------------------------------- //

    /*
     * getX is a getter method.
     * The Methode will get the X axis cords.
     * @param return          Is an integer with the X axis pixel cords.
     */
    public int getX() {
        return pixelX;
    }

    /*
     * setX is a setter method.
     * The Methode will set the X axis cords.
     * @param pixelX          Is an integer with the X axis pixel cords.
     */
    public void setX(int pixelX) {
        this.pixelX = pixelX;
    }

    /*
     * getY is a getter method.
     * The Methode will get the Y axis cords.
     * @param return          Is an integer with the Y axis pixel cords.
     */
    public int getY() {
        return pixelY;
    }

    /*
     * getY is a setter method.
     * The Methode will set the Y axis cords.
     * @param pixelY          Is an integer with the Y axis pixel cords.
     */
    public void setY(int pixelY) {
        this.pixelY = pixelY;
    }

    /*
     * getId is a getter method.
     * The Methode will get the id.
     * @param return          Is an integer with id.
     */
    public ID getId() {
        return id;
    }

    // ---------------------------------------- Getter Methods ---------------------------------------- //

    /*
     * setId is a setter method.
     * The Methode will set the id.
     * @param id          Is an integer with id.
     */
    public void setId(ID id) {
        this.id = id;
    }

    /*
     * getVelX is a getter method.
     * The Methode will get the velX.
     * @param return          Is an integer with velX the X axis acceleration.
     */
    public int getVelX() {
        return velX;
    }

    /*
     * setVelX is a setter method.
     * The Methode will set the velX.
     * @param velX          Is an integer with velX the X axis acceleration.
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }

    /*
     * setVelY is a getter method.
     * The Methode will get the velY.
     * @param return          Is an integer with velX the Y axis acceleration.
     */
    public int getVelY() {
        return velY;
    }

    /*
     * setVelY is a setter method.
     * The Methode will set the velY.
     * @param velY          Is an integer with velX the Y axis acceleration.
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }

    // ---------------------------------------- Runner Methods ---------------------------------------- //

    /*
     *The methode tick called every tick the tick is defined in Game.run().
     * If it should be used it needs to be added to Game.tick().
     */
    public abstract void tick();

    // ---------------------------------------- Initialising Methods ---------------------------------------- //

    /*
     *The methode render is called every run the run is defined in Game.run().
     */
    public abstract void render(Graphics g);

}
