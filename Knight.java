import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends Actor
{
    GreenfootImage[] knightRight = new GreenfootImage[8];
    GreenfootImage[] knightLeft = new GreenfootImage[8];
    GreenfootImage[] knightDefeat = new GreenfootImage[6];
    
    int jumpSpeed = 0;
    int shiftSpeed = 0;
    int climbSpeedY = 0;
    int totalPoints = 0;
    int upCounter = 0;
    int downCounter = 0;
    int health;
    
    SimpleTimer animationTimer = new SimpleTimer();
    SimpleTimer orcHitBuffer = new SimpleTimer();
    SimpleTimer defeatTimer = new SimpleTimer();
    
    
    String facing = "right";
    
    public Knight()
    {
        for(int i = 0; i < knightRight.length; i++)
        {
            knightRight[i] = new GreenfootImage("images/wizard spritesheet/sprite_0" + (i + 69) + ".png");
            knightRight[i].mirrorHorizontally();
            knightRight[i].scale(70, 70);
        }
        
        for(int i = 0; i < knightLeft.length; i++)
        {
            knightLeft[i] = new GreenfootImage("images/wizard spritesheet/sprite_0" + (i + 69) + ".png");
            knightLeft[i].scale(70, 70);
        }
        
        animationTimer.mark();
        setImage(knightRight[0]);
        this.health = 5;
    }
    
    int imageIndex = 0;
    public void animateKnight()
    {
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        animationTimer.mark();
        
        if(facing.equals("right"))
        {
            setImage(knightRight[imageIndex]);
            imageIndex = (imageIndex + 1) % knightRight.length;
        } 
        else if(facing.equals("left"))
        {
            setImage(knightLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % knightLeft.length;
        }
    }
    
    
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        if(isTouching(Orc.class) && orcHitBuffer.millisElapsed() > 1000)
        {
            orcHitBuffer.mark();
            health--;
        }
        world.setKnightHealth(health);
        world.knightHealth();
        animateKnight();
        jump();
        climbLadder();
        
        
        if(isTouching(Door.class) && Greenfoot.isKeyDown("e") && totalPoints > 3)
        {
            world.endDoor();
            Greenfoot.stop();
        }
        // climbing ladder
        if(totalPoints > 3)
        {
            if(getX() > 510 && getX() < 580 && getY() > 219 && Greenfoot.isKeyDown("e"))
            {
                setLocation(getX(), getY() - climbSpeedY);
                if(getY() == 219)
                {
                    totalPoints = 0;
                }
            }
            
            if(getX() > 0 && getX() < 85 && getY() > 87 && Greenfoot.isKeyDown("e"))
            {
                setLocation(getX(), getY() - climbSpeedY);
                if(getY() == 87)
                {
                    totalPoints = 0;
                }
            }
            
        }
        
        // jumping
        setLocation(getX() + shiftSpeed, getY() - jumpSpeed);
        
        if(getY() == 367 || getY() == 219 || getY() == 87 || upCounter != 0 || downCounter != 0)
        {
            if(Greenfoot.isKeyDown("a")){
                move(-2);
                facing = "left";
            }
            else if(Greenfoot.isKeyDown("d")){
                move(2);
                facing = "right";
            }
            
            if(Greenfoot.isKeyDown("space"))
            {
                if(jumpSpeed == 0)
                {
                    if(facing.equals("right") && Greenfoot.isKeyDown("d"))
                    {
                        shiftSpeed = 1;
                    }
                    else if(facing.equals("left") && Greenfoot.isKeyDown("a"))
                    {
                        shiftSpeed = -1;
                    }
                    jumpSpeed = 4;
                }
            }
        }
        
        if(health == 0)
        {
            world.gameOver();
            Greenfoot.stop();
            
        }
    }
    
    
    public void points(int total)
    {
        totalPoints = total;
    }
    
    
    
    public void jump()
    {
        if(upCounter > 25)
        {
            jumpSpeed = -4;
            upCounter = 0;
        }
        else if(downCounter > 25)
        {
            jumpSpeed = 0;
            downCounter = 0;
            shiftSpeed = 0;
        }
        else if(upCounter > 12 && upCounter <= 14)
        {
            jumpSpeed = 3;
        }
        else if(downCounter > 12 && downCounter <= 14)
        {
            jumpSpeed = -3;
        }
        else if(upCounter > 14 && upCounter <= 17)
        {
            jumpSpeed = 2;
        }
        else if(downCounter > 14 && downCounter <= 17)
        {
            jumpSpeed = -2;
        }
        else if(upCounter > 17 && upCounter <= 20)
        {
            jumpSpeed = 1;
        }
        else if(downCounter > 17 && downCounter <= 20)
        {
            jumpSpeed = -1;
        }
        
        
        
        if(jumpSpeed > 0)
        {
            upCounter += 1;
        }
        else if(jumpSpeed < 0)
        {
            downCounter += 1;
        }
    }
    
    
    public void climbLadder()
    {
        if(getY() > 266)
        {
            climbSpeedY = 2;
        }
    }
}
