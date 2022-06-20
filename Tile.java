import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile extends Actor
{
    private GreenfootImage tileAvatar;
    private int tileWidth;
    private int tileHeight;
    
    public Tile () {
        this(100,100,"images/tile.png");
    }
    public Tile (int width, int height, String Avatar)
    {
        this.tileWidth = width;
        this.tileHeight = height;
        tileAvatar = new GreenfootImage(Avatar);
        tileAvatar.scale(tileWidth, tileHeight);
        setImage(tileAvatar);
    }    
}
