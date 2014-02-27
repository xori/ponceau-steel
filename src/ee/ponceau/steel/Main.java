package ee.ponceau.steel;

import ee.ponceau.steel.definitions.Entity;
import ee.ponceau.steel.scenes.MainMenu;
import ee.ponceau.steel.util.Log;
import java.awt.Canvas;
import java.awt.Toolkit;

/**
 *
 * @author Evan
 */
public class Main {
  public final static Main i/*nstance*/ = new Main();
  
  public Window         window;
  public Canvas         canvas;
  public GraphicsEngine graphics;
  public Stage          stage = new Stage();
  public Physics        physics;
  /* TODO
  public ResourceManager loader;
   */
  
  public static boolean GAME_RUNNING = true;
  public long soFar = System.currentTimeMillis(), temp;
  
  private Main() {}
  
  public void mainLoop () {
    while(GAME_RUNNING) {
      //AI Update
      //Network Broadcast
      //Events
      //onCollision Events
      //timed Events
      //process active-events queue
      stage.currentScene.onUpdate(deltaFrom(soFar));
      for(Entity e : stage)
        e.update(deltaFrom(soFar));
      physics.update(System.currentTimeMillis() - soFar);
      //TODO VFX Pre
      graphics.draw(System.currentTimeMillis() - soFar);
      //TODO VFX Post
      window.buffer.show();
      Toolkit.getDefaultToolkit().sync();
      try {
        temp = soFar;
        soFar = System.currentTimeMillis();
        Thread.sleep((long) (1000.0 / graphics.MAXFPS) - (soFar - temp));
      } catch(Exception e) { }
    }
    window.dispose();
  }
  
  private double deltaFrom(long time) {
    return (System.currentTimeMillis() - time) / 1000.0;
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
    this.canvas = window.canvas;
    this.physics = new Physics();
    this.graphics = new GraphicsEngine(window.buffer.getDrawGraphics());
    this.stage.switchScene(new MainMenu());
    System.out.println("Debug Level: " + Log.STATE);
    this.notify(); // this begins the mainLoop
  }
}
