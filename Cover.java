
import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * A Cover that populates the world.
 * 
 *
 */
public class Cover extends Actor {
    int width;
    int height;

    private boolean debug = true;

    public Cover(int widthIn, int heightIn) {
        width = widthIn;
        height = heightIn;

        // Create an image of the set size using built in greenfoot commands
        GreenfootImage displayImage = new GreenfootImage(width, height);

        // Draw Circle
        displayImage.setColor(Color.LIGHT_GRAY); // Set color to ballColor
        displayImage.fill();

        // Add the image as the new image for this object
        setImage(displayImage);
    }// End Constructor

    void absorb() {
        List<RainDrop> rainDropsTouching = getIntersectingObjects(RainDrop.class);
        if (rainDropsTouching != null) {
            int dropsCatched = rainDropsTouching.size();
            if (dropsCatched > 0) {
                RainyWorld rainyWorld = (RainyWorld) getWorld();
                rainyWorld.removeObjects(rainDropsTouching);
            }
        }

    }

    public void act() {
        absorb();
    }
}
