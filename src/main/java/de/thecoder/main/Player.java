package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject {

    // ---------------------------------------- Global Variables ---------------------------------------- //

    //Game object handler reference
    Handler handler;
    //HUD reference
    HUD hud;


    // --------------------------------- Initialising Methods --------------------------- //

    /*
     *The Player constructor which creates a player at a specific position.
     * The parameter handler gets assigned and the parameter hud.
     * @param pixelX         Is a integer in pixel which should contain the X axis cord.
     * @param pixelY         Is a integer in pixel which should contain the Y axis cord.
     * @param id             Is the id that is assigned to the Player contained int the ID.java file.
     * @param handler        Is the handler that is assigned it should be the handler that is initialised in the Game class.
     * @param hud            Is the hud that is assigned it should be the hud that is initialised int the Game class.
     */
    public Player(int pixelX, int pixelY, ID id, Handler handler, HUD hud) {
        //Is the super constructed defined in the GameObject class.
        super(pixelX, pixelY, id);
        //Assigns the handler from the Game class to the handler in the Player class.
        this.handler = handler;
        //Assigns the hud from the Game class to the handler in the Player class.
        this.hud = hud;
    }

    /*
     *The methode tick called every tick the tick is defined in Game.run().
     * If it should be used it needs to be added to Game.tick().
     */
    public void tick() {

    }

    /*
     * setShip belongs to the initialising methods.
     * The Methode will add the ship that you want to create to the rendered objects and adds it to the Game.shipPosition array.
     * The ship always will be created from the X,Y cords away so that these are the ship bow.
     * @param arrayX           Is an integer in array value which should contain the X axis cord.
     * @param arrayY           Is an integer in array value which should contain the Y axis cord.
     * @param field            Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
     * @param shipType         Is an integer which should be 1/2/3/4/5 for each ship Type. (See GameDoc)
     * @param rotation         Is an integer which contains the rotation of the ship possible is 0 and 90.
     */
    public void setShip(int arrayX, int arrayY, int field, int shipType, int rotation) {

        //If statement will validate shipType if it matches the possible values.
        if (!Game.validateShipType(shipType))
            //Prints an error message out if it does not equal a possible value.
            System.out.println("ERROR: Wrong shipType validation Methode: setShip");

        //shipSize is the size of the ship based on the shipType.
        int shipSize = Game.shipTypeToSize(shipType);

        //If statement will check the rotation to set the ship right.
        if (rotation == 0) {
            //If statement  will validate if the cords are in the array. The shipSize is added to the arrayX to check if the ship is in bounds of the array.
            if (arrayX + shipSize <= Game.shipPosition.length && arrayY <= Game.shipPosition.length) {
                /*
                 *For loop to loop through the positions for the ship. The loop has the break statement which depends on the shipSize.
                 * @param i          Will be used as ship length which increases every loop process.
                 */
                for (int i = 0; i < shipSize; i++) {
                    //If statement will check if the for loop integers are in bounds of the array. Not necessary it's for controlling.
                    if (arrayX + i <= Game.shipPosition.length && arrayY <= Game.shipPosition.length) {
                        //Writes the shipType int to the array at given cords the i is the ship length so that it is variable.
                        Game.shipPosition[arrayX + i][arrayY][field] = shipType;
                        //Adds the ship to the handler so that it will be rendered.
                        handler.addObject(Game.shipTypeToObject(arrayX, arrayY, field, shipType, rotation, handler));
                    } else {
                        //If the ship position is not suitable the ERROR will be printed.
                        System.out.println("ERROR: Ship is out of the field. For loop. Methode: setShip Rotation: 0");
                        //Exits
                        System.exit(1);
                    }
                }
            } else {
                //If the ship position is not suitable the ERROR will be printed.
                System.out.println("ERROR: Ship is out of the field. Methode: setShip Rotation: 0");
                //Exits
                System.exit(1);
            }
            //Equals code above only the values and the direction has changed.
        } else if (rotation == 90) {
            if (arrayX <= Game.shipPosition.length && arrayY + shipSize <= Game.shipPosition.length) {
                for (int i = 0; i <= shipSize; i++) {
                    if (arrayX <= Game.shipPosition.length && arrayY + i <= Game.shipPosition.length) {
                        Game.shipPosition[arrayX][arrayY + i][field] = shipType;
                        handler.addObject(Game.shipTypeToObject(arrayX, arrayY, field, shipType, rotation, handler));
                    } else {
                        System.out.println("ERROR: Ship is out of the field. For loop. Methode: setShip Rotation: 90");
                        System.exit(1);
                    }
                }
            } else {
                System.out.println("ERROR: Ship is out of the field. Methode: setShip Rotation: 90");
                System.exit(1);
            }
        } else {
            System.out.println("ERROR: Entered wrong rotation. Methode: setShip");
            System.exit(1);
        }
    }


    // ---------------------------------------- Logic Methods ---------------------------------------- //


    /*
     * theAttackOrder is a logic methode.
     * The methode contains the structure of an attack and the logic.
     * @param rawPixelX          Is an integer which are the cords at the X axis from the mouse courser.
     * @param rawPixelY          Is an integer which are the cords at the Y axis from the mouse courser.
     */
    public void theAttackOrder(int rawPixelX, int rawPixelY) {
        //arrayCoordinates is an array which contain the converted array cords that are calculated with the raw mouse cords.
        int[] arrayCoordinates = Game.pixelsToCord(rawPixelX, rawPixelY);
        //arrayX is an integer which gets the converted coordinates for the X axis assigned from the array arrayCoordinates.
        int arrayX = arrayCoordinates[0];
        //arrayY is an integer which gets the converted coordinates for the Y axis assigned from the array arrayCoordinates.
        int arrayY = arrayCoordinates[1];
        //field is an integer which gets the converted coordinates for the field assigned from the array arrayCoordinates.
        int field = arrayCoordinates[2];

        //pixelCoordinates is an array which contains the converted pixel cords that are calculated with the before calculated array cords.
        int[] pixelCoordinates = Game.cordsToPixels(arrayX, arrayY, field);
        //pixelX is an integer which gets the converted coordinates for the X axis assigned from the array pixelCoordinates.
        int pixelX = pixelCoordinates[0];
        //pixelY is an integer which gets the converted coordinates for the Y axis assigned from the array pixelCoordinates.
        int pixelY = pixelCoordinates[1];

        //shipType is an integer which gets the ship type as integer assigned.
        int shipType = getShipTypeInArray(arrayX, arrayY, field);

        //If statement will check if at the given cords is a ship  and if that hasn't been hit.
        if (checkIfShip(arrayX, arrayY, field) && !checkIfShipIsHit(arrayX, arrayY, field, shipType)) {
            //Executes the Attack methode.
            attackTheShip(pixelX, pixelY, arrayX, arrayY, field, shipType);
        }
    }

    /*
     * attackTheShip is a logic methode.
     * The methode executes the actual attack and fires the missile.
     * @param pixelX         Is an integer which are the converted cords at the X axis from the mouse courser.
     * @param pixelY         Is an integer which are the converted cords at the Y axis from the mouse courser.
     * @param arrayX         Is an integer in array value which should contain the X axis cord.
     * @param arrayY         Is an integer in array value which should contain the Y axis cord.
     * @param field            Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
     * @param shipType         Is an integer which should be 1/2/3/4/5 for each ship Type. (See GameDoc)
     */
    public void attackTheShip(int pixelX, int pixelY, int arrayX, int arrayY, int field, int shipType) {

        //TODO: Center the creation at the fields
        //Adds the missile at the hit position to the handler so that it will be rendered.
        handler.addObject(new Missile(pixelX, pixelY, ID.Missile, handler));
        //Sets the hit position in the array to a destroyed ship this means that the ship type has to be multiplied by 11. (See GameDoc)
        Game.shipPosition[arrayX][arrayY][field] = shipType * 11;
        //If statement will check if the ship at given cords id destroyed.
        if (checkIfShipIsDestroyed(arrayX, arrayY, field)) {
            //If statement checks if the field at that the action happens is 0 and that the ships that are not destroyed for player one are 0.
            // This means it belongs to player one and player two is attacking and has destroyed all ships.
            if (field == 0 && Game.shipsAliveOne == 0) {
                //Messages for the player.
                HUD.setMessageOne("Lost!");
                HUD.setMessageTwo("WIN!");
            }
            //Equals code above only the values and the player has changed.
            else if (field == 1 && Game.shipsAliveTwo == 0) {
                HUD.setMessageTwo("Lost!");
                HUD.setMessageOne("WIN!");
            }
        } else {
            //If statement will check the field to know which player is playing and who is being attacked.
            if (field == 0)
                //Player two gets the message HIT. Means he is attacking.
                HUD.setMessageTwo("HIT");
            else if (field == 1)
                //Player one gets the message HIT. Means he is attacking.
                HUD.setMessageOne("HIT");
        }
    }

    // ---------------------------------------- Query Methods ---------------------------------------- //

    /*
     * checkIfShip is a query methode.
     * The Methode checks if at the cords is a ship
     * @param arrayX           Is an integer in array value which should contain the X axis cord.
     * @param arrayY           Is an integer in array value which should contain the Y axis cord.
     * @param field            Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
     * @return                 Will return true if at cords is a ship if there is no ship return false.
     */
    public boolean checkIfShip(int arrayX, int arrayY, int field) {
        //If statement will validate if the given cords are in the array bounds.
        if (arrayX < Game.shipPosition.length && arrayY < Game.shipPosition.length)
            //Returns true or false simplified if statement.
            return Game.shipPosition[arrayX][arrayY][field] != 0;
        //Returns false if the ship position isn't valid.
        return false;
    }

    /*
     * checkIfShipIsHit is a query methode.
     * The Methode returns only the ship type condition not the ship type.
     * @param arrayX           Is an integer in array value which should contain the X axis cord.
     * @param arrayY           Is an integer in array value which should contain the Y axis cord.
     * @param field            Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
     * @return                 Will return true if at cords is a ship and it was hit if there is no ship return false.
     */
    public boolean checkIfShipIsHit(int arrayX, int arrayY, int field, int shipType) {
        //Returns true or false simplified if statement.
        return Game.shipPosition[arrayX][arrayY][field] == shipType * 11;
    }

    /*
     * getShipTypeInArray is a query methode.
     * The Methode returns a boolean if the ship is destroyed it uses a list and a few for loops to check both possible rotations.
     * The Methode only works correct if only one type of a ship is in the field that is been checked.
     * @param arrayX           Is an integer in array value which should contain the X axis cord.
     * @param arrayY           Is an integer in array value which should contain the Y axis cord.
     * @param field            Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
     * @return                 Will return true if at cords is a ship that is destroyed if there is a ship not destroyed returns false.
     */
    public boolean checkIfShipIsDestroyed(int arrayX, int arrayY, int field) {

        //shipType is the type of a ship depends on the cords.
        int shipType = getShipTypeInArray(arrayX, arrayY, field);
        //shipTypeSize is the size of the ship and depends on given cords.
        int shipTypeSize = Game.shipTypeToSize(shipType);
        //shipStatus is an array list to that you can access it with indices. It should contain the parts of a ship.
        List<Integer> shipStatus = new ArrayList<Integer>();
        //counter is an integer. It should increase if one part is destroyed see following lines.
        int counter = 0;


        //The Ship that has been shot at.
        //If statement checks if the given cords are valid and don't exceed the array bounds.
        if (arrayX < Game.shipPosition.length && arrayY < Game.shipPosition.length) {
            //If statement that checks if at the given cords is a ship.
            if (checkIfShip(arrayX, arrayY, field)) {
                //Will add the condition of the ship at the position to the array list.
                shipStatus.add(getShipCondition(arrayX, arrayY, field));
            }
            //The other parts of the ship except the part at that was shot.
            //For loop will loop until it is as long as ship. Is initialised with 1 to skip the hit position.
            for (int i = 1, j = 1; i <= shipTypeSize; i++, j++) {
                //If statement will validate if the ship is placed with the rotation 90 degrees.
                if (checkIfShip(arrayX, arrayY + 1, field) || checkIfShip(arrayX, arrayY - 1, field)) {
                    //If statement will check if the for loop will not cause an out of bounds exception and will check if at the cords that has been calculated by the for loop is a ship..
                    if (arrayX < Game.shipPosition.length && arrayY + i < Game.shipPosition.length && checkIfShip(arrayX, arrayY + i, field))
                        //Will add the condition of the ship at the position to the array list.
                        shipStatus.add(getShipCondition(arrayX, arrayY + i, field));

                    //Equals code above only the values and the direction has changed.
                    if (arrayX < Game.shipPosition.length && arrayY - j < Game.shipPosition.length && arrayY - j > 0 && checkIfShip(arrayX, arrayY - j, field))
                        shipStatus.add(getShipCondition(arrayX, arrayY - j, field));
                }
                //If statement will validate if the ship is placed with the rotation 0 degrees.
                if (checkIfShip(arrayX + 1, arrayY, field) || checkIfShip(arrayX - 1, arrayY, field)) {
                    //If statement will check if the for loop will not cause an out of bounds exception and will check if at the cords that has been calculated by the for loop is a ship..
                    if (arrayX + i < Game.shipPosition.length && arrayY < Game.shipPosition.length && checkIfShip(arrayX + i, arrayY, field))
                        //Adds the ship condition to the ArrayList
                        shipStatus.add(getShipCondition(arrayX + i, arrayY, field));
                    //Equals code above only the values and the direction has changed.
                    if (arrayX - j < Game.shipPosition.length && arrayY < Game.shipPosition.length && arrayX - j > 0 && checkIfShip(arrayX - j, arrayY, field))
                        shipStatus.add(getShipCondition(arrayX - j, arrayY, field));
                }
            }
        }
        //For each loop will loop until i is bigger than the size of the array list.
        for (Integer status : shipStatus) {
            //If statement uses i to access the array list and will check if all parts of a ship are destroyed. This means all parts are shipType multiplied by 11. (See GameDoc)
            if (status == getShipTypeInArray(arrayX, arrayY, field) * 11)
                //If the ship is destroyed increase the counter
                counter++;
        }
        //If statement will check if the integer has the same size as the ship. This means all parts are destroyed.
        if (counter == shipTypeSize) {
            //If statement will check which field was checked this will affect who's getting the points.
            if (field == 0) {
                //Decrease the integer. Is used to show how many ships one player has left. This is for player one.
                Game.shipsAliveOne--;
                //Sends a message to the player one that a ship is destroyed.
                HUD.setMessageOne("He attacked");
                //Sends a message to the player two that a ship is destroyed.
                HUD.setMessageTwo("You destroyed");

                //Equals code above only the values and the player has changed.
            } else if (field == 1) {
                Game.shipsAliveTwo--;
                HUD.setMessageOne("He attacked");
                HUD.setMessageTwo("You destroyed");
            }
            //Returns true if ship is destroyed.
            return true;
        } else
            //Returns false if the ship isn't destroyed
            return false;
    }

    // ---------------------------------------- Getter Methods ---------------------------------------- //

    /*
     * getShipTypeInArray is a getter methode.
     * The Methode returns only the ship type.
     * @param arrayX           Is an integer in array value which should contain the X axis cord.
     * @param arrayY           Is an integer in array value which should contain the Y axis cord.
     * @param field            Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
     * @return                 Will return an integer which contains the ship type.
     */
    public int getShipTypeInArray(int arrayX, int arrayY, int field) {
        //If statement will check if the ship is in a destroyed condition if so it will convert the value to the ship type. This means dividing with 11.
        if (Game.shipPosition[arrayX][arrayY][field] == 11 || Game.shipPosition[arrayX][arrayY][field] == 22 || Game.shipPosition[arrayX][arrayY][field] == 33 || Game.shipPosition[arrayX][arrayY][field] == 44 || Game.shipPosition[arrayX][arrayY][field] == 55)
            //Returns the converted integer with the ship type.
            return Game.shipPosition[arrayX][arrayY][field] / 11;
        else
            //Returns the integer with the ship type without conversion.
            return Game.shipPosition[arrayX][arrayY][field];
    }

    /*
     * getShipCondition is a getter methode.
     * The Methode returns only the ship type condition not the ship type.
     * @param arrayX           Is an integer in array value which should contain the X axis cord.
     * @param arrayY           Is an integer in array value which should contain the Y axis cord.
     * @param field            Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
     * @return                 Will return an integer which contains the ship type condition.
     */
    public int getShipCondition(int arrayX, int arrayY, int field) {
        //Returns the condition as example this could be 1 or 11. The other conditions see GameDoc.
        return Game.shipPosition[arrayX][arrayY][field];
    }


    // ---------------------------------------- Initialising Methods ---------------------------------------- //

    /*
     *The methode render is called every run the run is defined in Game.run().
     * If it should be used it needs to be added to Game.render().
     */
    public void render(Graphics g) {
    }
}