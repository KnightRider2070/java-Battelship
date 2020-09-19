package de.thecoder.main;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    //Game Window Size
    public static final int WIDTH = 1570, HEIGHT = 750;
    //Game hud
    private final HUD hud;
    //Size of a cell where a ship is placed
    private final int cellSize = 50;
    //Threads at that the game runs
    private Thread thread;
    //Is the game running boolean
    private boolean running = false;
    static int[][][] shipPosition = new int[10][10][2];
    //Game object handler
    private final Handler handler;
    private final Player player;


    //The Game logic and object initialisation
    public Game() {

        //Creating THE handler
        handler = new Handler();
        //Creating the hud
        hud = new HUD();
        //Adding the first player
        player = new Player(1, 1, ID.Player, handler, hud);

        //Creating listener for Key input
        this.addKeyListener(new KeyInput(handler));

        this.addMouseListener(new MouseInput(handler, player));


        //Creating the Window with the game in it
        new Window(WIDTH, HEIGHT, "Battelship", this);


        //test
        player.setShip(5, 5, 0, ID.Carrier);


    }

    //Converts the coordinates to pixels so that you can draw the object there
    //tempXY[] | [1] = x | [2] =y
    public static int[] cordsToPixelsConv(int x, int y, int field) {
        //Array that will be sent back with converted cords
        int[] tempXY = new int[2];
        //Asks which field is involved left or right.
        if (field == 0) {
            //Sets the values for x and y by calculating them and if they are to big or small the clamp methode will set them the right way.
            tempXY[0] = clamp(x * 50, 0, 500);
            tempXY[1] = clamp(y * 50 + 100, 100, 600);
            //Returns the Array with the XY coordinates.
            return tempXY;
            //Does the same as above with other values for field 2.
        } else if (field == 1) {
            tempXY[0] = clamp(x * 50 + 1000, 1000, 1500);
            tempXY[1] = clamp(y * 50 + 100, 100, 600);
            return tempXY;
            //If the wrong field is given you'll get a error message with the reason.
        } else
            //Message
            System.out.println("ERROR when converting cords to pixel! FIELD error!");
        //Program closes
        System.exit(0);
        return null;
    }

    //Converts the coordinates to pixels so that you can draw the object there
    public static int[] pixelsToCordConv(int x, int y) {
        //The array that will contain the XY coordinates and the calculated field.
        int[] tempXYF = new int[3];
        //Field integer
        int field = 0;
        //Converted X coordinate
        int convX = 0;
        //Converted Y coordinate
        int convY = 0;

        //Checks if the give X value is smaller than 500 this means field one.
        if (x < 500) {

            //Sets field to one.
            field = 0;

            //This is the for loop to check to which field the coordinates in pixels belong only X direction.
            // i = the beginning of one field and z is the boundary of the field and j is the field as array coordinate.
            for (int i = 0, z = 50, j = 0; z <= 500; i += 50, z += 50, j++) {
                if (x >= i && x <= z) {
                    convX = j;
                    break;
                }

            }

            //This is the for loop to check to which field the coordinates in pixels belong only Y direction.
            // i = the beginning of one field and z is the boundary of the field and j is the field as array coordinate.
            for (int i = 100, z = 150, j = 0; z <= 600; i += 50, z += 50, j++) {
                if (y >= i && y <= z) {
                    convY = j;
                    break;
                }
            }

            //Then the tried values get written into the array and checked if they are possible and if not they will be made possible.
            tempXYF[0] = clamp(convX, 0, 10);
            tempXYF[1] = clamp(convY, 0, 10);
            tempXYF[2] = field;
            //Returns the array
            return tempXYF;

            //Same as above only for field two so other values same logic.
        } else if (x > 1000) {

            field = 1;

            for (int i = 1000, z = 1050, j = 0; z <= 1500; i += 50, z += 50, j++) {
                if (x >= i && x <= z) {
                    convX = j;
                    break;
                }
            }

            for (int i = 100, z = 150, j = 0; z <= 600; i += 50, z += 50, j++) {
                if (y >= i && y <= z) {
                    convY = j;
                    break;
                }
            }


            tempXYF[0] = clamp(convX, 0, 10);
            tempXYF[1] = clamp(convY, 0, 10);
            tempXYF[2] = field;
            return tempXYF;

            //Will give an error message if you click not at a field.
        } else {
            //Message
            System.out.println("Error when converting pixels to cords! Not in the field.");
            //Closes Program
            System.exit(0);
        }
        return null;
    }

    //This clamp methode allows to be used in the tick methode and with it you can let a value never exceed the max or min
    public static int clamp(int var, int min, int max) {
        //Checks if the value is higher or equal the max.
        if (var >= max)
            //Returns the max value
            return var = max;
            //Checks if the value is less or equal min.
        else if (var <= min)
            //Returns the min value
            return var = min;
            //If the value is not equal min or max and do not exceed those you will get it back.
        else
            //Returns the value not changed
            return var;
    }

    public void arrayReader(int field) {

    }

    //Constructor to create a game you need a main methode in Java
    public static void main(String[] args) {
        new Game();
    }

    //Thread start
    public synchronized void start() {
        //Creates a new Thread
        thread = new Thread(this);
        //Starts the new Thread
        thread.start();
        //Sets boolean running true
        running = true;
    }

    //Thread stop
    public synchronized void stop() {
        //Tries to stop the thread we might expect an error
        try {
            //Kills the Thread
            thread.join();
            //Sets boolean running false
            running = false;
            //Catches Exceptions before they will effect the process.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Game Loop which creates the refresh rate, some complex shit.
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

    //One tick controlled and defined by the run methode
    private void tick() {
        //Calls the tick Methode of each Class that has one.
        handler.tick();
        hud.tick();
        player.tick();
    }

    //Renders all Objects out as Graphics so that they are visible
    private void render() {
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
