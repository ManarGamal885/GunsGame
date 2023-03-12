

package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author hossa
 */
public class StartMenu extends JFrame implements ActionListener{
    
    JButton startBtu;
    JButton exitBtu;
    GameManager gameManager;

    
    
    
    public StartMenu(GameManager gameManager)
    {
        this.gameManager = gameManager;
        
        startBtu =  new JButton();
        exitBtu = new JButton();
        startBtu.setBounds(200, 180, 100, 40);
        exitBtu.setBounds(200, 240, 100, 40);
        
        startBtu = createBtu(startBtu , "START");
        exitBtu = createBtu(exitBtu , "EXIT");
        
        
        //Add properties for JFrame screen;
        this.add(startBtu);
        this.add(exitBtu);
        
        
        //Default Setting to view the JFrame screen;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(500, 500);
        this.setVisible(true);
        setLocationRelativeTo(null);
        
        
        
    }

    
    public final JButton createBtu(JButton btu , String text)
    {
        
        btu.setText(text);
        btu.setFocusable(false);
        btu.setFont(new Font("Monospaced", Font.BOLD, 20));
        btu.setBackground(Color.lightGray);
        btu.setForeground(Color.black);
        btu.addActionListener(this);
        btu.setBorder(BorderFactory.createEtchedBorder());
        

        return btu;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startBtu)
        {
            //gameManager.sound.stopSound();
            gameManager = new GameManager(false, true);
            gameManager.time.start();
            
            
            this.dispose();
            
        }
        
        if(e.getSource() == exitBtu)
        {
            Println("GAME IS ClOSED");
            System.exit(0);
        }
    }
    
    
    public final void Println(String s)
    {
        System.out.println(s);
    }
    
}
