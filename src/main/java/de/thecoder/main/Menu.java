package de.thecoder.main;

import java.awt.*;
import java.awt.event.*;

public class Menu extends MouseAdapter {

/**
 * The Methode will calculate if a field is clicked.
 *
 * @param mouseX Is an integer in pixel that contains the mouse position X axis.
 * @param mouseY Is an integer in pixel that contains the mouse position Y axis.
 * @param pixelX Is an integer in pixel which should contain the X axis cord.
 * @param pixelY Is an integer in pixel which should contain the Y axis cord.
 * @param width  Is an integer in pixel which should be the width of the field to be checked.
 * @param height Is an integer in pixel which should be the height of the field to be checked.
 */
private boolean mouseOver(int mouseX, int mouseY, int pixelX, int pixelY, int width, int height) {

	if(mouseX > pixelX && mouseX < pixelX + width) {
		return mouseY > pixelY && mouseY < pixelY + height;
	} else
		return false;
}

public void mouseClicked(MouseEvent e) {

	int button = e.getButton(); int mouseX = e.getX(); int mouseY = e.getY();

	if(mouseOver(mouseX, mouseY, 535, 200, 500, 70) && button == 1)
		Game.gameState = STATE.GamePlayer1; if(mouseOver(mouseX, mouseY, 535, 400, 500, 70) && button == 1) {}
	//NOTHING
	if(mouseOver(mouseX, mouseY, 535, 600, 500, 70) && button == 1)
		Game.gameState = STATE.Help;
}

public void render(Graphics g) {

	/*Fonts that can be used*/
	Font font  = new Font("Arial", Font.PLAIN, 40); Font font2 = new Font("Arial", Font.PLAIN, 60);
	Font font3 = new Font("Arial", Font.PLAIN, 19);

	/*The Header*/
	g.setColor(Color.orange); g.drawRect(475, 50, 600, 50); g.setFont(font);
	g.drawString("Battelfield Menu chose wisely", 520, 90);

	/*The First Button*/
	g.setColor(Color.GREEN); g.drawRect(535, 200, 500, 70); g.setFont(font2); g.setColor(Color.white);
	g.drawString("Play Local TWO", 565, 255);

	/*The Second Button*/
	g.setColor(Color.ORANGE); g.drawRect(535, 400, 500, 70); g.setFont(font2); g.setColor(Color.white);
	g.drawString("Play Local AI", 605, 455);

	/*The Third Button*/
	g.setColor(Color.PINK); g.drawRect(535, 600, 500, 70); g.setFont(font2); g.setColor(Color.white);
	g.drawString("HELP", 705, 655);
}

}

