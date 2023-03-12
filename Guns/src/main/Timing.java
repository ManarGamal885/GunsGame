
package main;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;



/**
 *
 * @author hossa
 */
public class Timing  extends JFrame{
    
    public JLabel timeText;
    public int seconds = 0;
    String secondText = String.format("%02d", seconds);;
    int consumingTime = 0;
    
    
    Timer time = new Timer(1000 , new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                consumingTime += 1000;
                //1000 ms = 1 sec;
                seconds = consumingTime / 1000;
                secondText = String.format("%02d", seconds);
                
                
                timeText.setText(secondText);
            }
        }); 
   
    
    public Timing(){
       
        
        timeText  = new JLabel();
        timeText.setText(secondText);
        timeText.setBounds(230,120 , 400 , 40);
        timeText.setFont(new Font("", Font.BOLD, 20));   
        
    }
    
    
    public void start()
    {      
        this.time.start();
        Println("GAME IS RUNNING");
    }
    
    public void stop()
    {
        
        this.time.stop();
        Println("GAME IS STOPPING");
        
    }
    
    public void reset()
    {
        time.restart();
    }
    
    public final void Println(String s)
    {
        System.out.println(s);
    }
   
}
