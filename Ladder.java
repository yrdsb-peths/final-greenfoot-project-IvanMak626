import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ladder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ladder extends Actor
{
    /**
     * Act - do whatever the Ladder wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Ladder()
    {
        GreenfootImage ladderImage = new GreenfootImage("images/ladder sprite.png");
        ladderImage.scale(170, 170);
        setImage(ladderImage);
    }
    
    
    
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        if(isTouching(Knight.class) && Greenfoot.isKeyDown("e"))
        {
            world.ladderTouchKnight();
        }
    }
    
    
}
