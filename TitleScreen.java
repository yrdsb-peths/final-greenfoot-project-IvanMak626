import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleScreen extends World
{
    //variables
    StartButton startButton = new StartButton();
    TitleLabel titleLabel = new TitleLabel();
    Locked_Level ruleBox= new Locked_Level(450, 150);
    Label rules = new Label("How To Play", 45);
    
    public TitleScreen()
    {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        prepare();
    }
    
    public void act()
    {
        // if start button pressed, start the game
        if(Greenfoot.mouseClicked(startButton))
        {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
        //if instruction button is pressed, go to RulesBackground world
        if(Greenfoot.mouseClicked(ruleBox) || Greenfoot.mouseClicked(rules))
        {
            RulesBackground rulesBg = new RulesBackground();
            Greenfoot.setWorld(rulesBg);
        }
    }
    
    //prepare title screen
    private void prepare()
    {
        addObject(ruleBox, 300, 188);
        addObject(titleLabel, 300, 90);
        addObject(startButton, 300, 300);
        addObject(rules, 300, 185);
    }
}
