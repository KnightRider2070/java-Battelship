package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    // ---------------------------------------- Global Variables ---------------------------------------- //

    //List with all game objects.
    LinkedList<GameObject> object = new LinkedList<GameObject>();


    // ---------------------------------------- Logic Methods ---------------------------------------- //

    //Adds an object to the list.
    public void addObject(GameObject object) {
        this.object.add(object);
    }

    //Removes an object to the list.
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    // ---------------------------------------- Initialising Methods ---------------------------------------- //

    /*
     *The methode tick called every tick the tick is defined in Game.run().
     */
    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.tick();
        }
    }

    /*
     *The methode render is called every run the run is defined in Game.run().
     */
    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }
}
