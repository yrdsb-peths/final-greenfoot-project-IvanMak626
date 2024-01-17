import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TitleLabel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TitleLabel extends Actor
{
    //sets the title image and its size
    public TitleLabel()
    {
        GreenfootImage titleLabel = new GreenfootImage("images/proj title.png");
        titleLabel.scale(450, 95);
        setImage(titleLabel);
    }
}
