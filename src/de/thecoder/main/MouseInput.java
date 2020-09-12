package de.thecoder.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    private final Handler handler;

    public MouseInput(Handler handler) {
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int button = e.getButton();
    }

    public void mouseReleased(MouseEvent e) {
        int button = e.getButton();
    }
}
