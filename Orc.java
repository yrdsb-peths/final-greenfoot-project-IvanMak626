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
    GreenfootImage[] orcDefeat = new GreenfootImage[8];
    
    SimpleTimer animationTimer = new SimpleTimer();
    SimpleTimer bufferTimer = new SimpleTimer();
    SimpleTimer defeatTimer = new SimpleTimer();
    
    String facing = "right";
    int health;
    int moveLength = 0;
    
    
    public Orc()
    {
        for(int i = 0; i < orcRight.length; i++)
        {
            orcRight[i] = new GreenfootImage("images/orc spritesheet/sprite_0" + (i + 88) + ".png");
            orcRight[i].mirrorHorizontally();
            orcRight[i].scale(80, 80);
        }
        
        for(int i = 0; i < orcLeft.length; i++)
        {
            orcLeft[i] = new GreenfootImage("images/orc spritesheet/sprite_0" + (i + 88) + ".png");
            orcLeft[i].scale(80, 80);
        }
        
        for(int i = 0; i < orcDefeat.length; i++)
        {
            orcDefeat[i] = new GreenfootImage("images/orc spritesheet/sprite_" + (i + 172) + ".png");
            orcDefeat[i].scale(80, 80);
        }
        
        
        animationTimer.mark();
        setImage(orcRight[0]);
        this.health = 5;
    }
    
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        if(health != 0)
        {
            animateOrc();
            orcAction();
            world.setOrcHealth(health);
        }
        else if(health == 0)
        {
            removeOrc();
            defeatAnimate();
        }
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
    
    
    
    int orcIndex = 0;
    public void defeatAnimate()
    {
        if(defeatTimer.millisElapsed() < 100)
        {
            return;
        }
        defeatTimer.mark();
        
        setImage(orcDefeat[orcIndex]);
        orcIndex = (orcIndex + 1) % orcDefeat.length;
    }
    
    
    public void hitFireball()
    {
        bufferTimer.mark();
        health--;
        if(health == 0){
            MyWorld world = (MyWorld) getWorld();
            world.setOrcHealth(0);
        }
    }
    
    
    public void removeOrc()
    {
        if(bufferTimer.millisElapsed() > 800)
        {
            facing = "null";
            getWorld().removeObject(this);
        }
    }
    
    
    public void orcAction()
    {
        MyWorld world = (MyWorld) getWorld();
        world.getKnightPos();
        world.enemyHealth();
    }
    
    
    
    public void orcDirection(String direction)
    {
        facing = direction;
    }
}
