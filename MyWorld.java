import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    SimpleTimer orcTimer = new SimpleTimer();
    Knight knight = new Knight();
    
    
    boolean orcSpawned = false;
    boolean fireballSpawned = false;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
                
        addObject(knight, 300, 350);
        
    }
    
    
    public void act()
    {
        if(Greenfoot.mouseClicked(null) == true)
        {           
            fireballSpawned = getObjects(Fireball.class).size() != 0;
            if(fireballSpawned == false)
            {
                spawnFireball();
            }
            
        }
        
        if(orcTimer.millisElapsed() > 3000)
        {
            // true if at least 1 orc in world
            orcSpawned = getObjects(Orc.class).size() != 0; 
            if(orcSpawned == false)
            {
                spawnOrc();
            }
            
            orcTimer.mark();
        }
    }
    
    
    
    public void spawnFireball()
    {
        Fireball fireball = new Fireball();
        fireballSpawned = true;
        if(Greenfoot.getMouseInfo().getX() > knight.getX())
        {
            fireball.fireSpeed(3);
            fireball.fireDirection("right");
            addObject(fireball, knight.getX(), knight.getY());
        }
        else if(Greenfoot.getMouseInfo().getX() < knight.getX())
        {
            fireball.fireSpeed(-3);
            fireball.fireDirection("left");
            addObject(fireball, knight.getX(), knight.getY());
        }
    }
    
    
    public void spawnOrc()
    {
        int randomSpawn = Greenfoot.getRandomNumber(2);
        Orc orc = new Orc();
        orcSpawned = true;
        if(randomSpawn == 1)
        {
            addObject(orc, 0, 350);
            orc.orcDirection("left");
        }
        else if(randomSpawn == 0)
        {
            addObject(orc, 600, 350);
            orc.orcDirection("right");
        }
    }
    
}
