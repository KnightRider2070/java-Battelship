package de.thecoder.main.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


public class Menu {


public void render(Graphics g) {

	/*Fonts that can be used*/
	Font font  = new Font("Arial", Font.PLAIN, 40);
	Font font2 = new Font("Arial", Font.PLAIN, 60);
	Font font3 = new Font("Arial", Font.PLAIN, 19);

	/*The Header*/
	g.setColor(Color.orange);
	g.drawRect(475, 50, 600, 50);
	g.setFont(font);
	g.drawString("Battelfield Menu chose wisely", 520, 90);

	/*The First Button*/
	g.setColor(Color.GREEN);
	g.drawRect(535, 200, 500, 70);
	g.setFont(font2);
	g.setColor(Color.white);
	g.drawString("Play Local TWO", 565, 255);

	/*The Second Button*/
	g.setColor(Color.ORANGE);
	g.drawRect(535, 400, 500, 70);
	g.setFont(font2);
	g.setColor(Color.white);
	g.drawString("Play Local AI", 605, 455);

	/*The Third Button*/
	g.setColor(Color.PINK);
	g.drawRect(535, 600, 500, 70);
	g.setFont(font2);
	g.setColor(Color.white);
	g.drawString("HELP", 705, 655);
}

}

