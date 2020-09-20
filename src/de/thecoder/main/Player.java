package de.thecoder.main;

import java.awt.*;

public class Player extends GameObject {

    Handler handler;
    HUD hud;

    public Player(int x, int y, ID id, Handler handler, HUD hud) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
    }

    //Creates a ship and adds it to the array.
    public void setShip(int arrayX, int arrayY, int rotation, int field, int shipType) {

        if (!Game.validateShipType(shipType))
            System.out.println("ERROR: Wrong shipType check setShip methode call.");

        int shipSize = Game.shipTypeToSize(shipType);
        ID shipID = Game.shipTypeToID(shipType);

        if (rotation == 0) {
            for (int i = 0; i <= shipSize; i++) {
                if (arrayX + i >= Game.shipPosition.length && arrayY >= Game.shipPosition.length) {
                    Game.shipPosition[arrayX + i][arrayY][field] = shipType;
                    handler.addObject(Game.shipTypeToObject(shipType, arrayX, arrayY, field, rotation, handler));
                } else {
                    System.out.println("ERROR: Ship is out of the field. Methode: setShip");
                    System.exit(1);
                }
            }
        } else if (rotation == 90) {
            for (int i = 0; i <= shipSize; i++) {
                if (arrayX <= Game.shipPosition.length && arrayY + i <= Game.shipPosition.length) {
                    Game.shipPosition[arrayX][arrayY + i][field] = shipType;
                    handler.addObject(Game.shipTypeToObject(shipType, arrayX, arrayY, field, rotation, handler));
                } else {
                    System.out.println("ERROR: Ship is out of the field. Methode: setShip");
                    System.exit(1);
                }
            }
        } else {
            System.out.println("ERROR: Entered wrong rotation Methode: setShip");
            System.exit(1);
        }
    }

    //Checks if at the give coordinates which are array coordinates is a ship.
    public boolean checkIfShip(int x, int y, int field) {
        //Checks if the array is 0 this means no ship.
        return Game.shipPosition[x][y][field] != 0;
    }

    public int getShipInArray(int arrayX, int arrayY, int field) {
        if (Game.shipPosition[arrayX][arrayY][field] != 0) {
            return Game.shipPosition[arrayX][arrayY][field];
        } else {
            System.out.println("ERROR: There is no ship Methode: getShipInArray");
            System.exit(1);
            return 0;
        }
    }

    public boolean checkIfShipIsHit(int arrayX, int arrayY, int field, int shipType) {
        return Game.shipPosition[arrayX][arrayY][field] == shipType * 11;
    }

    public boolean checkIfShipDestroyed() {
        return true;
    }

    //Gets called from Mouse Input XY coordinates are pixels.
    public void theAttackOrder(int rawPixelX, int rawPixelY) {
        //Converts Pixel to array coordinates
        int[] arrayCoordinates = Game.pixelsToCord(rawPixelX, rawPixelY);
        //Gets the coordinates that are array coordinates and writes them from the array to an integer.
        int arrayX = arrayCoordinates[0];
        int arrayY = arrayCoordinates[1];
        int field = arrayCoordinates[2];

        int[] pixelCoordinates = Game.cordsToPixels(arrayX, arrayY, field);
        int pixelX = pixelCoordinates[0];
        int pixelY = pixelCoordinates[1];

        int shipTypeInt = getShipInArray(arrayX, arrayY, field);

        //If at the position where you clicked is a ship it will call the next methode.
        if (checkIfShip(arrayX, arrayY, field) && !checkIfShipIsHit(arrayX, arrayY, field, shipTypeInt)) {
            //Executes the Attack methode with pixel coordinates.
            Attack(pixelX, pixelY, arrayX, arrayY, field, shipTypeInt);
        }
    }

    //Attacks the ship and creates a missile, the XY coordinates are pixels.
    public void Attack(int pixelX, int pixelY, int arrayX, int arrayY, int field, int shipType) {

        //Adds a missile to the game at the position of the click.
        //TODO: Center the creation at the fields
        handler.addObject(new Missile(pixelX, pixelY, ID.Missile, handler));
        Game.shipPosition[arrayX][arrayY][field] = shipType * 11;
        //Because this only gets executed when a ship is hit it will show the message Hit
        //TODO: Separate the Player Messages so that both are possible to win and get a hit.
        HUD.setMessageOne("HIT!");
    }

    public void render(Graphics g) {
    }
}