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
    Orc orc = new Orc();
    Ladder ladderOne = new Ladder();
    Ladder ladderTwo = new Ladder();
    Healthbar healthbar = new Healthbar();
    
    boolean orcSpawned = false;
    boolean fireballSpawned = false;
    
    int totalSpawned = 0;
    int towerStage = 1;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        buildPlatform();
        addObject(knight, 300, 363);
        spawnOrc();
    }
    
    
    public void act()
    {
        ladderClimb();
        if(Greenfoot.mouseClicked(null) == true)
        {
            fireballSpawned = getObjects(Fireball.class).size() != 0;
            if(fireballSpawned == false)
            {
                spawnFireball();
            }
            
        }
        
        
        // true if at least 1 orc in world
        orcSpawned = getObjects(Orc.class).size() != 0; 
        if(orcSpawned == false)
        {
            if(totalSpawned == 3 && orcSpawned == false)
            {
                totalSpawned = 4;
            }
            spawnOrc();
        }
        
        orcTimer.mark();
        
    }
    
    
    public void buildPlatform()
    {
        for(int i = 0; i < getWidth()-1; i++)
        {
            if(i % 20 == 10)
            {
                Block blockOne = new Block();
                Block blockTwo = new Block();
                
                addObject(blockOne, i, 266);
                addObject(blockTwo, i, 133);
            }
        }
        addObject(ladderOne, 50, 195);
        addObject(ladderTwo, 550, 330);
    }
    
    
    public void spawnFireball()
    {
        Fireball fireball = new Fireball();
        fireballSpawned = true;
        if(Greenfoot.getMouseInfo().getX() > knight.getX())
        {
            fireball.fireSpeed(4);
            fireball.fireDirection("right");
            addObject(fireball, knight.getX(), knight.getY());
        }
        else if(Greenfoot.getMouseInfo().getX() < knight.getX())
        {
            fireball.fireSpeed(-4);
            fireball.fireDirection("left");
            addObject(fireball, knight.getX(), knight.getY());
        }
    }
    
    
    public void getKnightPos()
    {
        if(orc.getX() > knight.getX())
        {
            orc.move(-1);
        }
        else if(orc.getX() < knight.getX())
        {
            orc.move(1);
        }
        
    }
    
    
    public void ladderTouchKnight()
    {
        if(knight.getY() > 266 && totalSpawned > 3)
        {
            knight.climbLadder();
        }
    }
    
    
    public void ladderClimb()
    {
        if(totalSpawned > 3)
        {
            knight.points(4);
        }
    }
    
    public void spawnOrc()
    {
        orc = new Orc();
        orcSpawned = true;
        if(totalSpawned < 3)
        {
            totalSpawned++;
            addObject(orc, 600, 360);
            orc.orcDirection("right");
            addObject(healthbar, orc.getX()+3, orc.getY()-22);
        }
    }
    
    public void orcHealth()
    {
        healthbar.setLocation(orc.getX()+3, orc.getY()-22);
    }
}
