package ee.ponceau.steel.controls;

import ee.ponceau.steel.Main;
import ee.ponceau.steel.definitions.Controller;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 *
 * @author Evan
 */
public class GenericControls extends Controller{

  @Override
  public void mouseClicked(MouseEvent e) {
    
  }

  @Override
  public void mousePressed(MouseEvent e) {
    
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    
  }

  @Override
  public void mouseExited(MouseEvent e) {
    
  }

  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println(e.getKeyChar());
  }

  @Override
  public void keyPressed(KeyEvent e) {
    //if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
      Main.GAME_RUNNING = false;
    //}
      System.out.println(e.getKeyChar());
  }

  @Override
  public void keyReleased(KeyEvent e) {
    System.out.println(e.getKeyChar());
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    
  }
}
