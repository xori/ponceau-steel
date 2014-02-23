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
  public Vector2D dimension;
  public Shape _shape;
  
  public Entity() { 
    this(0,0,0,0);
  }
  
  public Entity(double x, double y, double w, double h) {
    position = new Vector3D(x, y, 0);
    dimension = new Vector2D(w, h);
    _shape = new Rectangle();
  }
  
  public void update(double delta) {
    
  }
  
  public void draw(GraphicsEngine engine, Graphics2D g) {
    _shape = new Rectangle(position.xi(), position.yi(), 
            dimension.xi(), dimension.yi());
    g.draw(_shape);
  }
  
  public boolean collides(Entity e) {
    // Do box collision. If you need more, override.
    return this.position.x > e.position.x + e.dimension.x ? false :
            this.position.y > e.position.y + e.dimension.y ? false :
            this.position.x + this.dimension.x < e.position.x ? false :
            this.position.y + this.dimension.y < e.position.y ? false : true;
  }
}
