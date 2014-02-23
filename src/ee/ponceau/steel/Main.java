package ee.ponceau.steel;

import ee.ponceau.steel.definitions.Entity;
import ee.ponceau.steel.scenes.MainMenu;
import java.awt.Toolkit;

/**
 *
 * @author Evan
 */
public class Main {
  public final static Main i/*nstance*/ = new Main();
  
  public Window         window;
  public GraphicsEngine graphics;
  public Stage          stage = new Stage();
  /* TODO
  public PhysicsEngine  physics; ?? is it needed? Or just do it in the Entity
  public ResourceManager loader;
   */
  
  public static boolean GAME_RUNNING = true;
  
  private Main() {}
  
  public synchronized void mainLoop () {
    long soFar = System.currentTimeMillis(), temp;
    while(GAME_RUNNING) {
      //AI Update
      //Network Broadcast
      //Events
      //onCollision Events
      //timed Events
      //process active-events queue
      stage.currentScene.onUpdate((System.currentTimeMillis() - soFar) / 1000.0);
      for(Entity e : stage) {
        e.update((System.currentTimeMillis() - soFar) / 1000.0);
      }
      
    // <graphics>
      //TODO VFX Pre
      graphics.draw(System.currentTimeMillis() - soFar);
      //TODO VFX Post
      //graphics.g.dispose();
      window.buffer.show();
      Toolkit.getDefaultToolkit().sync();
    // </graphics>
      try {
        temp = soFar;
        soFar = System.currentTimeMillis();
        Thread.sleep((long) (1000.0 / 60.0) - (soFar - temp));
      } catch(Exception e) { }
    }
    window.dispose();
  }

  /**
   * this function puts the mainLoop thread to sleep.
   */
  public synchronized void hold() {
    try {
      this.wait();
    } catch (InterruptedException ex) {}
  }
  /**
   * The initilizer method.
   * This connects any engine with it's dependency and starts the default scene.
   * @param window 
   */
  public synchronized void setWindow(Window window) {
    this.window = window;
    this.stage.switchScene(new MainMenu());
    this.graphics = new GraphicsEngine(window.buffer.getDrawGraphics());
    this.notify(); // this begins the mainLoop
  }
}
