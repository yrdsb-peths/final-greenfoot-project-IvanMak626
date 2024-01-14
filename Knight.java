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
    
    int jumpSpeed = 0;
    int climbSpeedX = 0;
    int climbSpeedY = 0;
    
    int totalPoints = 0;
    
    SimpleTimer jumpTimer = new SimpleTimer();
    int upCounter = 0;
    int downCounter = 0;
    
    SimpleTimer animationTimer = new SimpleTimer();
    String facing = "right";
    
    public Knight()
    {
        for(int i = 0; i < knightRight.length; i++)
        {
            knightRight[i] = new GreenfootImage("images/wizard spritesheet/sprite_0" + (i + 69) + ".png");
            knightRight[i].mirrorHorizontally();
            knightRight[i].scale(80, 80);
        }
        
        for(int i = 0; i < knightLeft.length; i++)
        {
            knightLeft[i] = new GreenfootImage("images/wizard spritesheet/sprite_0" + (i + 69) + ".png");
            knightLeft[i].scale(80, 80);
        }
        
        animationTimer.mark();
        setImage(knightRight[0]);
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
        animateKnight();
        jump();
        climbLadder();
        if(totalPoints > 3)
        {
            if(getX() > 510 && getX() < 580 && getY() > 213 && Greenfoot.isKeyDown("e"))
            {
                setLocation(getX() + climbSpeedX, getY() - climbSpeedY);
            }
            
            if(getX() > 0 && getX() < 85 && getY() > 83 && Greenfoot.isKeyDown("e"))
            {
                setLocation(getX() + climbSpeedX, getY() - climbSpeedY);
            }
        }
        setLocation(getX(), getY() - jumpSpeed);
        
        if(getY() == 363 || getY() == 213 || getY() == 83 || upCounter != 0 || downCounter != 0)
        {
            if(Greenfoot.isKeyDown("a")){
                move(-3);
                facing = "left";
            }
            else if(Greenfoot.isKeyDown("d")){
                move(3);
                facing = "right";
            }
            
            if(Greenfoot.isKeyDown("space"))
            {
                if(jumpSpeed == 0)
                {
                    jumpSpeed = 4;
                }
            }
        }
    }
    
    public void points(int total)
    {
        totalPoints = total;
    }
    
    public void jump()
    {
        if(upCounter > 13)
        {
            jumpSpeed = -4;
            upCounter = 0;
        }
        else if(downCounter > 13)
        {
            jumpSpeed = 0;
            downCounter = 0;
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
            climbSpeedX = 1;
            climbSpeedY = 5;
        }
    }
}
