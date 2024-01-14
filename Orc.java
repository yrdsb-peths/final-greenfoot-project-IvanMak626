import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Orc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Orc extends Enemy
{
    GreenfootImage[] orcRight = new GreenfootImage[8];
    GreenfootImage[] orcLeft = new GreenfootImage[8];
    
    SimpleTimer animationTimer = new SimpleTimer();
    String facing = "right";
    int health;
    int moveLength = 0;
    
    public Orc()
    {
        for(int i = 0; i < orcRight.length; i++)
        {
            orcRight[i] = new GreenfootImage("images/orc spritesheet/sprite_0" + (i + 88) + ".png");
            orcRight[i].mirrorHorizontally();
            orcRight[i].scale(85, 85);
        }
        
        for(int i = 0; i < orcLeft.length; i++)
        {
            orcLeft[i] = new GreenfootImage("images/orc spritesheet/sprite_0" + (i + 88) + ".png");
            orcLeft[i].scale(85, 85);
        }
        
        
        animationTimer.mark();
        setImage(orcRight[0]);
        this.health = 5;
    }
    
    public void act()
    {
        animateOrc();
        orcAction();
    }
    
    int imageIndex = 0;
    public void animateOrc()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        
        if(facing.equals("right"))
        {
            setImage(orcRight[imageIndex]);
            imageIndex = (imageIndex + 1) % orcRight.length;
        } 
        else if(facing.equals("left"))
        {
            setImage(orcLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % orcLeft.length;
        }
        else
        {
            return;
        }
        
    }

    
    public void hitFireball()
    {
        health--;
        if(health == 0)
        {
            facing = "null";
            getWorld().removeObject(this);
        }
    }
    
    public void orcAction()
    {
        MyWorld world = (MyWorld) getWorld();
        world.getKnightPos();
        world.orcHealth();
    }
    
    
    
    public void orcDirection(String direction)
    {
        facing = direction;
    }
}
