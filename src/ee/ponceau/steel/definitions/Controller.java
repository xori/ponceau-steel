package ee.ponceau.steel.definitions;

import ee.ponceau.steel.util.LimitedQueue;
import ee.ponceau.steel.util.VirtualKeyboard;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Evan
 */
public abstract class Controller implements MouseInputListener, KeyListener, MouseWheelListener {
  public VirtualKeyboard keys = new VirtualKeyboard();
  public Mouse mouse = new Mouse();
  public class Mouse {
    public int x, y;
    public boolean left, right, middle;
    public boolean scrollUp, scrollDown;
  }
  
  @Override
  public void mouseClicked(MouseEvent e) {
    
  }

  @Override
  public void mousePressed(MouseEvent e) {
    if(e.getButton()== MouseEvent.BUTTON1){
      mouse.left = true;
    } else if (e.getButton() == MouseEvent.BUTTON2) {
      mouse.right = true;
    } else if (e.getButton() == MouseEvent.BUTTON3) {
      mouse.middle = true;
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if(e.getButton()== MouseEvent.BUTTON1){
      mouse.left = false;
    } else if (e.getButton() == MouseEvent.BUTTON2) {
      mouse.right = false;
    } else if (e.getButton() == MouseEvent.BUTTON3) {
      mouse.middle = false;
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    
  }

  @Override
  public void mouseExited(MouseEvent e) {
    
  }

  @Override
  public void keyTyped(KeyEvent e) {
    
  }

  @Override
  public void keyPressed(KeyEvent e) {
    keys.Press(e.getKeyCode());
  }

  @Override
  public void keyReleased(KeyEvent e) {
    keys.unPress(e.getKeyCode());
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    mouse.x = e.getX();
    mouse.y = e.getY();
  }
  
}
