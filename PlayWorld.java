import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayDeque;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayWorld extends World
{
    private int time = 20;
    private int SEC = 70;
    private int turn = 0;
    private int team = 1;
    private int oldTeam = 2;
    private int power;
    private boolean isPlay = true;
    public ArrayDeque<Fighter> playQueue = new ArrayDeque<>();
    private int sizeWidth = 6;
    private int sizeHeight = 4;
    private int numberOfPlayer = 2;
    private int numberOfPlayerInGame = 1;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public PlayWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);
        int[][] board = genarateBoard(sizeWidth,sizeHeight);
        for(int i=0; i<numberOfPlayer; i++) {
            int[] pos = spawnFighter(board);
            board[pos[0]][pos[1]] = 2;
        }
        int initPosX = 1280/2-(sizeWidth/2*100);
        int initPosY = 720/2-(sizeHeight/2*100);
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        for(int i=0; i<board[0].length; i++) {
            int posX = initPosX;
            for(int j=0; j<board.length; j++) {
                if(board[j][i] == 0 ) {
                    addObject( new Tile(), posX, initPosY);
                }
                else if(board[j][i] == 2 ) {
                    addObject( new Tile(), posX, initPosY);
                    String fighterAvatar = "images/team"+ numberOfPlayerInGame + ".png";
                    addObject( new Fighter(fighterAvatar), posX, initPosY);
                    numberOfPlayerInGame ++;       
                }
                posX +=100;
            }
            initPosY +=100;
        }
        addObject( new ShowTime(), 1095, 48 );
        //Greenfoot.start();
    }
    
    public void timeCount() {
        if(isPlay) {
            SEC--;
        }
        if(SEC == 0) {
            time--;
            SEC=70;
        }
        if(time == 0) {
            if(turn!=0) {
                stopPlayer(false);
            }
            playNext(this.team);
            toggle();
            pause();
            time=20;
            turn++;
        }
    }
    
    private void toggle () {
        if(team==1) {
            team=2;
            oldTeam=1;
        }
        else {
            team=1;
            oldTeam=2;
        }
    }
    
    public void removeChar(Fighter player) {
        playQueue.remove(player);
    }
    
    private void stopPlayer(boolean isDeath) {
        Fighter player;
        player = deQueue();    
        if(player != null) {
            if(!isDeath) {
                queue(player);
            }
            player.setIsTurn(false);
        }
    }
    
    private void playNext(int team) {
        //World world = getWorld();
        Fighter player=null;
        int win=0;
        if(playQueue.size()>0) {
            player = playQueue.peek();
        }
        
        if(player!=null) {
            player.setIsTurn(true);
        }
        /*else {
            switch (win) {
            case 1 : 
                soundTrack.stop();
                Greenfoot.setWorld(new Red());
                break;
            case 2 :
                soundTrack.stop();
                Greenfoot.setWorld(new Green());
                break;
        }
        }*/
    }
    
    private void queue (Fighter player) {
        playQueue.add(player);
    }
    
    private Fighter deQueue () {
        Fighter player=null;
        player = playQueue.poll();
        return player;
    }
    
    public void pause () {
        isPlay = false;
        Fighter player = null;
        if(playQueue.size()>0) {
            player = playQueue.peek();
            player.setIsTurn(isPlay);
        }
        addObject( new Pause(), 600 , 384);
    }
    
    public void unPause () {
        isPlay = true;
        Fighter player;
        if(playQueue.size()>0) {
            player = playQueue.peek();
            player.setIsTurn(isPlay);
        }
    }
    
    public boolean getIsPlay() {
        return isPlay;
    }
    
    public void removeQueue(int team) {
        stopPlayer(true);
    }
    
    public void setTime (int time) {
        this.time = time;
    }
    
    public int getTime() {
        return time;
    }
    
    public int getPower() {
        return power;
    }
    
    public void setPower(int power) {
        this.power=power;
    }
    
    private int[][] genarateBoard(int sizeWidth, int sizeHeight) {
        int[][] board = new int[sizeWidth][sizeHeight];
        boolean isValid = false;
        while (!isValid) {
            int pitLimit = (int)(0.25*sizeWidth*sizeHeight);
            for(int i=0; i<sizeWidth; i++) {
                for(int j=0; j<sizeHeight; j++) {
                    System.out.println(pitLimit);
                    if(pitLimit>0) {
                        if(Math.random()<0.25) {
                            board[i][j] = 1;
                            pitLimit -= 1;
                        }
                    }
                    else {
                        break;
                    }
                }
            }
            isValid = checkValidBoard(board);
        }
        return board;
    }
    
    private boolean checkValidBoard(int[][] board) {
        for(int i=0; i<board.length; i++) {
            // some Algo
        }
        return true;
    }
    
    private int[] spawnFighter(int[][] AvaliableSpace) {
        int posWidth = (int) (Math.random()*(sizeWidth));
        int posHeight = (int) (Math.random()*(sizeHeight));
        while(AvaliableSpace[posWidth][posHeight] != 0) {
            posWidth = (int) (Math.random()*(sizeWidth));
            posHeight = (int) (Math.random()*(sizeHeight));
        }
        return new int[] {posWidth,posHeight};
    }
}

