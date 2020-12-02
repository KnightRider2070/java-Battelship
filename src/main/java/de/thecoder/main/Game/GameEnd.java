package de.thecoder.main.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.imageio.ImageIO;

public class GameEnd {

//Image requirements
private InputStream   inputStream;
private BufferedImage image;

/**
 * The methode will get the image.
 */
private void getImage() {


	inputStream = getClass().getClassLoader().getResourceAsStream("images/medal.png");

	try {
		image = ImageIO.read(Objects.requireNonNull(inputStream));
	} catch(IOException exception) {
		exception.printStackTrace();
	}
	try {
		inputStream.close();
	} catch(IOException e) {
		e.printStackTrace();
	}
}

public void render(Graphics g) {
	/*Fonts that can be used*/
	Font font  = new Font("Arial", Font.PLAIN, 50);
	Font font2 = new Font("Ariel", Font.PLAIN, 40);
	Font font3 = new Font("Ariel", Font.PLAIN, 48);

	g.setColor(Color.GRAY); /*Background*/

	g.fillRect(0, 0, 1550, 750); /*Where to start painting black.*/

	/*The Header*/
	g.setColor(Color.orange);
	g.drawRect(500, 50, 515, 50);
	g.setFont(font);
	g.drawString("Battelfield Conclusion", 510, 90);

	//The ships from player one still alive.
	g.setColor(Color.RED);
	g.setFont(font2);
	g.drawString("Player one ships: " + Game.getShipsAlivePlayerOne(), 50, 350);

	//The ships from player two still alive.
	g.setColor(Color.green);
	g.setFont(font2);
	g.drawString("Player two ships: " + Game.getShipsAlivePlayerTwo(), 1100, 350);

	//The winners
	g.setColor(Color.MAGENTA);
	g.setFont(font3);
	g.drawString("Winner: " + Game.winner, 650, 250);

	//Trophy
	getImage();
	g.drawImage(image, 500, 200, null);


}

}
