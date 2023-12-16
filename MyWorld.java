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
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        addObject(knight, 300, 300);
        
        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        
        Fireball fireball = new Fireball();
        
        addObject(fireball, knight.getX(), knight.getY());
    }
}
