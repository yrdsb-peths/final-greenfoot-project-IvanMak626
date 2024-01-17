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
    InstructionButton instruction = new InstructionButton();
    
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
        if(Greenfoot.mouseClicked(instruction))
        {
            RulesBackground rulesBg = new RulesBackground();
            Greenfoot.setWorld(rulesBg);
        }
    }
    
    //prepare title screen
    private void prepare()
    {
        
        addObject(instruction, 300, 350);
        addObject(titleLabel, 300, 90);
        addObject(startButton, 300, 240);
    }
}
