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
  
  // Some variables to automate some tedious tasks.
  public Shape      _shape;
  private Vector2D  VELOCITY_X_LIMIT;
  private Vector2D  VELOCITY_Y_LIMIT;
  private double    FRICTION;
  
  public Entity() { 
    this(0,0,0,0);
  }
  
  public Entity(double x, double y, double w, double h) {
    position = new Vector3D(x, y, 0);
    velocity = new Vector3D(0, 0, 0);
    dimension = new Vector2D(w, h);
    _shape = new Rectangle();
    VELOCITY_X_LIMIT = VELOCITY_Y_LIMIT = new Vector2D(-3, 3);
    FRICTION = 0.1;
  }
  
  public void update(double delta) {
    position = position.add(velocity);
    velocity = velocity.scale(1 - ((1/FRICTION) * delta));
  }
  
  public void setVelocityLimits(Vector2D xlimits, Vector2D ylimits){
    VELOCITY_X_LIMIT = xlimits;
    VELOCITY_Y_LIMIT = ylimits;
  }
  
  public void draw(GraphicsEngine engine, Graphics2D g) {
    _shape = new Rectangle(position.xi(), position.yi(), 
            dimension.xi(), dimension.yi());
    g.draw(_shape);
  }
  
  /**
   * Used for Camera detection.
   * This hitbox should only be used in the Camera class for determining
   * whether to draw this entity. It thus *should* also be inaccurately *large*
   * instead of accurately small.
   * @param e Entity to test.
   * @return true if entity collides with other given entity
   */
  public boolean collides(Entity e) {
    // Do box collision. If you need more, override.
    return this.position.x > e.position.x + e.dimension.x ? false :
            this.position.y > e.position.y + e.dimension.y ? false :
            this.position.x + this.dimension.x < e.position.x ? false :
            this.position.y + this.dimension.y < e.position.y ? false : true;
  }
}
