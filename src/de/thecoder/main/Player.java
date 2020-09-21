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

    }

    //Creates a ship and adds it to the array.
    public void setShip(int arrayX, int arrayY, int rotation, int field, int shipType) {

        //Validates shipType if it exists
        if (!Game.validateShipType(shipType))
            //Prints an error message out
            System.out.println("ERROR: Wrong shipType check setShip methode call. Methode: setShip");

        //Is the size of a ship based on the shipType that the user entered
        int shipSize = Game.shipTypeToSize(shipType);
        //Is the ID of a ship based on the shipType that the user entered
        ID shipID = Game.shipTypeToID(shipType);

        //Checks what rotation the ship has
        if (rotation == 0) {
            //Uses the ship size to create a for loop which contains instructions to write into an array.
            for (int i = 0; i <= shipSize; i++) {
                //Checks if the position is not out of bounds this is dependent on the ship size which is provided by the for loop.
                if (arrayX + i >= Game.shipPosition.length && arrayY >= Game.shipPosition.length) {
                    //Writes the shipType int to the array at given cords the i is the ship length so that it is variable.
                    Game.shipPosition[arrayX + i][arrayY][field] = shipType;
                    //Adds the ship not only in the Array adds it as well to the world, so that it is visible.
                    handler.addObject(Game.shipTypeToObject(shipType, arrayX, arrayY, field, rotation, handler));
                } else {
                    //If the ship position that is given leads to the problem that the ship would be out of bounds.
                    System.out.println("ERROR: Ship is out of the field. Methode: setShip");
                    //Close the app
                    System.exit(1);
                }
            }
            //Same as above only with the difference that it is now for another rotation
        } else if (rotation == 90) {
            for (int i = 0; i <= shipSize; i++) {
                if (arrayX <= Game.shipPosition.length && arrayY + i <= Game.shipPosition.length) {
                    Game.shipPosition[arrayX][arrayY + i][field] = shipType;
                    handler.addObject(Game.shipTypeToObject(shipType, arrayX, arrayY, field, rotation, handler));
                } else {
                    System.out.println("ERROR: Ship is out of the field. Methode: setShip");
                    System.exit(1);
                }
            }
        } else {
            System.out.println("ERROR: Entered wrong rotation Methode: setShip");
            System.exit(1);
        }
    }

    //Checks if at the give coordinates which are array coordinates is a ship.
    public boolean checkIfShip(int x, int y, int field) {
        //Checks if the array is 0 this means no ship.
        return Game.shipPosition[x][y][field] != 0;
    }

    //Return the ship at a position
    public int getShipInArray(int arrayX, int arrayY, int field) {
        //Checks if there is a ship.
        if (Game.shipPosition[arrayX][arrayY][field] != 0) {
            //Returns the Id which means an integer
            return Game.shipPosition[arrayX][arrayY][field];
        } else {
            //Error if the array contains a 00 at the position
            System.out.println("ERROR: There is no ship Methode: getShipInArray");
            //Closes the app
            System.exit(1);
            //Returns 0
            return 0;
        }
    }

    //Checks if the ship is destroyed at a cord
    public boolean checkIfShipIsHit(int arrayX, int arrayY, int field, int shipType) {
        //If loop which is is bundled with the return statement
        return Game.shipPosition[arrayX][arrayY][field] == shipType * 11;
    }

    public boolean checkIfShipDestroyed(int field) {

        boolean rotatet = false;
        boolean shipFound = true;
        int shipTypeSize;
        int shipType = 0;

        for (int x = 0; x <= 9; x++) {
            for (int y = 0; y <= 9; y++) {
                if (checkIfShip(x, y, field)) {
                    if (checkIfShip(x, y - 1, field) && !checkIfShip(x, y + 1, field)) {
                        rotatet = true;
                        if (getShipInArray(x, y, field) == 1 || getShipInArray(x, y, field) == 2 || getShipInArray(x, y, field) == 3 || getShipInArray(x, y, field) == 4 || getShipInArray(x, y, field) == 5) {
                            shipType = getShipInArray(x, y, field);
                        } else {
                            shipType = getShipInArray(x, y, field) / 11;
                        }
                        shipTypeSize = Game.shipTypeToSize(shipType);
                        for (int i = 0; i <= shipTypeSize; i++) {
                            if (getShipInArray(x, y - i, field) != shipType * 11) {
                                shipFound = false;
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    //Gets called from Mouse Input XY coordinates are pixels.
    public void theAttackOrder(int rawPixelX, int rawPixelY) {
        //Converts Pixel to array coordinates
        int[] arrayCoordinates = Game.pixelsToCord(rawPixelX, rawPixelY);
        //Gets the coordinates that are array coordinates and writes them from the array to an integer.
        int arrayX = arrayCoordinates[0];
        int arrayY = arrayCoordinates[1];
        int field = arrayCoordinates[2];

        int[] pixelCoordinates = Game.cordsToPixels(arrayX, arrayY, field);
        int pixelX = pixelCoordinates[0];
        int pixelY = pixelCoordinates[1];

        int shipTypeInt = getShipInArray(arrayX, arrayY, field);

        //If at the position where you clicked is a ship it will call the next methode.
        if (checkIfShip(arrayX, arrayY, field) && !checkIfShipIsHit(arrayX, arrayY, field, shipTypeInt)) {
            //Executes the Attack methode with pixel coordinates.
            Attack(pixelX, pixelY, arrayX, arrayY, field, shipTypeInt);
        }
    }

    //Attacks the ship and creates a missile, the XY coordinates are pixels.
    public void Attack(int pixelX, int pixelY, int arrayX, int arrayY, int field, int shipType) {

        //Adds a missile to the game at the position of the click.
        //TODO: Center the creation at the fields
        handler.addObject(new Missile(pixelX, pixelY, ID.Missile, handler));
        Game.shipPosition[arrayX][arrayY][field] = shipType * 11;
        //Because this only gets executed when a ship is hit it will show the message Hit
        //TODO: Separate the Player Messages so that both are possible to win and get a hit.
        HUD.setMessageOne("HIT!");
    }

    public void render(Graphics g) {
    }
}