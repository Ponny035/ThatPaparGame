import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fighter extends Actor {
    private GreenfootImage fighterAvatar;
    private int fighterWidth;
    private int fighterHeight;
    private int team;
    private int health;
    private int rotation = 0;
    private final int MAXHEALTH;
    private boolean isTurn;
    private boolean isDead;
    private int weaponDmg = 1;
    private int weaponRange =1;
    
    public Fighter() {
        this(5,50, 50, "images/player.png", true, false,1);
    }
    
    public Fighter(String Avatar) {
        this(5,50, 50, Avatar, true, false,1);
    }
    
    public Fighter(int health, int width, int height, String Avatar, boolean isTurn, boolean isDead, int team) {
      MAXHEALTH = health;
      this.team = team;
      this.health = MAXHEALTH;
      this.fighterWidth = width;
      this.fighterHeight = height;
      this.isTurn = isTurn;
      fighterAvatar = new GreenfootImage(Avatar);
      fighterAvatar.scale(fighterWidth, fighterHeight);
      setImage(fighterAvatar);
    }
    
    private boolean checkMove() {
        int x = getX();
        int y = getY();
        if((Greenfoot.isKeyDown("left"))||(Greenfoot.isKeyDown("a"))) {
            setLocation(x-100, y);
            setRotation(-90);
            rotation=-90;
            return true;
        }
        if(Greenfoot.isKeyDown("right")||(Greenfoot.isKeyDown("d"))) {
            setLocation(x+100, y);
            setRotation(90);
            rotation=90;
            return true;
        }
        if((Greenfoot.isKeyDown("up"))||(Greenfoot.isKeyDown("w"))) {
            setLocation(x, y-100);
            setRotation(0);
            rotation=0;
            return true;
        }
        if(Greenfoot.isKeyDown("down")||(Greenfoot.isKeyDown("s"))) {
            setLocation(x, y+100);
            setRotation(180);
            rotation=180;
            return true;
        }
        return false;
    }
    private Actor checkAttack (int rotation, int weaponRange, Actor enemy) {
        int x = getX();
        int y = getY();
        try {
            int enemyX = enemy.getX();
            int enemyY = enemy.getY();
            if ((rotation == 90) ||(rotation == -90)) {
                if (y == enemyY) {
                    for (int i = 1; i<=weaponRange; i++) {
                        if( (x+(i*100)) == (enemyX)) {
                            return enemy;
                        }
                    }  
                }
            }
            
            else if ((rotation == 0) ||(rotation == 180)) {
                if (x == enemyX) {
                    for (int i = 1; i<=weaponRange; i++) {
                        if( (y+(i*100)) == (enemyY)) {
                            return enemy;
                        }
                    }  
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error: Enemy Does exist ");
        }
        return null;
    }
    
    public void takeDmg(int dmg, int rotation) {
        this.health -= dmg;
        int x = getX();
        int y = getY();
        if(rotation == -90) {
            setLocation(x-100, y);
        }
        else if(rotation == 90) {
            setLocation(x+100, y);
        }
        else if(rotation == 0) {
            setLocation(x, y-100);
        }
        else if(rotation == 180) {
            setLocation(x, y+100);
        }
        checkAlive();
    }
    
    private void attack(Fighter enemy, int weaponDmg, int rotation) {
        enemy.takeDmg(weaponDmg,rotation);
    }
    
    private void checkAlive() {
        if(!isTouching(Tile.class) || (health == 0)){;
            ((PlayWorld)getWorld()).removeChar(this);
            getWorld().removeObject(this);
        }
    }
    
    public void act() 
    {
        if(isTurn) {
            isTurn = !checkMove();
        }
        for (Object enemy : getWorld().getObjects(Fighter.class)) {
            if(checkAttack(rotation, weaponRange,(Actor)enemy) != null) {
                System.out.println("att");
                attack((Fighter)enemy, weaponDmg, rotation);
            }
        }
        checkAlive();
    }
    
    public void setIsTurn (boolean isTurn) {
        this.isTurn = isTurn;
    }
}