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
    super.keyPressed(e);
    if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
      Main.GAME_RUNNING = false;
      return;
    } else if (e.getKeyCode() == KeyEvent.VK_MINUS) {
      Main.i.graphics.camera.dimension.x = 1600;
      Main.i.graphics.camera.dimension.y = 800;
    } else if (e.getKeyCode() == KeyEvent.VK_EQUALS) {
      Main.i.graphics.camera.dimension.x = 400;
      Main.i.graphics.camera.dimension.y = 200;
    }
  }
}
