import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Actor
{
    GreenfootImage[] fireRight = new GreenfootImage[8];
    GreenfootImage[] fireLeft = new GreenfootImage[8];    
    
    String facing = "right";
    SimpleTimer animationTimer = new SimpleTimer();
    int imageIndex = 0;
    int fireMove = 0;
    
    MouseInfo mouseInfo = Greenfoot.getMouseInfo();
    
    
    public Fireball()
    {
        
        for(int i = 0; i < fireRight.length; i++)
        {
            fireRight[i] = new GreenfootImage("images/fireball_1/fireball0" + i + ".png");
            fireRight[i].mirrorHorizontally();
            fireRight[i].scale(70, 70);
        }
        
        for(int i = 0; i < fireLeft.length; i++)
        {
            fireLeft[i] = new GreenfootImage("images/fireball_1/fireball0" + i + ".png");
            fireLeft[i].scale(70, 70);
        }
        
        
        animationTimer.mark();
    }
    
    
    
    public void animateFireBall()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        
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
        
        if(isTouching(Orc.class))
        {
            hitOrc();
        }
        else if(getX() == 0 || getX() == getWorld().getWidth()-1)
        {
            facing = "null";
            getWorld().removeObject(this);
        }
        move(fireMove);
        animateFireBall();
    }
    
    public void fireSpeed(int moveFire)
    {
        fireMove = moveFire;
    }
    
    
    public void fireDirection(String direction)
    {
        facing = direction;
        
    }
    
    
    
    public void hitOrc()
    {
        facing = "null";
        Orc orc = (Orc) getOneIntersectingObject(Orc.class);
        orc.hitFireball();
        getWorld().removeObject(this);
    
    }
}
