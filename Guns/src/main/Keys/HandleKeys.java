
package main.Keys;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

/**
 *
 * @author hossa
 */
public class HandleKeys implements KeyListener {
    
    public final int UP = KeyEvent.VK_UP;
    public final int DOWN = KeyEvent.VK_DOWN;
    public final int LEFT = KeyEvent.VK_LEFT;
    public final int RIGHT = KeyEvent.VK_RIGHT;
    public final int SPACE = KeyEvent.VK_SPACE;

       
    
    final BitSet keyBits = new BitSet(256);
    
    @Override
    public void keyTyped( final KeyEvent e) {
    }

    @Override
    public void keyPressed(final KeyEvent e) {
        int keyCode = e.getKeyCode();
        keyBits.set(keyCode);
       
        
    }

    @Override
    public void keyReleased(final KeyEvent e) {
       int keyCode = e.getKeyCode();
       
        keyBits.clear(keyCode);
    }
    
    public boolean isKeyPressed(final int keyCode) {
        return keyBits.get(keyCode);
    }
}
