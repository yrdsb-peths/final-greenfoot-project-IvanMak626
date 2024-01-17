import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Door here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Door extends Actor
{
    // creates the final door that allows the player to win
    public Door()
    {
        GreenfootImage doorImage = new GreenfootImage("images/castle door.png");
        doorImage.scale(100, 115);
        setImage(doorImage);
    }
}
