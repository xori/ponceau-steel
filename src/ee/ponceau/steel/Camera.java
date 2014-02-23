package ee.ponceau.steel;

import ee.ponceau.steel.definitions.Entity;
import ee.ponceau.steel.util.Vector2D;
import java.awt.Dimension;

/**
 *
 * @author Evan
 */
public class Camera extends Entity{
  public final Dimension resolution = new Dimension(800, 400);
  
  public Camera() {
    this.dimension.x = resolution.width;
    this.dimension.y = resolution.height;
  }
  
  public void pointAt(Entity e) {
    position.x = e.position.x + e.dimension.x / 2 - this.dimension.x / 2;
    position.y = e.position.y + e.dimension.y / 2 - this.dimension.y / 2;
  }
  
  public Vector2D getScale() {
    return new Vector2D(resolution.width/dimension.x, 
            resolution.height/dimension.y);
  }  

  public Vector2D getTranslation() {
    return new Vector2D(-position.x, -position.y);
  }
}
