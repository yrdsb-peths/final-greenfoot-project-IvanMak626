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
    Locked_Level darkScreenOne = new Locked_Level(675);
    Locked_Level darkScreenTwo = new Locked_Level(350);
    
    boolean orcSpawned = false;
    boolean fireballSpawned = false;
    
    int totalSpawned = 0;
    int towerStage = 1;
    
    SimpleTimer fireballTimer = new SimpleTimer();
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        buildPlatform();
        addObject(knight, 300, 367);
        spawnOrc();
        addObject(darkScreenOne, getWidth()/2, 137);
        
    }
    
    
    public void act()
    {
        ladderClimb();
        if(Greenfoot.mouseClicked(null) == true)
        {
            fireballSpawned = getObjects(Fireball.class).size() != 0;
            if(fireballSpawned == false && fireballTimer.millisElapsed() > 1000)
            {
                spawnFireball();
                fireballTimer.mark();
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
            orcTimer.mark();
        }
        
        
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
    
    
    public void setOrcHealth(int health)
    {
        healthbar.getHealth(health);
    }
    
    
    public void getKnightPos()
    {
        if(orc.getX() > knight.getX())
        {
            orc.move(-1);
            orc.orcDirection("right");
        }
        else if(orc.getX() < knight.getX())
        {
            orc.move(1);
            orc.orcDirection("left");
        }
        
    }
    
    
    public void ladderTouchKnight()
    {
        if((knight.getY() > 266 && totalSpawned > 3))
        {
            knight.climbLadder();
        }
    }
    
    
    public void ladderClimb()
    {
        if(totalSpawned > 3)
        {
            knight.points(4);
            if(knight.getY() == 367)
            {
                removeObject(darkScreenOne);
                addObject(darkScreenTwo, getWidth()/2, 71);
            }
            else if(knight.getY() == 219)
            {
                removeObject(darkScreenTwo);
            }
            removeObject(healthbar);
            totalSpawned = 0;
            towerStage++;
        }
    }
    
    
    public void spawnOrc()
    {
        orc = new Orc();
        orcSpawned = true;
        if(totalSpawned < 3)
        {
            if(knight.getY() > 266 && towerStage == 1)
            {
                totalSpawned++;
                addObject(orc, 600, 363);
                orc.orcDirection("right");
                addObject(healthbar, orc.getX()+3, orc.getY()-22);
            }
            else if(knight.getY() == 219 && towerStage == 2)
            {
                totalSpawned++;
                addObject(orc, 0, 219);
                orc.orcDirection("left");
                addObject(healthbar, orc.getX()+3, orc.getY()-22);
            }
            else if(knight.getY() == 87 && towerStage == 3)
            {
                totalSpawned++;
                addObject(orc, 600, 87);
                orc.orcDirection("right");
                addObject(healthbar, orc.getX()+3, orc.getY()-22);
            }
        }
    }
    
    public void orcHealth()
    {
        healthbar.setLocation(orc.getX()+3, orc.getY()-22);
    }
}
