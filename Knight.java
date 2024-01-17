import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knight extends Actor
{    
    //image arrays for animations
    GreenfootImage[] knightRight = new GreenfootImage[8];
    GreenfootImage[] knightLeft = new GreenfootImage[8];
    GreenfootImage[] knightDefeat = new GreenfootImage[6];
    
    //integers
    int jumpSpeed = 0;
    int shiftSpeed = 0;
    int climbSpeedY = 0;
    int totalPoints = 0;
    int upCounter = 0;
    int downCounter = 0;
    int imageIndex = 0;
    int health;
    int towerStage;
    
    //timers
    SimpleTimer animationTimer = new SimpleTimer();
    SimpleTimer orcHitBuffer = new SimpleTimer();
    SimpleTimer defeatTimer = new SimpleTimer();
    
    //determines which way knight faces
    String facing = "right";
    
    //runs this code everytime a new Knight is created (in this case only one)
    public Knight()
    {
        //fills out array for knight facing right
        for(int i = 0; i < knightRight.length; i++)
        {
            knightRight[i] = new GreenfootImage("images/wizard spritesheet/sprite_0" + (i + 69) + ".png");
            knightRight[i].mirrorHorizontally();
            knightRight[i].scale(70, 70);
        }
        
        //fills out array for knight facing left
        for(int i = 0; i < knightLeft.length; i++)
        {
            knightLeft[i] = new GreenfootImage("images/wizard spritesheet/sprite_0" + (i + 69) + ".png");
            knightLeft[i].scale(70, 70);
        }
        
        animationTimer.mark();
        setImage(knightRight[0]);
        
        //sets knight health to 5
        this.health = 5;
    }
    
    //cycles through frames of knight walking
    public void animateKnight()
    {
        //returns to the rest of this method every 100 milliseconds
        if(animationTimer.millisElapsed() < 100)
        {
            return;
        }
        //reset animationTimer
        animationTimer.mark();
        
        //going to the next frame of the animation 
        if(facing.equals("right"))
        {
            setImage(knightRight[imageIndex]);
            imageIndex = (imageIndex + 1) % knightRight.length;
        } 
        else if(facing.equals("left"))
        {
            setImage(knightLeft[imageIndex]);
            imageIndex = (imageIndex + 1) % knightLeft.length;
        }
    }
    
    //gets the floor/level that knight is on
    public void getTowerStage(int stage)
    {
        towerStage = stage;
    }
    
    public void act()
    {
        MyWorld world = (MyWorld) getWorld();
        //every second that an orc is touching the knight, knight loses 1 hp
        if(isTouching(Orc.class) && orcHitBuffer.millisElapsed() > 1000)
        {
            orcHitBuffer.mark();
            health--;
        }
        world.setKnightHealth(health);
        world.knightHealth();
        animateKnight();
        jump();
        climbLadder();
        
        /*if all enemies on the final floor have been defeated, let player win
         * by pressing 'e' at the door
         */
        if(isTouching(Door.class) && Greenfoot.isKeyDown("e") && totalPoints > 7)
        {
            world.endDoor();
            Greenfoot.stop();
        }
        // climbing ladder
        ladderSense();
        
        // jumping
        setLocation(getX() + shiftSpeed, getY() - jumpSpeed);
        // move left and right & jump, regardless of whether knight is mid-jump or not
        if(getY() == 367 || getY() == 219 || getY() == 87 || upCounter != 0 || downCounter != 0)
        {
            //'a' key to move left
            if(Greenfoot.isKeyDown("a")){
                move(-2);
                facing = "left";
            }
            //'d' key to move right
            else if(Greenfoot.isKeyDown("d")){
                move(2);
                facing = "right";
            }
            
            //if spacebar is pressed, jump
            if(Greenfoot.isKeyDown("space"))
            {
                if(jumpSpeed == 0)
                {
                    // if knight is moving left/right while spacebar pressed, get a momentum boost
                    if(facing.equals("right") && Greenfoot.isKeyDown("d"))
                    {
                        shiftSpeed = 1;
                    }
                    else if(facing.equals("left") && Greenfoot.isKeyDown("a"))
                    {
                        shiftSpeed = -1;
                    }
                    jumpSpeed = 4;
                }
            }
        }
        
        if(health == 0)
        {
            world.gameOver();
            Greenfoot.stop();
            
        }
    }
    
    // gets total number of orcs spawned on that floor
    public void points(int total)
    {
        totalPoints = total;
    }
    
    
    //allows knight to jump up and down with a single click    
    public void jump()
    {
        /*jumping that is slower when knight is further up, 
         * and faster when knight isn't that high
         */
        if(upCounter > 25)
        {
            jumpSpeed = -4;
            upCounter = 0;
        }
        else if(downCounter > 25)
        {
            jumpSpeed = 0;
            downCounter = 0;
            shiftSpeed = 0;
        }
        else if(upCounter > 12 && upCounter <= 14)
        {
            jumpSpeed = 3;
        }
        else if(downCounter > 12 && downCounter <= 14)
        {
            jumpSpeed = -3;
        }
        else if(upCounter > 14 && upCounter <= 17)
        {
            jumpSpeed = 2;
        }
        else if(downCounter > 14 && downCounter <= 17)
        {
            jumpSpeed = -2;
        }
        else if(upCounter > 17 && upCounter <= 20)
        {
            jumpSpeed = 1;
        }
        else if(downCounter > 17 && downCounter <= 20)
        {
            jumpSpeed = -1;
        }
        
        
        /*if knight has started jumping, continue the execution 
         *until knight has reached the ground
         */
        if(jumpSpeed > 0)
        {
            upCounter += 1;
        }
        else if(jumpSpeed < 0)
        {
            downCounter += 1;
        }
    }
    
    public void ladderSense()
    {
        /*if certain number of orcs defeated on that floor, allow knight
         * to climb up ladder
         */
        if((totalPoints > 3 && getY() > 219))
        {
            if(getX() > 510 && getX() < 580 && Greenfoot.isKeyDown("e"))
            {
                setLocation(getX(), getY() - climbSpeedY);
                if(getY() == 219)
                {
                    totalPoints = 0;
                }
            }
            
        }
        else if((totalPoints > 5 && getY() > 87))
        {            
            if(getX() > 0 && getX() < 85 && Greenfoot.isKeyDown("e"))
            {
                setLocation(getX(), getY() - climbSpeedY);
                if(getY() == 87)
                {
                    totalPoints = 0;
                }
            }
            
        }
    }
    
    public void climbLadder()
    {
        //if knight is not at the top of the ladder, keep allowing knight to climb
        if(getY() > 219 && towerStage == 2)
        {
            climbSpeedY = 2;
        }
        else if(getY() > 87 && towerStage == 3)
        {
            climbSpeedY = 2;
        }
    }
}
