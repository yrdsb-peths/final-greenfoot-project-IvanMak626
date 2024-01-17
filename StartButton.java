import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends Actor
{
    //sets the start buttonimage and its size
    public StartButton()
    {
        GreenfootImage button = new GreenfootImage("images/start button.png");
        button.scale(300, 125);
        setImage(button);
    }
}
