import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Actor
{
    //image arrays for animation
    GreenfootImage[] fireRight = new GreenfootImage[8];
    GreenfootImage[] fireLeft = new GreenfootImage[8];    
    
    // determines which way Fireball faces
    String facing = "right";
    
    // timer for animation
    SimpleTimer animationTimer = new SimpleTimer();
    
    //integers
    int imageIndex = 0;
    int fireMove = 0;
    
    
    //everytime a new fireball is created
    public Fireball()
    {
        //fills out array for fireball facing right
        for(int i = 0; i < fireRight.length; i++)
        {
            fireRight[i] = new GreenfootImage("images/fireball_1/fireball0" + i + ".png");
            fireRight[i].mirrorHorizontally();
            fireRight[i].scale(70, 70);
        }
        
        //fils out array for fireball facing left
        for(int i = 0; i < fireLeft.length; i++)
        {
            fireLeft[i] = new GreenfootImage("images/fireball_1/fireball0" + i + ".png");
            fireLeft[i].scale(70, 70);
        }
        
        
        animationTimer.mark();
    }
    
    
    //cycles through frames of fireball being shot
    public void animateFireBall()
    {
        //returns to the rest of this method every 100 milliseconds
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        //reset animationTimer
        animationTimer.mark();
        
        //going to the next frame of the animation
        if(facing.equals("right"))
        {
            setImage(fireRight[imageIndex]);
            imageIndex = (imageIndex + 1) % fireRight.length;
        } 
        else if(facing.equals("left"))
        {
            setImage(fireLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % fireLeft.length;
        }
        else
        {
            return;
        }
    }
    
    
    public void act()
    {
        //if fireball hits an orc, call the hitOrc() method
        if(isTouching(Orc.class))
        {
            /* sidenote:
             * I couldn't do anything to adjust the hitboxes of the orc as
             * the dimensions of each png is locked from the spritesheet cutter
             */
            hitOrc();
        }
        /*if the fireball is touching the left or right border of the world, 
         * remove the fireball
         */
        else if(getX() == 0 || getX() == getWorld().getWidth()-1)
        {
            facing = "null";
            getWorld().removeObject(this);
        }
        
        //move the fireball wherever mouse clicked (left/right side of knight)
        move(fireMove);
        //animate the fireball
        animateFireBall();
    }
    
    //get the direction of which fireball moves from MyWorld class
    public void fireSpeed(int moveFire)
    {
        fireMove = moveFire;
    }
    
    //gets which way fireball is facing for the animation
    public void fireDirection(String direction)
    {
        facing = direction;
        
    }
    
    
    /*if orc is touching fireball, remove fireball and call the 
     * hitFireball() method in the orc class (which removes the orc)
     */
    public void hitOrc()
    {
        facing = "null";
        Orc orc = (Orc) getOneIntersectingObject(Orc.class);
        orc.hitFireball();
        getWorld().removeObject(this);
    
    }
}
