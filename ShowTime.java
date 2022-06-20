import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Time here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShowTime extends Actor
{
    private int time;
    /**
     * Act - do whatever the Time wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        printTime();
        ((PlayWorld)getWorld()).timeCount();
    }
    private void printTime() 
    {
        time = ((PlayWorld)getWorld()).getTime();
        //test = ((BackEnd)getWorld()).getTest();
        setImage(new GreenfootImage("Time left "+time, 24, Color.BLACK, new Color(0,0,0,0))); 
    }
}
