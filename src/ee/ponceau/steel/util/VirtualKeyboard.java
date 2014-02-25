package ee.ponceau.steel.util;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Evan
 */
public class VirtualKeyboard extends HashSet<Integer>{ //TODO this should be a HashSet or HashMap
  public boolean isPressed(int keyCode) {
    return this.contains(keyCode);
  }
  public void unPress(int keyCode) {
    this.remove(keyCode);
  }
  public void Press(int keyCode) {
    if(!isPressed(keyCode)) super.add(keyCode);
  }
}
