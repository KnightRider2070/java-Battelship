package de.thecoder.main;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Player extends GameObject {

private final Handler handler; /*Is the handler that is assigned it should be the handler that is initialized in the
Game class.*/
private final Game    game; /*Is a reference to the game that was initialised when lunching the program.*/
private       boolean isHit = false; /*To check if the attacker hit*/


/**
 * The Player constructor which creates a player at a specific position.
 *
 * @param pixelX  Is a integer in pixel which should contain the X axis cord.
 * @param pixelY  Is a integer in pixel which should contain the Y axis cord.
 * @param id      Is the id that is assigned to the Player contained int the ID.java file.
 * @param handler Is the handler that is assigned it should be the handler that is initialised in the Game class.
 * @param game    Is the game reference from the game that was created before so that we can reference to created
 *                values.
 */
public Player(final int pixelX, final int pixelY, final ID id, final Handler handler, final Game game) {

	super(pixelX, pixelY, id);
	this.handler = handler;
	this.game    = game;
}

/**
 * The methode tick called every tick the tick is defined in Game.run(). If it should be used it needs to be added to
 * Game.tick().
 */
public void tick() {

}

/**
 * setShip belongs to the initialising methods. The Methode will add the ship that you want to create to the rendered
 * objects and adds it to the Game.shipPosition array. The ship always will be created from the X,Y cords away so that
 * these are the ship bow.
 *
 * @param arrayX   Is an integer in array value which should contain the X axis cord.
 * @param arrayY   Is an integer in array value which should contain the Y axis cord.
 * @param field    Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
 * @param shipType Is an integer which should be 1/2/3/4/5 for each ship Type. (See GameDoc)
 * @param rotation Is an integer which contains the rotation of the ship possible is 0 and 90.
 *
 * @return IS a boolean if the ship is set.
 */
public boolean setShip(int arrayX, int arrayY, int field, int shipType, int rotation) {

	int counter = 0; /*Counts ships per field.*/

	/*If statement will validate shipType if it matches the possible values.*/
	if(! Game.validateShipType(shipType))
		/*Prints an error message out if it does not equal a possible value.*/
		System.out.println("ERROR: Wrong shipType validation Methode: setShip");

	/*shipSize is the size of the ship based on the shipType.*/
	int shipSize = Game.shipTypeToSize(shipType);

	/*Checks if the bow position is free.*/
	if(arrayX < Game.shipPosition.length && arrayY < Game.shipPosition.length) {
		if(Game.shipPosition[arrayX][arrayY][field] != 0)
			return false;

		if(rotation == 0) { /*If statement will check the rotation to set the ship right.*/
			if(arrayX + shipSize < Game.shipPosition.length) /*If statement check if the ship not exceeding length.*/
				for(int i = 1; i <= shipSize; ++ i) { /*Loops through the ship.*/
					if(Game.shipPosition[arrayX + i][arrayY][field] != 0) /*Checks if field is !free.*/
						break;
					counter++;
				}
			if(counter == shipSize) { /*If all positions for ship are free places it.*/
				for(int i = 0; i < shipSize; ++ i) {
					Game.shipPosition[arrayX + i][arrayY][field] = shipType;
				}
				if(field == 0) {
					handler.addObjectOne(Game.shipTypeToObject(arrayX, arrayY, field, shipType, rotation, handler));
					return true;
				} else if(field == 1) {
					handler.addObjectTwo(Game.shipTypeToObject(arrayX, arrayY, field, shipType, rotation, handler));
					return true;
				}
			} else {
				System.out.println("ERROR: Ship is out of the field. For loop. Methode: setShip Rotation: 0");
				return false;
			}
			/*Equals code above only the rotation has changed.*/
		} else if(rotation == 90) {
			if(arrayY + shipSize < Game.shipPosition.length)
				for(int i = 0; i < shipSize; ++ i) {
					if(Game.shipPosition[arrayX][arrayY + i][field] != 0)
						break;
					counter++;
				}
			if(counter == shipSize) {
				for(int i = 0; i < shipSize; ++ i) {
					Game.shipPosition[arrayX][arrayY + i][field] = shipType;
				}
				if(field == 0) {
					handler.addObjectOne(Game.shipTypeToObject(arrayX, arrayY, field, shipType, rotation, handler));
					return true;
				} else if(field == 1) {
					handler.addObjectTwo(Game.shipTypeToObject(arrayX, arrayY, field, shipType, rotation, handler));
					return true;
				}
			} else {
				System.out.println("ERROR: Ship is out of the field. For loop. Methode: setShip Rotation: 90");
				return false;
			}
		}
	}
	return false;
}


/**
 * The methode contains the order how to act, if a player hit a ship if then the player changes or if the player hat the
 * chance to hit again.
 *
 * @param rawPixelX Is an integer which are the cords at the X axis from the mouse courser.
 * @param rawPixelY Is an integer which are the cords at the Y axis from the mouse courser.
 */
public void theGameAttackManager(int rawPixelX, int rawPixelY) {

	if(Game.gameState == STATE.GamePlayer1) {
		/*Executes the methode that contains the attack logic.*/
		theAttackOrder(rawPixelX, rawPixelY);
		if(isHit) {/*Checks if attack was hit or miss.*/
			HUD.setMessageOne("Attack again!");
		} else {
			Game.gameState = STATE.GamePlayer2;
		}
		/*Equals above but with a different player.*/
	} else if(Game.gameState == STATE.GamePlayer2) {
		theAttackOrder(rawPixelX, rawPixelY);
		if(isHit) {
			HUD.setMessageTwo("Attack again!");
		} else {
			Game.gameState = STATE.GamePlayer1;
		}
	}
}


/**
 * The methode contains the structure of an attack and the logic.
 *
 * @param rawPixelX Is an integer which are the cords at the X axis from the mouse courser.
 * @param rawPixelY Is an integer which are the cords at the Y axis from the mouse courser.
 */
public void theAttackOrder(int rawPixelX, int rawPixelY) {

	int[] arrayCoordinates = Game.pixelsToCord(rawPixelX,
	                                           rawPixelY);/*arrayCoordinates array which contain the array cords.*/
	int arrayX = arrayCoordinates[0]; /*arrayX is an integer which contains the X axis assigned from the array.*/
	int arrayY = arrayCoordinates[1]; /*arrayY is an integer which contains the Y axis assigned from the array.*/
	int field  = arrayCoordinates[2];    /*field is an integer which contains the field assigned from the array.*/

	int[] pixelCoordinates = Game.cordsToPixels(arrayX, arrayY,
	                                            field);/*pixelCoordinates is an array which contains pixel cords.*/
	int pixelX = pixelCoordinates[0]; /*pixelX is an integer which contains the X axis assigned from the array.*/
	int pixelY = pixelCoordinates[1]; /*pixelY is an integer which contains the Y axis assigned from the array.*/

	int shipType = getShipTypeInArray(arrayX, arrayY, field);/*shipType is an integer which contains the ship type.*/

	/*Checks if a player won the game.*/
	if(Game.getShipsAlivePlayerOne() == 0) {
		Game.gameState = STATE.GameEnd;
		HUD.setMessageAll("GAME ENDED!");
		HUD.setMessageOne("LOST :(!");
		HUD.setMessageTwo("WON :)!");
	} else if(Game.getShipsAlivePlayerTwo() == 0) {
		Game.gameState = STATE.GameEnd;
		HUD.setMessageAll("GAME ENDED!");
		HUD.setMessageOne("WON :)!");
		HUD.setMessageTwo("LOST :(!");
	} else
		attackTheShip(pixelX, pixelY, arrayX, arrayY, field, shipType);

}

/**
 * The methode executes the actual attack and fires the missile.
 *
 * @param pixelX   Is an integer which are the converted cords at the X axis from the mouse courser.
 * @param pixelY   Is an integer which are the converted cords at the Y axis from the mouse courser.
 * @param arrayX   Is an integer in array value which should contain the X axis cord.
 * @param arrayY   Is an integer in array value which should contain the Y axis cord.
 * @param field    Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
 * @param shipType Is an integer which should be 1/2/3/4/5 for each ship Type. (See GameDoc)
 */
public void attackTheShip(int pixelX, int pixelY, int arrayX, int arrayY, int field, int shipType) {

	if(arrayX < Game.shipPosition.length && arrayX >= 0 && arrayY < Game.shipPosition.length && arrayY >= 0)
		if(checkIfShip(arrayX, arrayY, field) && ! checkIfShipIsHit(arrayX, arrayY, field, shipType)) {
			//TODO: Center the creation at the fields
			handler.addObject(
					new Missile(pixelX, pixelY, ID.Missile, handler)); /*Adds the missile at the hit position.*/
			handler.addObject(new MissileFragment(pixelX, pixelY, "red", ID.MissileFragment));

			Game.shipPosition[arrayX][arrayY][field] = shipType * 11; /*Sets ship destroyed.*/

			if(checkIfShipIsDestroyed(arrayX, arrayY, field)) { /*If statement will check if ship is destroyed.*/
				if(field == 0) /*If statement will check which field was attacked and then decreases the shipsAlive.*/
					Game.setShipsAlivePlayerOne(Game.getShipsAlivePlayerOne() - 1);
					/*Equals code above only the values and the field has changed.*/
				else if(field == 1)
					Game.setShipsAlivePlayerTwo(Game.getShipsAlivePlayerTwo() - 1);
			} else {
				if(field == 0) { /*If statement check field to know which player is playing and who is being
				attacked.*/
					isHit = true;
					HUD.setMessageTwo("HIT");
				} else if(field == 1) {
					isHit = true;
					HUD.setMessageOne("HIT");
				}
			}
		} else {
			/*The attack was not a hit so it will add objects to show this.*/
			isHit = false;
			if(field == 0)
				handler.addObjectTwo(new MissileFragment(pixelX, pixelY, "green", ID.MissileFragment));
			else if(field == 1)
				handler.addObjectOne(new MissileFragment(pixelX, pixelY, "green", ID.MissileFragment));
		}
}

/**
 * The Methode checks if at the cords is a ship.
 *
 * @param arrayX Is an integer in array value which should contain the X axis cord.
 * @param arrayY Is an integer in array value which should contain the Y axis cord.
 * @param field  Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
 *
 * @return Will return true if at cords is a ship if there is no ship return false.
 */
public boolean checkIfShip(int arrayX, int arrayY, int field) {

	return Game.shipPosition[arrayX][arrayY][field] != 0;
}

/**
 * The Methode returns only the ship type condition not the ship type.
 *
 * @param arrayX Is an integer in array value which should contain the X axis cord.
 * @param arrayY Is an integer in array value which should contain the Y axis cord.
 * @param field  Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
 *
 * @return Will return true if at cords is a ship and it was hit if there is no ship return false.
 */
public boolean checkIfShipIsHit(int arrayX, int arrayY, int field, int shipType) {

	return Game.shipPosition[arrayX][arrayY][field] == shipType * 11;
}

/**
 * The Methode returns a boolean if the ship is destroyed it uses a list and a few for loops to check both possible
 * rotations. The Methode only works correct if only one type of a ship is in the field that is been checked.
 *
 * @param arrayX Is an integer in array value which should contain the X axis cord.
 * @param arrayY Is an integer in array value which should contain the Y axis cord.
 * @param field  Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
 *
 * @return Will return true if at cords is a ship that is destroyed if there is a ship not destroyed returns false.
 */
public boolean checkIfShipIsDestroyed(int arrayX, int arrayY, int field) {

	int shipType     = getShipTypeInArray(arrayX, arrayY, field);
	int shipTypeSize = Game.shipTypeToSize(shipType);
	/*shipStatus is an array list to that you can access it with indices. It should contain the parts of a ship.*/
	List<Integer> shipStatus = new ArrayList<>();
	/*counter is an integer. It should increase if one part is destroyed see following lines.*/
	int counter = 0;


	/*If statement checks if the given cords are valid and don't exceed the array bounds.*/
	if(arrayX < Game.shipPosition.length && arrayY < Game.shipPosition.length && arrayX >= 0 && arrayY >= 0) {
		/*If statement that checks if at the given cords is a ship.*/
		if(checkIfShip(arrayX, arrayY, field)) {
			/*Will add the condition of the ship at the position to the array list.*/
			shipStatus.add(getShipCondition(arrayX, arrayY, field));
		}
		/*For loop will loop until it is as long as ship. Is initialised with 1 to skip the hit position.*/
		for(int i = 1; i <= shipTypeSize; i++) {

			if(arrayX + i < Game.shipPosition.length) {
				if(checkIfShip(arrayX + i, arrayY, field) && getShipTypeInArray(arrayX + i, arrayY,
				                                                                field) == shipType) {
					shipStatus.add(getShipCondition(arrayX + i, arrayY, field));
				}
			}
			if(arrayX - i >= 0) {
				if(checkIfShip(arrayX - i, arrayY, field) && getShipTypeInArray(arrayX - i, arrayY,
				                                                                field) == shipType) {
					shipStatus.add(getShipCondition(arrayX - i, arrayY, field));
				}
			}
			if(arrayY + i < Game.shipPosition.length) {
				if(checkIfShip(arrayX, arrayY + i, field) && getShipTypeInArray(arrayX, arrayY + i,
				                                                                field) == shipType) {
					shipStatus.add(getShipCondition(arrayX, arrayY + i, field));
				}
			}
			if(arrayY - i >= 0) {
				if(checkIfShip(arrayX, arrayY - i, field) && getShipTypeInArray(arrayX, arrayY - i,
				                                                                field) == shipType) {
					shipStatus.add(getShipCondition(arrayX, arrayY - i, field));
				}
			}

		}
	}
	/*For each loop will loop through the array with position int position.*/
	for(Integer position : shipStatus) {
		/*If statement uses i to access the array list and will check if all parts of a ship are destroyed.*/
		if(position == shipType * 11)
			counter++;
	}
	return counter == shipTypeSize;
}

/**
 * The Methode returns only the ship type.
 *
 * @param arrayX Is an integer in array value which should contain the X axis cord.
 * @param arrayY Is an integer in array value which should contain the Y axis cord.
 * @param field  Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
 *
 * @return Will return an integer which contains the ship type.
 */
public int getShipTypeInArray(int arrayX, int arrayY, int field) {
	/*If statement will check if the ship is in a destroyed condition if so it will convert the value to the ship*/
	if(Game.shipPosition[arrayX][arrayY][field] == 11 || Game.shipPosition[arrayX][arrayY][field] == 22 || Game.shipPosition[arrayX][arrayY][field] == 33 || Game.shipPosition[arrayX][arrayY][field] == 44 || Game.shipPosition[arrayX][arrayY][field] == 55)
		return Game.shipPosition[arrayX][arrayY][field] / 11;
	else
		return Game.shipPosition[arrayX][arrayY][field];
}

/**
 * The Methode returns only the ship type condition not the ship type.
 *
 * @param arrayX Is an integer in array value which should contain the X axis cord.
 * @param arrayY Is an integer in array value which should contain the Y axis cord.
 * @param field  Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
 *
 * @return Will return an integer which contains the ship type condition.
 */
public int getShipCondition(int arrayX, int arrayY, int field) {
	/*Returns the condition as example this could be 1 or 11. The other conditions see GameDoc.*/
	return Game.shipPosition[arrayX][arrayY][field];
}

/**
 * The methode render is called every run the run is defined in Game.run(). If it should be used it needs to be added to
 * Game.render().
 */
public void render(Graphics g) {

}

}