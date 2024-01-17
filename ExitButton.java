import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Exit_Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExitButton extends Actor
{
    // this button exits back to the title screen when pressed, from the instructions page
    public ExitButton()
    {
        GreenfootImage exitButton = new GreenfootImage("images/exit button.png");
        exitButton.scale(49, 50);
        setImage(exitButton);
    }
}
