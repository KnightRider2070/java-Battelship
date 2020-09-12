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
    //Game object handler
    private Handler handler;


    //The Game logic and object initialisation
    public Game() {

        //Creating listener for Key input
        this.addKeyListener(new KeyInput(handler));

        //Creating THE handler
        handler = new Handler();

        //Creating the Window with the game in it
        new Window(WIDTH, HEIGHT, "Battelship", this);

        //Creating the hud
        hud = new HUD();

        //Adding the first objects
        handler.addObject(new Player(1, 1, ID.Player, handler));
        handler.addObject(new Destroyer(WIDTH / 2 - 52, HEIGHT / 2 - 52, ID.Destroyer, handler));


    }

    //Converts the coordinates to pixels so that you can draw the object there
    public static int[] cordsToPixelsConv(int x, int y, int field) {
        int[] tempXY = new int[2];
        if (field == 1) {

            tempXY[0] = clamp(x * 50, 0, 500);
            tempXY[1] = clamp(y * 50 + 100, 100, 600);
            return tempXY;
        } else if (field == 2) {
            tempXY[0] = clamp(x * 50 + 1000, 1000, 1500);
            tempXY[1] = clamp(y * 50 + 100, 100, 600);
            return tempXY;
        } else
            System.out.println("Error converting cords to pixel!");
        System.exit(0);
        return null;
    }

    //This clamp methode allows to be used in the tick methode and with it you can let a value never exceed the max or min
    public static int clamp(int var, int min, int max) {
        if (var >= max)
            return var = max;
        else if (var >= min)
            return var = min;
        else
            return var;
    }

    public static void main(String[] args) {
        new Game();
    }

    //Thread start
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    //Thread stop
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Game Loop which creates the refresh rate, some complex shit
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
        handler.tick();
        hud.tick();
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
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //First Field
        //Width 500px starts at 0px ends at 500px
        for (int lx = 0; lx < 500; lx += 50) {
            //Height 500px but starts at 100px so ends at 600px
            for (int ly = 100; ly < 600; ly += 50) {
                g.setColor(Color.cyan);
                g.drawRect(lx, ly, cellSize, cellSize);
            }
        }
        //Second Field
        //Width 500px starts at 1500px ends at 1000px
        for (int lx = 1500; lx > 1000; lx -= 50) {
            //Height 500px but starts at 100px so ends at 600px
            for (int ly = 100; ly < 600; ly += 50) {
                g.setColor(Color.green);
                g.drawRect(lx, ly, cellSize, cellSize);
            }
        }

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

}
