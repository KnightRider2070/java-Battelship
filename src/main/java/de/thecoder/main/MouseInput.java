package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    // ---------------------------------------- Global Variables ---------------------------------------- //

    private final Handler handler;
    Player player;

    // ---------------------------------------- Initialising Methods ---------------------------------------- //

    public MouseInput(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
    }

    // ---------------------------------------- Logic Methods ---------------------------------------- //

    public void mouseClicked(MouseEvent e) {
        int button = e.getButton();
        int x = e.getX();
        int y = e.getY();
        if (button == 1) {
            player.theAttackOrder(x, y);
        }
    }

    public void mouseReleased(MouseEvent e) {
        int button = e.getButton();
    }

}
