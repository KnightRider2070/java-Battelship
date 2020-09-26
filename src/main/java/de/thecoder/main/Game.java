package de.thecoder.main;

// ---------------------------------------- Imported Libraries ---------------------------------------- //

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {


    // ---------------------------------------- Global Variables ---------------------------------------- //


    //This are the HEIGHT and WIDTH of the window that should be created. It's final because the size won't change.
    public static final int WIDTH = 1570, HEIGHT = 750;
    //shipsAliveOne is the amount of ships that the player one has to begin the game. It's initialised with 5. It's static so that it could be accessed by all other classes.
    public static int shipsAliveOne = 5;
    //shipsAliveTwo is the amount of ships that the player two has to begin the game. It's initialised with 5. It's static so that it could be accessed by all other classes.
    public static int shipsAliveTwo = 5;
    //Initialises a integer array shipPosition with the size X = 10,Y = 10, Z = 2. It's static so that it could be accessed by all other classes.
    static int[][][] shipPosition = new int[10][10][2];
    //References the handler to a Handler. It's final because this handler should always be the same.
    private final Handler handler;
    //References the hud to a HUD. It's final because this hud should always be the same.
    private final HUD hud;
    //References the player to a Player. It's final because this player should always be the same.
    private final Player player;
    //This is the size of one cell that you can see in the game. It's final because this should always stay the same.
    private final int cellSize = 50;
    //References the thread to a Thread. Those are the Threads at that the game run.
    private Thread thread;
    //Assigns the boolean running the value false, cuz it's not running.
    private boolean running = false;


    // ---------------------------------------- Initialising Methods ---------------------------------------- //


    //The Game logic and object initialisation
    public Game() {

        //Assigns the handler a new Handler.
        handler = new Handler();
        //Assigns the hud a new Hud.
        hud = new HUD();
        //It adds the first player to the battlefield.
        player = new Player(1, 1, ID.Player, handler, hud);
        //Creating listener for Key input
        this.addKeyListener(new KeyInput(handler));
        //Creating listener for Mouse input
        this.addMouseListener(new MouseInput(handler, player));

        //Creating the Window with the game in it.
        new Window(WIDTH, HEIGHT, "Battelship", this);

        //test
        player.setShip(1, 5, 0, 2, 0);
        player.setShip(6, 3, 0, 1, 90);
    }


    // ---------------------------------------- Logic Methods ---------------------------------------- //

    //Constructor to create a game you need a main methode in Java.
    public static void main(String[] args) {
        new Game();
    }


    // ---------------------------------------- Query Methods ---------------------------------------- //

    /* pixelsToCord is a query methode.
     * The Methode will convert pixel coordinates to array cords.
     * @param pixelX         Is an integer which are the converted cords at the X axis from the mouse courser.
     * @param pixelY         Is an integer which are the converted cords at the Y axis from the mouse courser.
     * @param return         Is an integer array which is sent back with the array XY and field cords.
     */
    public static int[] pixelsToCord(int pixelX, int pixelY) {
        //tempXYF is an array which contains the return values.
        int[] tempXYF = new int[3];
        //field is an integer which should contain the field for return.
        int field = 0;
        //convX is an integer which should contain the array X axis cord for return.
        int convX = 0;
        //convY is an integer which should contain the array Y axis cord for return.
        int convY = 0;

        //If statement will checks if the pixelX is smaller that 500 this means it is field 0.
        if (pixelX < 500) {
            //For loop will loop through the X pixels. i is the beginning of a field z is the end of a field added 50 so that one field is looped through.
            //This is the reason why j only get added 1 it is the array cord.
            for (int i = 0, z = 50, j = 0; z <= 500; i += 50, z += 50, j++) {
                //If statement will check if the cords are in the possible field.
                if (pixelX >= i && pixelX <= z) {
                    //Assigns convX the j value which equals the array cord.
                    convX = j;
                    //Breaks the fort loop cuz the coordinate is found.
                    break;
                }
            }

            //Equals code above only the field beginning has changed.
            for (int i = 100, z = 150, j = 0; z <= 600; i += 50, z += 50, j++) {
                if (pixelY >= i && pixelY <= z) {
                    convY = j;
                    break;
                }
            }

            //Assigns the tempXYF array the calculated coordinates. No field assignment cuz its already 0 since initialisation.
            tempXYF[0] = clamp(convX, 0, 10);
            tempXYF[1] = clamp(convY, 0, 10);
            //Returns the tempXYF array.
            return tempXYF;

            //Equals code above only the field has changed.
        } else if (pixelX > 1000) {
            //Assigns the field integer to field 1.
            field = 1;

            for (int i = 1000, z = 1050, j = 0; z <= 1500; i += 50, z += 50, j++) {
                if (pixelX >= i && pixelX <= z) {
                    convX = j;
                    break;
                }
            }

            for (int i = 100, z = 150, j = 0; z <= 600; i += 50, z += 50, j++) {
                if (pixelY >= i && pixelY <= z) {
                    convY = j;
                    break;
                }
            }

            //Assigns the tempXYF array the calculated coordinates.
            tempXYF[0] = clamp(convX, 0, 10);
            tempXYF[1] = clamp(convY, 0, 10);
            tempXYF[2] = field;
            //Returns the tempXYF array.
            return tempXYF;

            //When you the pixel cords are out of boundaries it will causes an error message and a exit.
        } else {
            System.out.println("Error when converting pixels to cords! Not in the field.");
            //Program closes
            System.exit(1);
        }
        return null;
    }


    /* cordsToPixels is a query methode.
     * The Methode will convert array coordinates to pixel.
     * @param arrayX           Is an integer in array value which should contain the X axis cord.
     * @param arrayY           Is an integer in array value which should contain the Y axis cord.
     * @param field            Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
     * @param return           Is an integer array which is sent back with the pixel XY cords.
     */
    public static int[] cordsToPixels(int arrayX, int arrayY, int field) {
        //tempXY is an array which contains the return values.
        int[] tempXY = new int[2];
        //If statement will check which field cords should be converted.
        if (field == 0) {
            //tempXY gets values assigned. Those are calculated multiplied by 50 because one field is 50 pixel big. Clamp methode used that the value can't exceed max and min.
            tempXY[0] = clamp(arrayX * 50, 0, 500);
            //tempXY gets values assigned. Those are calculated multiplied by 50 because one field is 50 pixel big added 100 cuz the field starts after 100 pixel.
            //Clamp methode used that the value can't exceed max and min.
            tempXY[1] = clamp(arrayY * 50 + 100, 100, 600);
            //Returns the tempXY array.
            return tempXY;
            //Equals code above only the field has changed.
        } else if (field == 1) {
            tempXY[0] = clamp(arrayX * 50 + 1000, 1000, 1500);
            tempXY[1] = clamp(arrayY * 50 + 100, 100, 600);
            return tempXY;
            //When you enter the wrong field value the methode will cause an error message and exits the program.
        } else
            System.out.println("ERROR: Entered wrong field. Methode: cordsToPixels");
        //Program closes
        System.exit(1);
        return null;
    }


    /* validateShipType is a query methode.
     * The Methode will check if the shipType is suitable.
     * @param shipType         Is an integer which should be 1/2/3/4/5 for each ship Type. (See GameDoc)
     * @param return           Is a boolean true if shipType is 1 up to 5, false if not.
     */
    public static boolean validateShipType(int shipType) {
        //Returns true or false simplified if statement.
        return shipType >= 1 && shipType <= 5;
    }


    /* shipTypeToObject is a query methode.
     * The Methode will convert array coordinates and ship type to a GameObject.
     * @param arrayX           Is an integer in array value which should contain the X axis cord.
     * @param arrayY           Is an integer in array value which should contain the Y axis cord.
     * @param field            Is an integer which should be 0(Player One) or 1(Player Two) is the layer with ship cords.
     * @param shipType         Is an integer which should be 1/2/3/4/5 for each ship Type. (See GameDoc)
     * @param rotation         Is an integer which contains the rotation of the ship possible is 0 and 90.
     * @param handler          Is the handler that is assigned it should be the handler that is initialised in the Game class.
     * @param return           Is a Game Object that is returned.
     */
    public static GameObject shipTypeToObject(int arrayX, int arrayY, int field, int shipType, int rotation, Handler handler) {

        //cords is an array which gets the pixel cords assigned that are calculated by the array cords.
        int[] cords = Game.cordsToPixels(arrayX, arrayY, field);
        //pixelX gets the value from the array assigned which contains the pixel X axis value.
        int pixelX = cords[0];
        //pixelY gets the value from the array assigned which contains the pixel Y axis value.
        int pixelY = cords[1];

        //If statement will checks which ship type is given.
        if (shipType == 1)
            //Returns a new created Carrier without adding it to the render.
            return new Carrier(pixelX, pixelY, rotation, ID.Carrier, handler);
        if (shipType == 2)
            return new Battleship(pixelX, pixelY, rotation, ID.Battleship, handler);
        if (shipType == 3)
            return new Cruiser(pixelX, pixelY, rotation, ID.Cruiser, handler);
        if (shipType == 4)
            return new Submarine(pixelX, pixelY, rotation, ID.Submarine, handler);
        if (shipType == 5)
            return new Destroyer(pixelX, pixelY, rotation, ID.Destroyer, handler);

        //This will get executed if non of this if statements will be true. An error message will be printed out, exits and null returns.
        System.out.println("ERROR: Entered the wrong shipType Methode: shipTypeToObject");
        //Program close
        System.exit(1);
        return null;
    }


    /* shipTypeToID is a query methode.
     * The Methode will return the ID of a ship.
     * @param shipType         Is an integer which should be 1/2/3/4/5 for each ship Type. (See GameDoc)
     * @param return           Is ID that is returned IDs can be found in ID class.
     */
    public static ID shipTypeToID(int shipType) {
        //If statement will check if the shipType is a ship.
        if (shipType == 1)
            //Returns the ID.Carrier cuz it belongs to shipType 1. (See GameDoc)
            return ID.Carrier;
        if (shipType == 2)
            return ID.Battleship;
        if (shipType == 3)
            return ID.Cruiser;
        if (shipType == 4)
            return ID.Submarine;
        if (shipType == 5)
            return ID.Destroyer;

        //This will get executed if non of this if statements will be true. An error message will be printed out, exits and null returns.
        System.out.println("ERROR: Entered the wrong shipType Methode: shipTypeToID");
        //Program close
        System.exit(1);
        return null;
    }


    /* shipTypeToSize is a query methode.
     * The Methode will return the size of a ship based on the type.
     * @param shipType         Is an integer which should be 1/2/3/4/5 for each ship Type. (See GameDoc)
     * @param return           Is an integer which contains the ship size.
     */
    public static int shipTypeToSize(int shipType) {

        //If statement will check if the ship is a ship or destroyed ship.
        if (shipType == 1 || shipType == 11)
            //Returns 5 this is the size of a ship from the type 1. (See GameDoc)
            return 5;
        if (shipType == 2 || shipType == 22)
            return 4;
        if (shipType == 3 || shipType == 33)
            return 3;
        if (shipType == 4 || shipType == 44)
            return 3;
        if (shipType == 5 || shipType == 55)
            return 2;

        //This will get executed if non of this if statements will be true. An error message will be printed out, exits and 0 returns.
        System.out.println("ERROR: Entered the wrong shipType Methode: shipTypeToSize");
        //Program close
        System.exit(1);
        return 0;
    }


    /* clamp is a query methode.
     * The Methode will check if the entered value exceed the min max entered value and if so sets it..
     * @param var         Is an integer that should be checked.
     * @param min         Is an integer that contains the minimal value of var.
     * @param max         Is an integer that contains the maximal value of var.
     * @param return      Is an integer that contains the raw var or the clamped var.
     */
    public static int clamp(int var, int min, int max) {
        //If statement will check if the var is bigger or equals the max value.
        if (var >= max)
            //Returns the max value cuz var exceeded it or was equal.
            return var = max;
            //If statement will check if the var is smaller or equals the mun value.
        else if (var <= min)
            //Returns the min value cuz var exceeded it or was equal.
            return var = min;
            //If the value is not equal min or max and do not exceed those you will get it back.
        else
            //Returns the var untouched.
            return var;
    }


    // ---------------------------------------- Setter Methods ---------------------------------------- //

    /*
     * setShipsAliveTwo is a setter method.
     * The Methode will set the ships alive value for player two.
     * @param shipsAliveTwo          Is an integer for player two how many ships still exist.
     */
    public void setSipsAliveTwo(int shipsAliveTwo) {
        Game.shipsAliveTwo = clamp(shipsAliveTwo, 0, 5);
    }

    /*
     * getShipsAliveOne is a getter method.
     * The Methode will get the ships alive value for player one.
     * @param return          Is an integer for player one how many ships still exist.
     */
    public int getShipsAliveOne() {
        return Game.shipsAliveOne;
    }


    // ---------------------------------------- Getter Methods ---------------------------------------- //

    /*
     * setShipsAliveOne is a setter method.
     * The Methode will set the ships alive value for player one.
     * @param shipsAliveOne          Is an integer for player one how many ships still exist.
     */
    public void setShipsAliveOne(int shipsAliveOne) {
        Game.shipsAliveOne = clamp(shipsAliveOne, 0, 5);
    }

    /*
     * setShipsAliveTwo is a getter method.
     * The Methode will get the ships alive value for player two.
     * @param return          Is an integer for player two how many ships still exist.
     */
    public int getShipsAliveTwo() {
        return Game.shipsAliveTwo;
    }


    // ---------------------------------------- Runner Methods ---------------------------------------- //


    /*
     * The methode start is synchronized cuz only then the changes are definitely visible.
     * All other synchronized methods will be executed after this not parallel.
     * Only one thread is created this means it's a single threaded application. One command at a time.
     */
    public synchronized void start() {
        //Creates a new Thread and targets this Game class.
        thread = new Thread(this);
        //Starts the new Thread.
        thread.start();
        //Sets boolean running true.
        running = true;
    }

    /*
     * The methode stop is synchronized cuz only then the changes are definitely visible.
     * Stops the thread the program else wont exit.
     */
    public synchronized void stop() {
        //Tries to stop the thread excepting an error.
        try {
            //Kills the Thread.
            thread.join();
            //Sets boolean running false.
            running = false;
            //Catches Exceptions before it will effect the process.
        } catch (Exception e) {
            //Prints the error out.
            e.printStackTrace();
        }
    }

    /*
     * Game Loop which creates the refresh rate and fps counter, some complex shit.
     * Not going to explain it in detail.
     */
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }


    /*
     * One tick controlled and defined by the run methode.
     * Add here the classes which tick methods you want to use.
     */
    private void tick() {
        handler.tick();
        hud.tick();
    }


    // ---------------------------------------- Initialising Methods ---------------------------------------- //


    /*
     * Renders all Objects out as Graphics so that they are visible.
     */
    private void render() {
        //Is a buffer that will store the drawn graphics as long as the JFrame runs.
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //Background
        g.setColor(Color.BLACK);
        //Where to start painting black
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //First Field
        //Width 500px starts at 0px ends at 500px
        for (int lx = 0; lx < 500; lx += 50) {
            //Height 500px but starts at 100px so ends at 600px
            for (int ly = 100; ly < 600; ly += 50) {
                //Color cyan
                g.setColor(Color.cyan);
                //Size and position are controlled by variables.
                g.drawRect(lx, ly, cellSize, cellSize);
            }
        }
        //Second Field
        //Width 500px starts at 1500px ends at 1000px
        for (int lx = 1500; lx > 1000; lx -= 50) {
            //Height 500px but starts at 100px so ends at 600px
            for (int ly = 100; ly < 600; ly += 50) {
                //Color green
                g.setColor(Color.green);
                //Size and position are controlled by variables.
                g.drawRect(lx, ly, cellSize, cellSize);
            }
        }

        //Calls render Methods from the other classes so that they will be rendered as well.
        handler.render(g);
        hud.render(g);

        //Shows the Objects and field and hides the simple Graphics.
        g.dispose();
        bs.show();
    }

}
