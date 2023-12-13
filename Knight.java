import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends Actor
{
    GreenfootImage knightImage = new GreenfootImage("images/knight sprite.png");
    /**
     * Act - do whatever the Knight wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Knight()
    {
        knightImage.scale(120, 100);
        setImage(knightImage);
    }
    
    
    SimpleTimer jumpTimer = new SimpleTimer();
    int counter = 0;
    public void act()
    {
        if(Greenfoot.isKeyDown("a")){
            move(-2);
            
        }
        else if(Greenfoot.isKeyDown("d")){
            move(2);
            
        }        
        jump();
    }
    
    public void jump()
    {
        if(Greenfoot.isKeyDown("w"))
        {
            int x = getX();
            int y = getY();
            if(jumpTimer.millisElapsed() > 30)
            {
                if(counter <= 5)
                {
                    setLocation(x, y - 10);
                    counter+= 1;
                    jumpTimer.mark();
                }
                else
                {
                    if(counter >= 10)
                    {
                        counter = 0;
                    }
                    setLocation(x, y + 10);
                    counter += 1;
                    jumpTimer.mark();
                }           
            }
        }
    }
}
