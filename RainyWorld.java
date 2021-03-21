import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.awt.Color;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
// import java.util.concurrent.Executors;
// import java.util.concurrent.ScheduledExecutorService;
// import java.util.concurrent.TimeUnit;

/**
 * This is a rainy world
 * 
 * 
 */
public class RainyWorld extends World {
    // Class Constants
    private static int WORLD_WIDTH = 1800;
    private static int WORLD_HEIGHT = 600;
    private static int BOX_WIDTH = 60;
    private static int BOX_HEIGHT = 480;
    private static int BOX_SPEED = 1;
    private static int CONCENTRATION = 200;
    private static int DROP_DISTANCE = 20;
    private static int DROP_SIZE = 4;
    private static int DROP_SPEED = 2;
    private static int WIND_SPEED = -1;
    private static int ROOF_WIDTH = 78;
    private static int ROOF_HEIGHT = 20;
    private static int COVER_WIDTH = 2;
    private static int COVER_HEIGHT = 800;

    // Instance Variables
    private boolean debug = false; // Turn on to enable extra debugging statements

    int spawnTimer;
    boolean isReady;

    // ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    // Runnable task = new Runnable() {
    //    public void run() {
            // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            // Date date = new Date();
            // System.out.println(dateFormat.format(date));
    
            // spawnTimer = (spawnTimer + 1) % 4; // repeat every 1 seconds
            // if (spawnTimer == 0) // at each timer reset
    //        {
                // generateRainDrops();
    //        }
    //    }
    //};
    
    /**
     * Constructor for objects of class KatamariWorld.
     * 
     */
    public RainyWorld() {
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(WORLD_WIDTH, WORLD_HEIGHT, 1); // Please do not make this bigger, I mark on a laptop screen

        spawnTimer = 0;
        isReady = false;
        initialize();
    }

    /**
     * Place a ball on a random location on the board
     * 
     * @return Returns true if successful, false if not successful
     */
    private void initialize() {
        // Add a ball to the world
        Box box = new Box(BOX_SPEED);
        addObject(box, (int) (BOX_WIDTH / 2 + 2), (int) (WORLD_HEIGHT - BOX_HEIGHT / 2 + 2));

        Roof roofLeft = new Roof(ROOF_WIDTH, ROOF_HEIGHT);
        addObject(roofLeft, (int) (ROOF_WIDTH / 2), (int) (ROOF_HEIGHT / 2));

        Roof roofRight = new Roof(ROOF_WIDTH, ROOF_HEIGHT);
        addObject(roofRight, (int) (WORLD_WIDTH - ROOF_WIDTH / 2), (int) (ROOF_HEIGHT / 2));

        Cover coverLeft = new Cover(COVER_WIDTH, COVER_HEIGHT);
        addObject(coverLeft, (int) (ROOF_WIDTH + COVER_WIDTH / 2), (int) (COVER_HEIGHT / 2));

        Cover coverRight = new Cover(COVER_WIDTH, COVER_HEIGHT);
        addObject(coverRight, (int) (WORLD_WIDTH - ROOF_WIDTH - COVER_WIDTH / 2), (int) (COVER_HEIGHT / 2));

    }

    void generateRainDrops() {
        // World width 1200 - roof 200 * 2 = 800. Rain drop size 4 distance 4, total
        // WORLD_WIDTH
        int totalPerLine = (int) ((WORLD_WIDTH - 2 * ROOF_WIDTH) / (DROP_SIZE + DROP_DISTANCE));
        // System.out.println("totalPerLine " + totalPerLine);
        // totalPerLine = 2;
        for (int i = 0; i < totalPerLine; i++) {
            RainDrop drop = new RainDrop(DROP_SIZE, DROP_SPEED, WIND_SPEED);
            addObject(drop, ROOF_WIDTH + COVER_WIDTH + (int)(DROP_DISTANCE/2) + (DROP_SIZE + DROP_DISTANCE) * i, (int) (DROP_SIZE / 2) + 2);
        }
    }

    public void setIsReady(boolean isReadyIn) {
        isReady = isReadyIn;
    }

    public boolean getIsReady() {
        return isReady;
    }

    // public void dkdk(Box box) {
    //    int initialDelay = 10;
    //    int periodicDelay = 100;

        // scheduler.scheduleAtFixedRate(task, initialDelay, periodicDelay, TimeUnit.MILLISECONDS);
    // }

    public void act() {

            
            spawnTimer = (spawnTimer + 1) % (90*CONCENTRATION/1000);
            if (spawnTimer == 1) // at each timer reset
            {
               DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
               Date date = new Date();
               System.out.println(dateFormat.format(date));
                generateRainDrops();
            }
        
    }
}
