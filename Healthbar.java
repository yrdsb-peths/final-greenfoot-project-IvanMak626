import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Healthbar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Healthbar extends Actor
{
    //image array for all possible hitpoints
    GreenfootImage[] healthbarImage = new GreenfootImage[6];
    
    //this code runs everytime a new Healthbar is created (for Knight and for Orcs)
    public Healthbar()
    {
        //fills out the array with all the images needed
        for(int i = 0; i < healthbarImage.length; i++)
        {
            healthbarImage[i] = new GreenfootImage("images/healthbar new sprites/healthbar" + i + ".png");
            healthbarImage[i].scale(60, 15);
        }
        //sets an image to appear first
        setImage(healthbarImage[5]);
    }
    
    //sets the image of how much health the actor has
    public void getHealth(int numHealth)
    {
        setImage(healthbarImage[numHealth]);
    }
}
