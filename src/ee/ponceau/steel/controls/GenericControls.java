package ee.ponceau.steel.controls;

import ee.ponceau.steel.Main;
import ee.ponceau.steel.definitions.Controller;
import java.awt.event.KeyEvent;

/**
 *
 * @author Evan
 */
public class GenericControls extends Controller{
  @Override
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
      Main.GAME_RUNNING = false;
      return;
    }
    super.keyPressed(e);
  }
}
