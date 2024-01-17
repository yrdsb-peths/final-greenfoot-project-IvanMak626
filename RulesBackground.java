import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class RulesBackground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RulesBackground extends World
{
    //variables
    ExitButton exitButton = new ExitButton();
    
    //instructions on how to play
    Label rulesTitle = new Label("How To Play", 60);
    Label moveLabel = new Label("1. Press 'a' and 'd' to move left and right", 40);
    Label jumpLabel = new Label("2. Press spacebar to jump", 40);
    Label interactLabelOne = new Label("3. Hold down 'e' to interact with", 40);
    Label interactLabelTwo = new Label("ladders and doors (only when all", 40);
    Label interactLabelThree = new Label("enemies on that floor are defeated)", 40);
    
    /**
     * Constructor for objects of class RulesBackground.
     * 
     */
    public RulesBackground()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
        prepare();
    }
    
    public void act()
    {
        //if the exit button is clicked, exit out of the instructions page
        if(Greenfoot.mouseClicked(exitButton))
        {
            TitleScreen titleWorld = new TitleScreen();
            Greenfoot.setWorld(titleWorld);
        }
    }
    
    //adds all the text to the instruction page
    public void prepare()
    {
        addObject(exitButton, 560, 40);
        addObject(rulesTitle, 300, 50);
        addObject(moveLabel,  300, 150);
        addObject(jumpLabel, 200, 200);
        addObject(interactLabelOne, 238, 250);
        addObject(interactLabelTwo, 280, 300);
        addObject(interactLabelThree, 293, 350);
    }
}
