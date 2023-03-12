package main;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.FPSAnimator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.media.opengl.GLCanvas;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author hossa
 */
public class Gameplay extends JFrame implements ActionListener {
    
    JButton menuBtu;
    GameManager gameManager;
    MainCode mc;
    
    public Gameplay(JLabel textTime , GameManager gameManager)
    {
        
        this.gameManager = gameManager;
        mc = new MainCode();
        
        GLCanvas glcanvas;
        Animator animator;
        glcanvas = new GLCanvas();
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        glcanvas.addGLEventListener(mc);
        mc.setCanvas(glcanvas);
        
        animator = new FPSAnimator(60);
        animator.add(glcanvas);
        animator.start();
        
        
        
        menuBtu = new JButton();
        menuBtu.setBounds(10, 10, 60, 40);
        menuBtu = createBtu(this.menuBtu, "MENU");
        
        JLabel title = new JLabel("DROP YOUR CODE");
        title.setBounds(140,200 , 400 , 40);
        title.setFont(new Font("", Font.BOLD, 20));
        
          
          //add(textTime);
          add(menuBtu);
          //add(title);
          add(glcanvas);
        
        setTitle("Guns");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
        glcanvas.requestFocus();
                
        
    }
    
    public final JButton createBtu(JButton btu , String text)
    {
        
        btu.setText(text);
        btu.setFocusable(false);
        btu.setFont(new Font("", Font.BOLD, 12));
        btu.setBackground(Color.lightGray);
        btu.setForeground(Color.black);
        btu.addActionListener(this);
        btu.setBorder(BorderFactory.createEtchedBorder());
        

        return btu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == menuBtu)
        {
            //gameManager.sound.stopSound();
            gameManager =  new GameManager(true, false);
            gameManager.time.stop();
            this.dispose();
        }
        
        
        
        
    }
}
