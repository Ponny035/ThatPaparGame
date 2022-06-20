import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Pause here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pause extends Actor
{
    private boolean isPlay;
    /**
     * Act - do whatever the Pause wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkPause ();
    }    
    private void checkPause () {
        isPlay = ((PlayWorld)getWorld()).getIsPlay();
        if(isPlay) {
            getWorld().removeObject(this);
        }
    }
}
