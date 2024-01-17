import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Block here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Block extends Actor
{
    
    /* everytime a new block is created, create a new image, scale it to the 
    correct size, set the class to that image
    */
    public Block()
    {
        GreenfootImage blockImage = new GreenfootImage("images/block sprite.png");
        blockImage.scale(50, 50);
        setImage(blockImage);
    }
}
