package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //


import java.awt.*;
import java.util.LinkedList;

public class Handler {

    // ---------------------------------------- Global Variables ---------------------------------------- //

    //List with all game objects
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    // ---------------------------------------- Initialising Methods ---------------------------------------- //


    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    // ---------------------------------------- Logic Methods ---------------------------------------- //


    //Adds an object
    public void addObject(GameObject object) {
        this.object.add(object);
    }

    //Removes an object
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
}
