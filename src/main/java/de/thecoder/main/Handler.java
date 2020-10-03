package de.thecoder.main;


import java.awt.*;
import java.util.*;

public class Handler {

private final Game game; /*Is a reference to the game that was initialised when lunching the program.*/

LinkedList<GameObject> object          = new LinkedList<GameObject>(); /*List with all game objects not from players.*/
LinkedList<GameObject> playerOneObject = new LinkedList<GameObject>(); /*List with all game objects from player one.*/
LinkedList<GameObject> playerTwoObject = new LinkedList<GameObject>(); /*List with all game objects from player two.*/

/**
 * Handler constructor is the constructor that controls all objects that are added.
 *
 * @param game Is the game that is assigned it should be the game that is initialised in the Game class.
 */
public Handler(Game game) {

	this.game = game;
}

/**
 * Adds an object to the list object.
 *
 * @param object Is a GameObject that is added to the list playerTwoObject.
 */
public void addObject(GameObject object) {

	this.object.add(object);
}

/**
 * Adds an object to the list playerOneObject.
 *
 * @param object Is a GameObject that is added to the list playerTwoObject.
 */
public void addObjectOne(GameObject object) {

	this.playerOneObject.add(object);
}

/**
 * Adds an object to the list playerTwoObject.
 *
 * @param object Is a GameObject that is added to the list playerTwoObject.
 */
public void addObjectTwo(GameObject object) {

	this.playerTwoObject.add(object);
}

/**
 * Removes an object from the list object.
 *
 * @param object Is a GameObject that is removed to the list playerTwoObject.
 */
public void removeObject(GameObject object) {

	this.object.remove(object);
}

/**
 * Removes an object from the list playerOneObject.
 *
 * @param object Is a GameObject that is removed to the list playerTwoObject.
 */
public void removeObjectOne(GameObject object) {

	this.playerOneObject.remove(object);
}

/**
 * Removes an object from the list playerTwoObject.
 *
 * @param object Is a GameObject that is removed to the list playerTwoObject.
 */
public void removeObjectTwo(GameObject object) {

	this.playerTwoObject.remove(object);
}

/**
 * The methode tick called every tick the tick is defined in Game.run().
 */
public void tick() {

	for(int i = 0; i < object.size(); i++) {
		GameObject tempObject = object.get(i); tempObject.tick();
	}
}

/**
 * The methode render is called every run the run is defined in Game.run().
 */
public void render(Graphics g) {

	if(game.gameState == STATE.GameEnd) {
		for(GameObject tempObject : playerOneObject) {
			tempObject.render(g);
		} for(GameObject tempObject : playerTwoObject) {
			tempObject.render(g);
		} for(GameObject tempObject : object) {
			tempObject.render(g);
		}
	}

	if(game.gameState == STATE.GamePlayer1)  /*If statement will check if game state is player ones.*/
		for(GameObject tempObject : playerOneObject) { /*For each loop loops through the list playerOneObject.*/
			tempObject.render(g);
		}

	if(game.gameState == STATE.GamePlayer2)
		for(GameObject tempObject : playerTwoObject) { /*For each loop loops through the list playerTwoObject.*/
			tempObject.render(g);
		}

	for(GameObject tempObject : object) { /*For each loop loops through the list object.*/
		tempObject.render(g);
	}
}

}
