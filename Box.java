import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Iterator;

/**
 * A box that populates the world.
 * 
 *
 */
public class Box extends Actor {
    // Instance Constants

    // Instance Variables
    int timeScale;
    int height;
    int width;
    Color boxColor;
    int speed;
    int dropsTotal;

    private boolean debug = true;

    public Box() {
        this(2);
    }// End Constructor

    /***
     * Constructor for objects of class Ball.
     * 
     * @param sizeIn The size of the ball to create
     */
    public Box(int speedIn) {
        timeScale = 100;
        speed = speedIn;
        height = 480;
        width = 60;
        boxColor = Color.LIGHT_GRAY;

        dropsTotal = 0;

        updateImage();
    }// End Constructor

    /**
     * Returns the size of the ball
     * 
     * @return size the size of the ball
     */
    public int getDropsTotal() {
        return dropsTotal;
    }

    /**
     * Update the image to be displayed on the screen for the Ball object
     * 
     */
    void updateImage() {
        // Create an image of the set size using built in greenfoot commands
        GreenfootImage displayImage = new GreenfootImage(width, height);

        // Draw Circle
        displayImage.setColor(boxColor); // Set color to ballColor
        displayImage.fill();

        // Display the rain drop numbers
        Font displayFont = new Font(16);
        displayImage.setColor(Color.BLACK);// Set color to Black
        displayImage.setFont(displayFont);
        String strDisplay = Integer.toString(dropsTotal);
        displayImage.drawString(strDisplay, (int) ((width - strDisplay.length() * 16) / 2), (int) (height / 2));

        // Add the image as the new image for this object
        setImage(displayImage);
        // if (debug) System.out.println("===============: width " + width + " height "
        // + height);
    }

    /**
     * 
     * 
     */
    void absorb() {
        List<RainDrop> rainDropsTouching = getIntersectingObjects(RainDrop.class);
        if (rainDropsTouching != null) {
            int dropsCatched = rainDropsTouching.size();
            if (dropsCatched > 0) {
                dropsTotal += dropsCatched;
                RainyWorld rainyWorld = (RainyWorld) getWorld();
                rainyWorld.removeObjects(rainDropsTouching);
                this.updateImage();
            }
        }

    }

    /**
     * This method is called whenever the 'Act' or 'Run' button gets pressed in the
     * environment.
     */
    public void act() {
        RainyWorld rainyWorld = (RainyWorld)getWorld();
        if(rainyWorld.getIsReady()){
            absorb();
            move(speed);
            if (Greenfoot.isKeyDown("right")) {
                move(speed);
            }
            if (Greenfoot.isKeyDown("space")) {
                Greenfoot.stop();
            }
            if (this.getX() >= rainyWorld.getWidth() - (int) (width / 2)) {
                move(-speed);
            }
        }
    }

}
