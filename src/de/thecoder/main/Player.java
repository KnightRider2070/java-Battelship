package de.thecoder.main;

import java.awt.*;

public class Player extends GameObject {

    Handler handler;
    HUD hud;

    public Player(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;

    }


    public void tick() {
        x += velX;
        y += velY;
    }

    //Creates a ship and sets it in the Field as well as in the array.
    public void setShip(int x, int y, int field, ID id) {
        //Checks if the ID is Destroyer
        if (id == ID.Destroyer) {
            //Adds the Position to the Array in the Game class.
            Game.shipPosition[x][y][field] = 5;
            Game.shipPosition[x + 1][y][field] = 5;
            //Adds the object to the actual world so that it is visible and does exist.
            handler.addObject(new Destroyer(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Destroyer, handler));
        }
    }

    //Removes the Ship in the field as well  as in the array.
    public void removeShip(int x, int y, int field, ID id) {
        //Checks if the ID is Destroyer
        if (id == ID.Destroyer) {
            //Removes the Position from the Array in the Game class.
            Game.shipPosition[x][y][field] = 0;
            Game.shipPosition[x + 1][y][field] = 0;
            //Removes the object to the actual world so that it is deleted and does no longer exist.
            handler.removeObject(new Destroyer(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Destroyer, handler));
        }

    }

    //Checks if at the give coordinates which are array coordinates is a ship.
    public boolean checkIfShip(int x, int y, int field) {
        //Checks if the array is 0 this means no ship.
        //Returns false
        return Game.shipPosition[x][y][field] != 0;
        //Returns true
    }

    //Returns the Ship type at a position XY that is given in array coordinates.
    public ID getShipType(int x, int y, int field) {
        //Initialises the tempObject with null
        ID tempGameObject = null;
        //Checks the array at the position if there are the values 5 ||55 || 555 this means there is some type of a Destroyer.
        if (Game.shipPosition[x][y][field] == 5 || Game.shipPosition[x][y][field] == 55 || Game.shipPosition[x][y][field] == 555)
            //Sets the tempObject to Destroyer
            tempGameObject = ID.Destroyer;
        //Returns the tempObject
        return tempGameObject;
    }

    //Attacks the ship and creates a missile, the XY coordinates are pixels.
    public void Attack(int x, int y) {
        //Adds a missile to the game at the position of the click.
        //TODO: Center the creation at the fields
        handler.addObject(new Missile(x, y, ID.Missile, handler));
        //Because this only gets executed when a ship is hit it will show the message Hit
        //TODO: Separate the Player Messages so that both are possible to win and get a hit.
        HUD.setMessageOne("HIT!");

    }

    //Gets called from Mouse Input XY coordinates are pixels.
    public void theAttackOrder(int x, int y) {
        //Converts Pixel to array coordinates
        int[] convCoordinates = Game.pixelsToCordConv(x, y);
        //Gets the coordinates that are array coordinates and writes them from the array to an integer.
        int convX = convCoordinates[0];
        int convY = convCoordinates[1];
        int field = convCoordinates[2];
        //Ship ID
        ID id;
        //If at the position where you clicked is a ship it will call the next methode.
        if (checkIfShip(convX, convY, field) == true) {
            //Sets the ship ID into an ID variable
            //TODO: USE THE LIST OR DELETE IT
            id = getShipType(convX, convY, field);
            //Executes the Attack methode with the raw pixel coordinates.
            Attack(x, y);
        }


    }


    public void render(Graphics g) {

    }


}
