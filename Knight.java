import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends Actor
{
    
    public Knight()
    {
        GreenfootImage knightImage = new GreenfootImage("images/knight sprite.png");
        knightImage.scale(120, 100);
        setImage(knightImage);
    }
    
    int jumpSpeed = 0;
    SimpleTimer jumpTimer = new SimpleTimer();
    int upCounter = 0;
    int downCounter = 0;
    
    public void act()
    {
        jump();
        setLocation(getX(), getY() - jumpSpeed);
        
        if(Greenfoot.isKeyDown("a")){
            move(-2);
            
        }
        else if(Greenfoot.isKeyDown("d")){
            move(2);
            
        }   
        
        if(Greenfoot.isKeyDown("space"))
        {
            if(jumpSpeed == 0)
            {
                jumpSpeed = 4;
            }
        }
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
}
