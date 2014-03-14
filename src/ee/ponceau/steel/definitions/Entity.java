package ee.ponceau.steel.definitions;

import ee.ponceau.steel.GraphicsEngine;
import ee.ponceau.steel.util.Vector2D;
import ee.ponceau.steel.util.Vector3D;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;


/**
 * A Drawable Abstract Entity
 * @author Evan
 */
public abstract class Entity {
  public Vector3D position;
  public Vector2D velocity;  
  public Vector2D dimension;
  /**
   * If this object is not null then it should be considered in the simulation */
  public PhysicalBody body;
  
  // Some variables to automate some tedious tasks.
  public Shape      _shape;
  
  // If true, the graphics engine will always call the draw function even if 
  // the camera isn't looking at it.
  public boolean drawAlways = false;
  
  public Entity() { 
    this(0,0,0,0);
  }
  
  /**
   * A drawable object.
   * @param x starting position
   * @param y starting position
   * @param w width, even if not used for drawing you should include this 
   * variable as it is used for determining whether to draw the object or not
   * @param h height, even if not used for drawing you should include this 
   * variable as it is used for determining whether to draw the object or not
   */
  public Entity(double x, double y, double w, double h) {
    position = new Vector3D(x, y, 0);
    velocity = new Vector3D(0, 0, 0);
    dimension = new Vector2D(w, h);
    _shape = new Rectangle();
  }
  
  public void update(double delta) {
    // Leave this up to the Physics engine.
    // position = position.add(velocity);
  }
  
  public void draw(GraphicsEngine engine, Graphics2D g) {
    _shape = new Rectangle(position.xi(), position.yi(), 
            dimension.xi(), dimension.yi());
    g.draw(_shape);
  }
  
  public boolean collides(Entity e) {
    // Do box collision. If you need more, override.
    // This is mainly only for the camera.
    return this.position.x > e.position.x + e.dimension.x ? false :
            this.position.y > e.position.y + e.dimension.y ? false :
            this.position.x + this.dimension.x < e.position.x ? false :
            this.position.y + this.dimension.y < e.position.y ? false : true;
  }
}
