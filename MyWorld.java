import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    public Knight knight = new Knight();
    Fireball fireball = new Fireball();
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        addObject(knight, 300, 350);
        
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        
        if(Greenfoot.mouseClicked(null))
        {
            addObject(fireball, knight.getX(), knight.getY());
        }
    }
    
    public void act()
    {
        fireball.setLocation(knight.getX(), knight.getY());
        
    }
}
