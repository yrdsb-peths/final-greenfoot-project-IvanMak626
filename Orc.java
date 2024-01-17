import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Orc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Orc extends Enemy
{
    // image arrays for animation
    GreenfootImage[] orcRight = new GreenfootImage[8];
    GreenfootImage[] orcLeft = new GreenfootImage[8];
    GreenfootImage[] orcDefeat = new GreenfootImage[8];
    
    
    //timers
    SimpleTimer animationTimer = new SimpleTimer();
    SimpleTimer bufferTimer = new SimpleTimer();
    SimpleTimer defeatTimer = new SimpleTimer();
    
    //determines which way Orc faces
    String facing = "right";
    
    //integers
    int health;
    int moveLength = 0;
    int imageIndex = 0;
    int orcIndex = 0;
    
    //runs this code every time a new Orc is created
    public Orc()
    {
        //fills out array of orc walking right animation
        for(int i = 0; i < orcRight.length; i++)
        {
            orcRight[i] = new GreenfootImage("images/orc spritesheet/sprite_0" + (i + 88) + ".png");
            orcRight[i].mirrorHorizontally();
            orcRight[i].scale(80, 80);
        }
        
        //fills out array of orc walking left animation
        for(int i = 0; i < orcLeft.length; i++)
        {
            orcLeft[i] = new GreenfootImage("images/orc spritesheet/sprite_0" + (i + 88) + ".png");
            orcLeft[i].scale(80, 80);
        }
        
        //fills out array of orc defeat animation
        for(int i = 0; i < orcDefeat.length; i++)
        {
            orcDefeat[i] = new GreenfootImage("images/orc spritesheet/sprite_" + (i + 172) + ".png");
            orcDefeat[i].scale(80, 80);
        }
        
        
        animationTimer.mark();
        setImage(orcRight[0]);
        
        // sets health to 5 hp
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
    
    //cycles through frames of orc walking animation every 100 milliseconds
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
    
    
    // when orc is defeated, the frames of the orc dying are cycled through once
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
    
    /*if orc touches fireball, a timer starts to make time for the orc defeat animation to run
     * before removing the orc
     */
    public void hitFireball()
    {
        //reset bufferTimer
        bufferTimer.mark();
        //decreases health by 1
        health--;
        if(health == 0){
            MyWorld world = (MyWorld) getWorld();
            world.setOrcHealth(0);
            GreenfootSound orcDeath = new GreenfootSound("sounds/Lego yoda death sound.mp3");
            orcDeath.play();
        }
    }
    
    // after the timer reachers 800 milliseconds, remove the orc
    public void removeOrc()
    {
        if(bufferTimer.millisElapsed() > 800)
        {
            facing = "null";
            getWorld().removeObject(this);
        }
    }
    
    // call the getKnightPos() and the enemyHealth() methods in the MyWorld class
    public void orcAction()
    {
        MyWorld world = (MyWorld) getWorld();
        world.getKnightPos();
        world.enemyHealth();
    }
    
    
    // gets the direction orc is facing from the MyWorld class
    public void orcDirection(String direction)
    {
        facing = direction;
    }
}
