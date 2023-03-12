
package main;


public class GameManager {
    
    public boolean menuOn;
    public boolean gameplayOn;
    public  Timing time;
    //public Sound sound = new Sound();
 
    
    public GameManager(boolean menuOn , boolean gameplayOn)
    {
        
        this.menuOn = menuOn;
        this.gameplayOn = gameplayOn;
        time = new Timing();
        
        // Active the menu windows
        if(this.menuOn)
        {
            StartMenu  menu = new StartMenu(this);
            //sound.playSound(0);

        }
        
        // Active the gameplay windows
        if(this.gameplayOn)
        {
            
            Gameplay gameplay = new Gameplay(time.timeText , this);
            //sound.playSound(1);
        }
        
    }
    
    public GameManager()
    {
        menuOn = true;
        gameplayOn = false;
        time = new Timing();
        //sound.playSound(0);
        
        
        StartMenu  menu = new StartMenu(this);
        
    }
    
    
    public void setGamePlayOn(boolean gameplayOn)
    {
        this.gameplayOn = gameplayOn;
    }
    
    public boolean getGamePlayOn()
    {
        return this.gameplayOn;
    }
}
