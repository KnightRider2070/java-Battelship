package de.thecoder.main.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Help {

/**
 * The Methode is the constructor that initialises all necessary objects for the help page.
 */
public Help() {

}

/**
 * The methode tick called every tick the tick is defined in Game.run(). If it should be used it needs to be added to
 * Game.tick().
 */
public void tick() {

}


/**
 * The methode render is called every run the run is defined in Game.run().
 */
public void render(Graphics g) {
	/*Fonts that can be used*/
	Font font  = new Font("Arial", Font.PLAIN, 40); Font font2 = new Font("Arial", Font.PLAIN, 25);
	Font font3 = new Font("Arial", Font.PLAIN, 19);

	/*The Header*/
	g.setColor(Color.orange); g.drawRect(475, 50, 600, 50); g.setFont(font);
	g.drawString("Battelfield Help read wisely", 540, 90); g.setColor(Color.WHITE); g.setFont(font2);
	g.setColor(Color.PINK); g.drawString("Weitere Informationen in der Game Doc", 530, 450); g.setColor(Color.red);
	g.drawString("Hier!", 720, 480);
	/*The Headline Rules*/
	g.setColor(Color.green); g.drawRect(100, 200, 300, 200); g.fillRect(130, 210, 240, 30); g.setColor(Color.BLACK);
	g.setFont(font2); g.drawString("Ziel", 215, 235); g.setColor(Color.WHITE); g.setFont(font3);
	g.drawString("1. Alle Gegner Schiffe versenken.", 110, 290);
	g.drawString("2. Die Positionen sind unbekannt.", 110, 315);
	g.drawString("3. Positionen werden geschätzt.", 110, 340); g.drawString("4. Angriff durch klicken.", 110, 365);

	/*The setup*/
	g.setColor(Color.blue); g.drawRect(600, 200, 300, 200); g.fillRect(630, 210, 240, 30); g.setColor(Color.WHITE);
	g.setFont(font2); g.drawString("Setup", 715, 235); g.setFont(font3);
	g.drawString("1. Jeder plaziert 5 Schiffe.", 610, 290);
	g.drawString("2. Setzen horizontal und vertikal.", 610, 315); g.drawString("3. Keine Überlappungen.", 610, 340);
	g.drawString("4. Bug wird an Cords gesetzt.", 610, 365);

	/*The Gameplay*/
	g.setColor(Color.GREEN); g.drawRect(1100, 200, 300, 200); g.fillRect(1130, 210, 240, 30); g.setColor(Color.BLACK);
	g.setFont(font2); g.drawString("Gameplay", 1195, 235); g.setColor(Color.WHITE); g.setFont(font3);
	g.drawString("1. Angriff Player 1.", 1110, 290); g.drawString("2. Wenn Treffer erneuter Angriff.", 1110, 315);
	g.drawString("3. Sonst Player 2.", 1110, 340); g.drawString("4. Wenn alle Schiffe zerstört Ende.", 1110, 365);

	/*Keystrokes*/
	g.drawRect(400, 500, 700, 200); g.fillRect(630, 510, 240, 30); g.setColor(Color.BLACK); g.setFont(font2);
	g.drawString("Keystrokes", 685, 535); g.setColor(Color.WHITE); g.setFont(font3);
	g.drawString("1. ESC beendet das Spiel.", 410, 590); g.drawString("2. Linker Maus Button zum angriff.", 410, 613);
	g.drawString("3. NUM Pad zur eingabe der Koordinaten 1-0.", 410, 640);
	g.drawString("4. Wenn Num eingabe dann Pfeiltasten Rotation setzten.", 410, 665);

	/*Menu Button*/
	g.setColor(Color.RED); g.drawRect(1200, 600, 200, 50); g.setColor(Color.WHITE); g.setFont(font);
	g.drawString("MENU", 1240, 640);
}

}
