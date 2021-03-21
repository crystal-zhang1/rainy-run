
import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * A box that populates the world.
 * 
 *
 */
public class Roof extends Actor {
    int width;
    int height;
    
    private boolean debug = true;

    public Roof(int widthIn, int heightIn) {
        width = widthIn;
        height = heightIn;

        // Create an image of the set size using built in greenfoot commands
        GreenfootImage displayImage = new GreenfootImage(width, height);

        // Draw Circle
        displayImage.setColor(Color.GRAY); // Set color to ballColor
        displayImage.fill();

        // Add the image as the new image for this object
        setImage(displayImage);
    }// End Constructor

}
