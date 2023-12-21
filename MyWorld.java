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
    Fireball fireball = new Fireball();
    Knight knight = new Knight();
    Orc orc = new Orc();
    Orc orcTwo = new Orc();
    
    boolean orcSpawned = false;
    boolean orcTwoSpawned = false;
    
    int fireMove = 0;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
                
        addObject(knight, 300, 350);
        addObject(orc, 0, 350);
        addObject(orcTwo, 600, 350);
        orcSpawned = true;
        orcTwoSpawned = true;
    }
    
    
    public void act()
    {
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        fireball.move(fireMove);
        if(Greenfoot.mouseClicked(null) == true)
        {
            if(mouseInfo.getX() > knight.getX())
            {
                fireMove = 3;
                fireball.fireDirection("right");
                addObject(fireball, knight.getX(), knight.getY());
            }
            else if(mouseInfo.getX() < knight.getX())
            {
                fireMove = -3;
                fireball.fireDirection("left");
                addObject(fireball, knight.getX(), knight.getY());
            }
        }
        
        
        if(orcTimer.millisElapsed() > 3000)
        {
            if(orcSpawned == false)
            {
                spawnOrc();
            }
            else if(orcTwoSpawned == false)
            {
                spawnSecondOrc();
            }
            orcTimer.mark();
        }
    }
    
    public void spawnOrc()
    {
        int randomSpawn = Greenfoot.getRandomNumber(2);
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
        orc.isOrcSpawned(orcSpawned);
    }
    
    
    public void spawnSecondOrc()
    {
        int rand = Greenfoot.getRandomNumber(2);
        orcTwoSpawned = true;
        if(rand == 1)
        {
            addObject(orcTwo, 0, 350);
            orcTwo.orcDirection("left");
        }
        else if(rand == 0)
        {
            addObject(orcTwo, 600, 350);
            orcTwo.orcDirection("right");
        }
        orcTwo.isOrcSpawned(orcTwoSpawned);
    }
}
