import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Actor
{
    GreenfootImage[] idleRight = new GreenfootImage[8];
    GreenfootImage[] idleLeft = new GreenfootImage[8];
    GreenfootImage[] idleUp = new GreenfootImage[8];
    GreenfootImage[] idleDown = new GreenfootImage[8];
    
    String facing = "right";
    SimpleTimer animationTimer = new SimpleTimer();
    
    public Fireball()
    {
        for(int i = 0; i < idleRight.length; i++)
        {
            idleRight[i] = new GreenfootImage("images/fireball_1/fireball0" + i + ".png");
            idleRight[i].mirrorHorizontally();
            idleRight[i].scale(100, 100);
        }
        
        for(int i = 0; i < idleLeft.length; i++)
        {
            idleLeft[i] = new GreenfootImage("images/fireball_1/fireball0" + i + ".png");
            idleLeft[i].scale(100, 100);
        }
        
        for(int i = 0; i < idleUp.length; i++)
        {
            idleUp[i] = new GreenfootImage("images/fireball_1/fireball" + (i+16) + ".png");
            idleUp[i].scale(100, 100);
        }
        
        for(int i = 0; i < idleDown.length; i++)
        {
            idleDown[i] = new GreenfootImage("images/fireball_1/fireball" + (i+16) + ".png");
            idleDown[i].mirrorVertically();
            idleDown[i].scale(100, 100);
        }
        
        animationTimer.mark();
        setImage(idleRight[0]);
    }
    
    int imageIndex = 0;
    public void animateFireBall()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        
        if(facing.equals("right"))
        {
            setImage(idleRight[imageIndex]);
            imageIndex = (imageIndex + 1) % idleRight.length;
        } 
        else if(facing.equals("left"))
        {
            setImage(idleLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % idleLeft.length;
        }
        else if(facing.equals("up"))
        {
            setImage(idleUp[imageIndex]);
            imageIndex = (imageIndex + 1) % idleUp.length;
        }
        else if(facing.equals("down"))
        {
            setImage(idleDown[imageIndex]);
            imageIndex = (imageIndex + 1) % idleDown.length;
        }
    }
    
    public void act()
    {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if(mouseInfo != null && Greenfoot.mouseClicked(null) == true)
        {
            if(mouseInfo.getX() > 300)
            {
                facing = "right";
            }
            else if(mouseInfo.getX() < 300)
            {
                facing = "left";
            }
            
        }
        
        
        animateFireBall();
    }
}
