package de.thecoder.main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject {

    Handler handler;
    HUD hud;
    int shipsDestroyed = 0;

    public Player(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {

    }

    //FIXME: One array value to much maybe for loop cause
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
            if (arrayX + shipSize <= Game.shipPosition.length && arrayY <= Game.shipPosition.length) {
                //Uses the ship size to create a for loop which contains instructions to write into an array.
                for (int i = 0; i <= shipSize; i++) {
                    //Checks if the position is not out of bounds this is dependent on the ship size which is provided by the for loop.
                    if (arrayX + i <= Game.shipPosition.length && arrayY <= Game.shipPosition.length) {
                        //Writes the shipType int to the array at given cords the i is the ship length so that it is variable.
                        Game.shipPosition[arrayX + i][arrayY][field] = shipType;
                        //Adds the ship not only in the Array adds it as well to the world, so that it is visible.
                        handler.addObject(Game.shipTypeToObject(shipType, arrayX, arrayY, field, rotation, handler));
                    } else {
                        //If the ship position that is given leads to the problem that the ship would be out of bounds.
                        System.out.println("ERROR: Ship is out of the field. Methode: setShip Rotation: 0");
                        //Close the app
                        System.exit(1);
                    }
                }
            } else {
                System.out.println("ERROR: Ship is out of the field. Methode: setShip Rotation: 90");
                System.exit(1);
            }
            //Same as above only with the difference that it is now for another rotation
        } else if (rotation == 90) {
            if (arrayX <= Game.shipPosition.length && arrayY + shipSize <= Game.shipPosition.length) {
                for (int i = 0; i <= shipSize; i++) {
                    if (arrayX <= Game.shipPosition.length && arrayY + i <= Game.shipPosition.length) {
                        Game.shipPosition[arrayX][arrayY + i][field] = shipType;
                        handler.addObject(Game.shipTypeToObject(shipType, arrayX, arrayY, field, rotation, handler));
                    } else {
                        System.out.println("ERROR: Ship is out of the field. Methode: setShip Rotation: 90");
                        System.exit(1);
                    }
                }
            } else {
                System.out.println("ERROR: Ship is out of the field. Methode: setShip Rotation: 90");
                System.exit(1);
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
        if (Game.shipPosition[arrayX][arrayY][field] == 11 || Game.shipPosition[arrayX][arrayY][field] == 22 || Game.shipPosition[arrayX][arrayY][field] == 33 || Game.shipPosition[arrayX][arrayY][field] == 44 || Game.shipPosition[arrayX][arrayY][field] == 55)
            return Game.shipPosition[arrayX][arrayY][field] / 11;
        else
            return Game.shipPosition[arrayX][arrayY][field];
    }

    //Return the ship condition
    public int getShipCondition(int arrayX, int arrayY, int field) {
        return Game.shipPosition[arrayX][arrayY][field];
    }

    //Checks if the ship is destroyed at a cord
    public boolean checkIfShipIsHit(int arrayX, int arrayY, int field, int shipType) {
        //If loop which is is bundled with the return statement
        return Game.shipPosition[arrayX][arrayY][field] == shipType * 11;
    }

    //Checks if the ship at give cords is destroyed
    //FIXME: Add that two ships can be placed direct against each other
    //FIXME: LOOP through array collides with ship in X axis and maybe as well in Y axis
    public boolean ship(int arrayX, int arrayY, int field) {

        //Is the type of the ship
        int shipType = getShipInArray(arrayX, arrayY, field);
        //Is the size of the ship
        int shipTypeSize = Game.shipTypeToSize(shipType);
        //Is a array list with integers which contains the condition of the ship
        List<Integer> shipStatus = new ArrayList<Integer>();
        //Is a counter
        int counter = 0;


        //Checks if the entered coordinates are in the array.
        if (arrayX < Game.shipPosition.length && arrayY < Game.shipPosition.length) {
            //Checks if at the given cords is a ship
            if (getShipInArray(arrayX, arrayY, field) != 0) {
                //Gets the part at that you shot and if there is a ship it will execute the command
                if (checkIfShip(arrayX, arrayY, field))
                    //Adds the ship condition to the ArrayList
                    shipStatus.add(getShipCondition(arrayX, arrayY, field));
            }
            //Is a for loop to go through the possible locations of the ship it depends on the shipTypeSize which depends on the shipType
            for (int i = 1, j = 1; i <= shipTypeSize; i++, j++) {
                //Checks if the ship is placed in the rotation of 90 degrees in Y axis
                if (checkIfShip(arrayX, arrayY + 1, field) || checkIfShip(arrayX, arrayY - 1, field)) {
                    //Checks if the calculated cords are in the array if so executes the next if statement
                    if (arrayX < Game.shipPosition.length && arrayY + i < Game.shipPosition.length) {
                        //Checks if there is a ship at the coordinates if so adds it to the list
                        if (checkIfShip(arrayX, arrayY + i, field))
                            //Adds the ship condition to the ArrayList
                            shipStatus.add(getShipCondition(arrayX, arrayY + i, field));
                    }
                    //The following parts are as above just with other values
                    if (arrayX < Game.shipPosition.length && arrayY - j < Game.shipPosition.length) {
                        if (checkIfShip(arrayX, arrayY - j, field))
                            shipStatus.add(getShipCondition(arrayX, arrayY - j, field));
                    }
                }
                //Checks if the ship is placed in the rotation of 0 degrees in X axis
                if (checkIfShip(arrayX + 1, arrayY, field) || checkIfShip(arrayX - 1, arrayY, field)) {
                    //Checks if the calculated cords are in the array if so executes the next if statement
                    if (arrayX + i < Game.shipPosition.length && arrayY < Game.shipPosition.length) {
                        //Checks if there is a ship at the coordinates if so adds it to the list
                        if (checkIfShip(arrayX + i, arrayY, field))
                            //Adds the ship condition to the ArrayList
                            shipStatus.add(getShipCondition(arrayX + i, arrayY, field));
                    }
                    //The following parts are as above just with other values
                    if (arrayX - j < Game.shipPosition.length && arrayY < Game.shipPosition.length) {
                        if (checkIfShip(arrayX - j, arrayY, field))
                            shipStatus.add(getShipCondition(arrayX - j, arrayY, field));
                    }
                }
            }
        }
        //Loops through the ArrayList to checks if all ships are destroyed which means there shipType is multiplied by 11
        for (int i = 0; i < shipStatus.size(); i++) {
            //Checks if the ship that is saved to the list is destroyed
            if (shipStatus.get(i) == getShipInArray(arrayX, arrayY, field) * 11)
                //If the ship is destroyed adds one to the counter
                counter++;
        }
        //Checks if the counter equals the shipTypeSize if this is true it means that a ship is destroyed
        if (counter == shipTypeSize) {
            if (field == 0) {
                //Removes a ship from the ship alive from player one
                HUD.shipsAliveOne--;
                //Sends a message to the player one that a ship is destroyed
                HUD.setMessageOne("Ship Destroyed");
                //Sends a message to the player two that a ship is destroyed
                HUD.setMessageTwo("Successfully Destroyed a enemy ship captain");

            } else if (field == 1) {
                //Removes a ship from the ship alive from player one
                HUD.shipsAliveTwo--;
                //Sends a message to the player one that a ship is destroyed
                HUD.setMessageOne("Ship Destroyed");
                //Sends a message to the player two that a ship is destroyed
                HUD.setMessageTwo("Successfully Destroyed a enemy ship captain");
            }
            //Returns true if ship is destroyed
            return true;
        } else
            return false;
    }



   /* public boolean checkIfShipDestroyed(int arrayX, int arrayY, int field) {

        boolean rotatet = false;
        boolean shipFound = true;
        int shipTypeSize;
        int shipType;

        if (getShipInArray(arrayX, arrayY, field) == 1 || getShipInArray(arrayX, arrayY, field) == 2 || getShipInArray(arrayX, arrayY, field) == 3 || getShipInArray(arrayX, arrayY, field) == 4 || getShipInArray(arrayX, arrayY, field) == 5) {
            shipType = getShipInArray(arrayX, arrayY, field);
        } else {
            shipType = getShipInArray(arrayX, arrayY, field) / 11;
        }
        shipTypeSize = Game.shipTypeToSize(shipType);

        for (int x = arrayX; x <= shipTypeSize; x++) {
            for (int y = arrayY; y <= shipTypeSize; y++) {
                if (checkIfShip(x, y, field)) {
                    if (checkIfShip(x, y - 1, field) && !checkIfShip(x, y + 1, field)) {
                        rotatet = true;
                        shipTypeSize = Game.shipTypeToSize(shipType);
                        if (getShipInArray(x, y, field) == 1 || getShipInArray(x, y, field) == 2 || getShipInArray(x, y, field) == 3 || getShipInArray(x, y, field) == 4 || getShipInArray(x, y, field) == 5) {
                            shipType = getShipInArray(x, y, field);
                        } else {
                            shipType = getShipInArray(x, y, field) / 11;
                        }
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
    }*/

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

        if (shipsDestroyed != 1) {
            handler.addObject(new Missile(pixelX, pixelY, ID.Missile, handler));
            Game.shipPosition[arrayX][arrayY][field] = shipType * 11;
            if (ship(arrayX, arrayY, 0))
                //Because this only gets executed when a ship is hit it will show the message Hit
                //TODO: Separate the Player Messages so that both are possible to win and get a hit.
                HUD.setMessageOne("HIT!");
        } else {
            System.out.println("WIN!");
        }
    }

    public void render(Graphics g) {
    }
}