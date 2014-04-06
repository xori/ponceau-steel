package ee.ponceau.steel;

import ee.ponceau.steel.definitions.Entity;
import ee.ponceau.steel.util.Vector2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Evan
 */
public class GraphicsEngine {
  private final Stage stage;
  public Graphics2D g;
  public Camera camera = new Camera();
  
  public final double MAXFPS = 40;
  
  public GraphicsEngine(Graphics g){
    this.g = (Graphics2D) g;
    this.stage = Main.i.stage;
  }
  
  public void draw(long delta /* in ms */) {
    Graphics2D newG = (Graphics2D) g.create();
    newG.clearRect(0, 0, camera.resolution.width, camera.resolution.height);
    
    // Set scaling (in scene)
    // camera.dimension.x = camera.resolution.width * 2;
    // apply scaling
    Vector2D scale = camera.getScale();
    newG.scale(scale.x, scale.y);
    // set camera (in scene)
    // camera.pointAt(stage.get(0));
    // apply camera
    Vector2D translation = camera.getTranslation();
    newG.translate(translation.x, translation.y);
    
    // sort entities from bottom to top
    // using their z co-ordinate
    Collections.sort(stage, (Entity o1, Entity o2) -> 
            o2.position.zi() - o1.position.zi());
    
    // draw.
    for(Entity e : stage) {
      if(e.drawAlways || camera.collides(e)){
        e.draw(this, newG);
      }
    }
    
    newG.dispose();
  }
}
