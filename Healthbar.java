import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Healthbar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Healthbar extends Actor
{
    GreenfootImage[] healthbarImage = new GreenfootImage[6];
    
    /**
     * Act - do whatever the Healthbar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Healthbar()
    {
        for(int i = 0; i < healthbarImage.length; i++)
        {
            healthbarImage[i] = new GreenfootImage("images/healthbar new sprites/healthbar" + i + ".png");
            healthbarImage[i].scale(60, 15);
        }
        setImage(healthbarImage[5]);
    }
    
    public void getHealth(int numHealth)
    {
        setImage(healthbarImage[numHealth]);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
