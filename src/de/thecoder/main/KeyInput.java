package de.thecoder.main;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private final Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
       /* for (int i =0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.Destroyer){
                //Key Event for Player 1

            }
            if(tempObject.getId() == ID.Destroyer){
                //Key Event for Player 2
}
            }*/
    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
    }
}


