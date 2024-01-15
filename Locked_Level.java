import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Unlocked_Level here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Locked_Level extends Actor
{
    public Locked_Level(int height)
    {
        GreenfootImage lockedScreen = new GreenfootImage("images/unlock in game.png");
        lockedScreen.scale(1100, height);
        setImage(lockedScreen);
    }
    
    /**
     * Act - do whatever the Unlocked_Level wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
}
