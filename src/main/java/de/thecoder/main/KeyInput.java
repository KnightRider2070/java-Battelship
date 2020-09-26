package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    // ---------------------------------------- Global Variables ---------------------------------------- //

    //Game object handler reference. Final cuz it should never be changed so that it is always listening to the same handler.
    private final Handler handler;

    // ---------------------------------------- Initialising Methods ---------------------------------------- //

    /*
     * KeyInput is the constructor for the KeyInput class
     * The Methode assigns the used values.
     * @param handler        Is the handler that is assigned it should be the handler that is initialised in the Game class.
     */
    public KeyInput(Handler handler) {
        //Assigns the handler from the Game class to the handler in the KeyInput class.
        this.handler = handler;
    }

    // ---------------------------------------- Logic Methods ---------------------------------------- //

    /*
     * keyPressed is a methode that executes if a key is pressed belongs to the KeyAdapter class
     * The Methode contains all actions that should happen if a button at the keyboard is pressed.
     */
    public void keyPressed(KeyEvent e) {
        //Assigns key the value from the pressed key.
        int key = e.getKeyCode();
        //If statement will check if the ESC button is pressed if so it will exit the program.
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
    }
}


