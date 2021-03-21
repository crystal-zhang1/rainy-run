
import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * A rain drop that populates the world.
 * 
 */
public class RainDrop extends Actor {
    // Instance Constants

    // Instance Variables
    int size;
    int speed;
    Font displayFont;
    int speedWind;
    Color dropColor;

    private boolean debug = true;

    /**
     * Constructor for objects of class Ball. If no input parameters are specified
     * it defaults to ball size of 20
     */
    public RainDrop() {
        this(4, 5, 90);
    }// End Constructor

    /***
     * Constructor for objects of class Ball.
     * 
     * @param sizeIn The size of the ball to create
     */
    public RainDrop(int sizeIn, int speedIn, int speedWindIn) {
        size = sizeIn;
        if (size <= 0) {
            size = 2;
        }
        speed = speedIn;
        speedWind = speedWindIn;
        dropColor = Color.CYAN;
        updateImage();
    }// End Constructor

    /**
     * Returns the size of the ball
     * 
     * @return size the size of the ball
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Checks to see if the ball is touching the bottom of the World
     * 
     * @return true if the ball is touching the edge, false if not
     */
    boolean isTouchingWorldEdge(int y, int size, int worldHeight) {
        if (debug) {
            System.out.println("Check if the ball is touching the edge of the World: " + y + " " + " size: " + size);
        }
        return (y + size >= worldHeight);
    }

    // Actor move method overriding
    // ref: https://www.javatpoint.com/method-overriding-in-java
    public void move(int speedDrop) {
        RainyWorld rainyWorld = (RainyWorld) getWorld();
        int worldWidth = rainyWorld.getWidth();
        int worldHeight = rainyWorld.getHeight();
        int newX = getX() + speedWind;
        int newY = getY() + speedDrop;
        // if(newX >= worldWidth || newY >= worldHeight) return;
        setLocation(newX, newY);
    }
    
    /**
     * Update the image to be displayed on the screen for the Ball object
     * 
     */
    void updateImage() {
        // Create an image of the set size using built in greenfoot commands
        GreenfootImage displayImage = new GreenfootImage(size, size);

        // Draw Circle
        displayImage.setColor(dropColor);
        displayImage.drawOval(0, 0, size - 1, size - 1); // Draw a circle
        displayImage.fillOval(0, 0, size - 1, size - 1);

        // Add the image as the new image for this object
        setImage(displayImage);

    }

    /**
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the
     * environment.
     */
    public void act() {
        if (this.isAtEdge()) {
            RainyWorld rainyWorld = (RainyWorld) this.getWorld();
            rainyWorld.removeObject(this);
            rainyWorld.setIsReady(true);
        } else {
            move(speed);
        }

        
    }

}
