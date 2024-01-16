import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Orc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Skeleton extends Enemy
{
    GreenfootImage[] skeletonRight = new GreenfootImage[8];
    GreenfootImage[] skeletonLeft = new GreenfootImage[8];
    
    
    SimpleTimer animationTimer = new SimpleTimer();
    SimpleTimer bufferTimer = new SimpleTimer();
    SimpleTimer defeatTimer = new SimpleTimer();
    
    String facing = "right";
    int health;
    int moveLength = 0;
    
    
    public Skeleton()
    {
        for(int i = 0; i < skeletonRight.length; i++)
        {
            skeletonRight[i] = new GreenfootImage("images/skeleton w bow sprite/skeleton_walk" + (i + 9) + ".png");
            skeletonRight[i].mirrorHorizontally();
            skeletonRight[i].scale(80, 80);
        }
        
        for(int i = 0; i < skeletonLeft.length; i++)
        {
            skeletonLeft[i] = new GreenfootImage("images/skeleton w bow sprite/skeleton_walk" + (i + 9) + ".png");
            skeletonLeft[i].scale(80, 80);
        }
        
        
        animationTimer.mark();
        setImage(skeletonRight[0]);
        this.health = 5;
    }
    
    public void act()
    {
        
    }
    
    int imageIndex = 0;
    public void animateSkeleton()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        
        if(facing.equals("right"))
        {
            setImage(skeletonRight[imageIndex]);
            imageIndex = (imageIndex + 1) % skeletonRight.length;
        } 
        else if(facing.equals("left"))
        {
            setImage(skeletonLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % skeletonLeft.length;
        }
        else
        {
            return;
        }
        
    }    
}
