import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    //characters
    Knight knight = new Knight();
    Orc orc = new Orc();
    
    //interactive objects
    Ladder ladderOne = new Ladder();
    Ladder ladderTwo = new Ladder();
    Door door = new Door();
    
    //healthbars
    Healthbar healthbarOrc = new Healthbar();
    Healthbar healthbarKnight = new Healthbar();
    
    // timers
    SimpleTimer orcTimer = new SimpleTimer();
    SimpleTimer fireballTimer = new SimpleTimer();
    
    // "locked" levels
    Locked_Level darkScreenOne = new Locked_Level(1100, 675);
    Locked_Level darkScreenTwo = new Locked_Level(1100, 350);
    
    // primitives
    boolean orcSpawned = false;
    boolean fireballSpawned = false;
    int totalOrcSpawned = 0;
    int towerStage = 1;
    
    // adds the objects that the player first sees when going into MyWorld
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        buildPlatform();
        addObject(darkScreenOne, getWidth()/2, 137);
        addObject(darkScreenTwo, getWidth()/2, 71);
        addObject(knight, 300, 367);
        addObject(healthbarKnight, knight.getX(), knight.getY() - 22);
        spawnOrc();
    }
    
    
    public void act()
    {
        ladderClimb();
        knight.getTowerStage(towerStage);
        if(Greenfoot.mouseClicked(null) == true)
        {
            /*if there is a fireball in the world, fireballSpawned = true,
             * vice versa with fireballSpawned = false
             */
            fireballSpawned = getObjects(Fireball.class).size() != 0;
            
            //buffers a fireball shot for 1000 milliseconds if previous fireball hits orc too quickly
            if(fireballSpawned == false && fireballTimer.millisElapsed() > 1000)
            {
                spawnFireball();
                //reset fireballTimer
                fireballTimer.mark();
            }
            
        }
        
        
        // true if at least 1 orc in world
        orcSpawned = getObjects(Orc.class).size() != 0; 
        if(orcSpawned == false)
        {
            // if all enemies on specified floor are defeated, advance to the next floor/unlock winning door
            if(totalOrcSpawned == 3 && towerStage == 1 && orcSpawned == false)
            {
                totalOrcSpawned = 4;
            }
            else if(totalOrcSpawned == 5 && towerStage == 2 && orcSpawned == false)
            {
                totalOrcSpawned = 6;
            }
            else if(totalOrcSpawned == 7 && towerStage == 3 && orcSpawned == false)
            {
                totalOrcSpawned = 8;
            }

            spawnOrc();
            //resets orcTimer
            orcTimer.mark();
        }
        
        
    }
    
    // adds doors, ladders and platforms ot the world
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
        addObject(door, 540, 65);
        addObject(ladderOne, 50, 195);
        addObject(ladderTwo, 550, 330);
    }
    
    
    // when mouse is clicked and if there isn't already a fireball spawned, spawn a fireball
    public void spawnFireball()
    {
        Fireball fireball = new Fireball();
        fireballSpawned = true;
        
        // if mouse is on right of knight, shoot right
        if(Greenfoot.getMouseInfo().getX() > knight.getX())
        {
            fireball.fireSpeed(4);
            fireball.fireDirection("right");
            addObject(fireball, knight.getX(), knight.getY());
        }
        // if mouse is on left of knight, shoot left
        else if(Greenfoot.getMouseInfo().getX() < knight.getX())
        {
            fireball.fireSpeed(-4);
            fireball.fireDirection("left");
            addObject(fireball, knight.getX(), knight.getY());
        }
    }
    
    // sets the healthbar image for how much health the orc has
    public void setOrcHealth(int healthOrc)
    {
        healthbarOrc.getHealth(healthOrc);
    }
    
    // sets the healthbar image for how much health the knight has
    public void setKnightHealth(int healthKnight)
    {
        healthbarKnight.getHealth(healthKnight);
    }
    
    
    // makes orc moves towards knight regardless of which side it is on
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
    
    
    // if all orcs on the floor are defeated, allow knight to climb the ladder
    public void ladderTouchKnight()
    {
        // checking if the first/lower ladder can be used
        if(knight.getY() == 367 && totalOrcSpawned > 3)
        {
            knight.climbLadder();
        }
        // checking if the second/higher ladder can be used
        else if(knight.getY() == 219 && totalOrcSpawned > 5)
        {
            knight.climbLadder();
        }
    }
    
    
    
    public void ladderClimb()
    {
        // enables knight to interact with ladder and the final door
        if(totalOrcSpawned > 3 && towerStage == 1)
        {
            knight.points(4);
            // removes first dark screen that unlocks level 2
            if(knight.getY() == 367)
            {
                removeObject(darkScreenOne);
            }
            removeObject(healthbarOrc);
            totalOrcSpawned = 0;
            towerStage++;
        }
        else if(totalOrcSpawned > 5 && towerStage == 2)
        {
            knight.points(6);
            // removes second dark screen that unlocks level 3
            if(knight.getY() == 219)
            {
                removeObject(darkScreenTwo);
            }
            removeObject(healthbarOrc);
            totalOrcSpawned = 0;
            towerStage++;
        }
        //enables knight to win by pressing the 'e' key at the final door
        else if(totalOrcSpawned > 7 && towerStage == 3)
        {
            
            knight.points(8);
            removeObject(healthbarOrc);
            towerStage++;
        }
    }
    
    // if not all orcs have been spawned on the specified floor, spawn an orc and its healthbar above it
    public void spawnOrc()
    {
        orc = new Orc();
        orcSpawned = true;
        if(totalOrcSpawned < 3 && towerStage == 1)
        {
            if(knight.getY() > 266)
            {
                totalOrcSpawned++;
                addObject(orc, 600, 363);
                orc.orcDirection("right");
                addObject(healthbarOrc, orc.getX()+3, orc.getY()-22);
            }
        }
        if(totalOrcSpawned < 5 && towerStage == 2)
        {
            if(knight.getY() == 219 && towerStage == 2)
            {
                totalOrcSpawned++;
                addObject(orc, 0, 219);
                orc.orcDirection("left");
                addObject(healthbarOrc, orc.getX()-4, orc.getY()-22);
            }
        }
        if(totalOrcSpawned < 7 && towerStage == 3)
        {
            if(knight.getY() == 87)
            {
                totalOrcSpawned++;
                addObject(orc, 600, 85);
                orc.orcDirection("right");
                addObject(healthbarOrc, orc.getX()+3, orc.getY()-22);
            }
        }
    }
    
    
    // makes orc's healthbar constantly stay above the orc
    public void enemyHealth()
    {
        if(towerStage == 2)
        {
            healthbarOrc.setLocation(orc.getX()-4, orc.getY()-22);
        }
        else
        {
            healthbarOrc.setLocation(orc.getX()+3, orc.getY()-22);
        }
    }
    
    // makes knight's healthbar constantly stay above the knight
    public void knightHealth()
    {
        healthbarKnight.setLocation(knight.getX(), knight.getY() - 30);
    }
    
    // adds the door that the knight has to reach in order to win
    public void endDoor()
    {
        Label gameWin = new Label("You Win!", 100);
        addObject(gameWin, 300, 200);
    }
    
    // displays game over screen if knights dies
    public void gameOver()
    {
        Label gameOverLabel = new Label("Game Over", 100);
        addObject(gameOverLabel, 300, 200);
    }
}
