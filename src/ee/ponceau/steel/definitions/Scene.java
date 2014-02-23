package ee.ponceau.steel.definitions;

import ee.ponceau.steel.GraphicsEngine;
import ee.ponceau.steel.Stage;

/**
 *
 * @author Evan
 */
public interface Scene {
  public void onLoad(Stage stage, GraphicsEngine g);
  public void onDestroy();
  public void onPause();
  /**
   * @param delta time since last update in seconds
   *  eg) delta = 0.5 = half a second has passed
   */
  public void onUpdate(double delta);
  public Controller getController();
}
