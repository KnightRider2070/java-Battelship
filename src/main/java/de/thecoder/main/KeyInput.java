package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    // ---------------------------------------- Global Variables ---------------------------------------- //

    private final Handler handler;

    // ---------------------------------------- Initialising Methods ---------------------------------------- //

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    // ---------------------------------------- Logic Methods ---------------------------------------- //

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
    }
}


