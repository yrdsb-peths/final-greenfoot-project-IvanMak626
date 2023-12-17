import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    StartButton startButton = new StartButton();
    TitleLabel titleLabel = new TitleLabel();
    
    public TitleScreen()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        
        prepare();
    }
    
    public void act()
    {
        if(Greenfoot.mouseClicked(startButton))
        {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
    }
    
    private void prepare()
    {
        addObject(titleLabel, 300, 90);
        addObject(startButton, 300, 300);
        Label label = new Label("Use \u2190 and \u2192 to Move", 50);
        addObject(label, getWidth()/2, 175);
    }
}
