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
    
    
    int fireMove = 0;
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
                
        addObject(knight, 300, 350);
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
            spawnOrc();
            orcTimer.mark();
        }
    }
    
    public void spawnOrc()
    {
        int randomSpawn = Greenfoot.getRandomNumber(2);
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
