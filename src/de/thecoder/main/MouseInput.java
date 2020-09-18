package de.thecoder.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private final Handler handler;
    Player player;

    public MouseInput(Handler handler, Player player) {
        this.handler = handler;
        this.player = player;
    }

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
