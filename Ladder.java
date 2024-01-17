import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ladder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ladder extends Actor
{
    //contructs the ladders on the first and second floor
    public Ladder()
    {
        GreenfootImage ladderImage = new GreenfootImage("images/ladder sprite.png");
        ladderImage.scale(75, 150);
        setImage(ladderImage);
    }
    
    
    /* if ladder is touching the knight and the 'e' key is pressed, 
     *  call the ladderTouchKnight() method from the MyWorld class
    */
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        if(isTouching(Knight.class) && Greenfoot.isKeyDown("e"))
        {
            world.ladderTouchKnight();
        }
    }
    
    
}
