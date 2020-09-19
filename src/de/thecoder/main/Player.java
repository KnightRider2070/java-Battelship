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
        x += velX;
        y += velY;
    }

    //Creates a ship and sets it in the Field as well as in the array.
    public void setShip(int x, int y, int field, ID id) {
         if (id == ID.Carrier) {
             //Adds the Position to the Array in the Game class.
             for (int i = 0; i <= 4; i++) {
                 Game.shipPosition[x + i][y][field] = 1;
             }
             //Adds the object to the actual world so that it is visible and does exist.
             handler.addObject(new Carrier(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Carrier, handler));
         } else if (id == ID.Battleship) {
             //Adds the Position to the Array in the Game class.
             for (int i = 0; i <= 3; i++) {
                 Game.shipPosition[x + i][y][field] = 2;
             }
             //Adds the object to the actual world so that it is visible and does exist.
             handler.addObject(new Battleship(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Battleship, handler));
         } else if (id == ID.Cruiser) {
             //Adds the Position to the Array in the Game class.
             for (int i = 0; i <= 2; i++) {
                 Game.shipPosition[x + i][y][field] = 3;
             }
             //Adds the object to the actual world so that it is visible and does exist.
             handler.addObject(new Cruiser(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Cruiser, handler));
         } else if (id == ID.Submarine) {
             //Adds the Position to the Array in the Game class.
             for (int i = 0; i <= 2; i++) {
                 Game.shipPosition[x + i][y][field] = 4;
             }
             //Adds the object to the actual world so that it is visible and does exist.
             handler.addObject(new Submarine(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Submarine, handler));
         } else if (id == ID.Destroyer) {
             //Adds the Position to the Array in the Game class.
             for (int i = 0; i <= 1; i++) {
                 Game.shipPosition[x + i][y][field] = 5;
             }
             //Adds the object to the actual world so that it is visible and does exist.
             handler.addObject(new Destroyer(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Destroyer, handler));
         }
    }

    //Removes the Ship in the field as well  as in the array.
    public void removeShip(int x, int y, int field, ID id) {
        if (id == ID.Carrier) {
            //Removes the Position from the Array in the Game class.
            for (int i = 0; i <= 4; i++) {
                Game.shipPosition[x + i][y][field] = 0;
            }
            //Removes the object to the actual world so that it is deleted and does no longer exist.
            handler.removeObject(new Carrier(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Carrier, handler));
        } else if (id == ID.Battleship) {
            //Removes the Position from the Array in the Game class.
            for (int i = 0; i <= 3; i++) {
                Game.shipPosition[x + i][y][field] = 0;
            }
            //Removes the object to the actual world so that it is deleted and does no longer exist.
            handler.removeObject(new Battleship(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Battleship, handler));
        } else if (id == ID.Cruiser) {
            //Removes the Position from the Array in the Game class.
            for (int i = 0; i <= 2; i++) {
                Game.shipPosition[x + i][y][field] = 0;
            }
            //Removes the object to the actual world so that it is deleted and does no longer exist.
            handler.removeObject(new Cruiser(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Cruiser, handler));
        } else if (id == ID.Submarine) {
            //Removes the Position from the Array in the Game class.
            for (int i = 0; i <= 2; i++) {
                Game.shipPosition[x + i][y][field] = 0;
            }
            //Removes the object to the actual world so that it is deleted and does no longer exist.
            handler.removeObject(new Submarine(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Submarine, handler));
        } else if (id == ID.Destroyer) {
            //Removes the Position from the Array in the Game class.
            for (int i = 0; i <= 1; i++) {
                Game.shipPosition[x + i][y][field] = 0;
            }
            //Removes the object to the actual world so that it is deleted and does no longer exist.
            handler.removeObject(new Destroyer(Game.cordsToPixelsConv(x, y, field)[0], Game.cordsToPixelsConv(x, y, field)[1], ID.Destroyer, handler));
        }

    }

    //Checks if at the give coordinates which are array coordinates is a ship.
    public boolean checkIfShip(int x, int y, int field) {
        //Checks if the array is 0 this means no ship.
        return Game.shipPosition[x][y][field] != 0;

    }

    //Returns the Ship type at a position XY that is given in array coordinates.
    public ID getShipType(int x, int y, int field) {
        //Initialises the tempObject with null
        ID tempGameObject = null;
        //Checks the array at the position if there are the values 1 || 11 || 111 this means there is some type of a Carrier.
        if (Game.shipPosition[x][y][field] == 1 || Game.shipPosition[x][y][field] == 11 || Game.shipPosition[x][y][field] == 111) {
            //Sets the tempObject to Carrier
            tempGameObject = ID.Carrier;
            //Returns the tempObject
            return tempGameObject;
        }
        //Checks the array at the position if there are the values 2 || 22 || 222 this means there is some type of a Battleship.
        else if (Game.shipPosition[x][y][field] == 2 || Game.shipPosition[x][y][field] == 22 || Game.shipPosition[x][y][field] == 222) {
            //Sets the tempObject to Battleship
            tempGameObject = ID.Battleship;
            //Returns the tempObject
            return tempGameObject;
        }
        //Checks the array at the position if there are the values 3 || 33 || 333 this means there is some type of a Cruiser.
        else if (Game.shipPosition[x][y][field] == 3 || Game.shipPosition[x][y][field] == 33 || Game.shipPosition[x][y][field] == 333) {
            //Sets the tempObject to Cruiser
            tempGameObject = ID.Cruiser;
            //Returns the tempObject
            return tempGameObject;
        }
        //Checks the array at the position if there are the values 4 || 4 || 444 this means there is some type of a Submarine.
        if (Game.shipPosition[x][y][field] == 4 || Game.shipPosition[x][y][field] == 44 || Game.shipPosition[x][y][field] == 444) {
            //Sets the tempObject to Submarine
            tempGameObject = ID.Submarine;
            //Returns the tempObject
            return tempGameObject;
        }
        //Checks the array at the position if there are the values 5 ||55 || 555 this means there is some type of a Destroyer.
        else if (Game.shipPosition[x][y][field] == 5 || Game.shipPosition[x][y][field] == 55 || Game.shipPosition[x][y][field] == 555) {
            //Sets the tempObject to Destroyer
            tempGameObject = ID.Destroyer;
            //Returns the tempObject
            return tempGameObject;
        }
        return tempGameObject;
    }

    //Gets the int of the ship
    public int getShipCondition(int arrayX, int arrayY, int field) {
        if (Game.shipPosition[arrayX][arrayY][field] != 0)
            return Game.shipPosition[arrayX][arrayY][field];
        return 0;
    }

    //Gets called from Mouse Input XY coordinates are pixels.
    public void theAttackOrder(int rawPixelX, int rawPixelY) {
        //Converts Pixel to array coordinates
        int[] arrayCoordinates = Game.pixelsToCordConv(rawPixelX, rawPixelY);
        //Gets the coordinates that are array coordinates and writes them from the array to an integer.
        int arrayX = arrayCoordinates[0];
        int arrayY = arrayCoordinates[1];
        int field = arrayCoordinates[2];

        int[] pixelCoordinates = Game.cordsToPixelsConv(arrayX, arrayY, field);
        int pixelX = pixelCoordinates[0];
        int pixelY = pixelCoordinates[1];

        //Ship ID
        ID id;
        //If at the position where you clicked is a ship it will call the next methode.
        if (checkIfShip(arrayX, arrayY, field) == true && getShipCondition(arrayX, arrayY, field) == 1 || getShipCondition(arrayX, arrayY, field) == 2 || getShipCondition(arrayX, arrayY, field) == 3 || getShipCondition(arrayX, arrayY, field) == 4 || getShipCondition(arrayX, arrayY, field) == 5) {
            //Sets the ship ID into an ID variable
            id = getShipType(arrayX, arrayY, field);
            //Executes the Attack methode with the raw pixel coordinates.
            Attack(pixelX, pixelY, arrayX, arrayY, field, id);
        }

    }

    //Attacks the ship and creates a missile, the XY coordinates are pixels.
    public void Attack(int pixelX, int pixelY, int arrayX, int arrayY, int field, ID id) {
        //Adds a missile to the game at the position of the click.
        //TODO: Center the creation at the fields
        handler.addObject(new Missile(pixelX, pixelY, ID.Missile, handler));
        if (id == ID.Carrier) {
            handler.addObject(new MissileFragment(pixelX, pixelY, ID.MissileFragment));
            Game.shipPosition[arrayX][arrayY][field] = 11;
        } else if (id == ID.Battleship) {
            handler.addObject(new MissileFragment(pixelX, pixelY, ID.MissileFragment));
            Game.shipPosition[arrayX][arrayY][field] = 22;
        } else if (id == ID.Cruiser) {
            handler.addObject(new MissileFragment(pixelX, pixelY, ID.MissileFragment));
            Game.shipPosition[arrayX][arrayY][field] = 33;
        } else if (id == ID.Submarine) {
            handler.addObject(new MissileFragment(pixelX, pixelY, ID.MissileFragment));
            Game.shipPosition[arrayX][arrayY][field] = 44;
        } else if (id == ID.Destroyer) {
            handler.addObject(new MissileFragment(pixelX, pixelY, ID.MissileFragment));
            Game.shipPosition[arrayX][arrayY][field] = 55;
        }

        if (checkIfShipIsDestroyed(0) == true) {
            System.exit(5);
        }
        //Because this only gets executed when a ship is hit it will show the message Hit
        //TODO: Separate the Player Messages so that both are possible to win and get a hit.
        HUD.setMessageOne("HIT!");

    }


    public boolean checkIfShipIsDestroyed(int field) {
        for (int y = 0; y <= 9; y++) {
            for (int x = 0; x <= 9; x++) {
                if (Game.shipPosition[x][y][field] == 11) {
                    for (int tx = 1; tx <= 5; tx++) {
                        if (Game.shipPosition[x + tx][y][field] != 11)
                            if (tx == 5) {
                                break;
                            } else {
                                System.out.println("Carrier DEAD");
                                return true;

                            }
                    }

                }
            }
        }
        return false;
    }

    public void render(Graphics g) {

    }


}
