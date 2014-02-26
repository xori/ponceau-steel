package ee.ponceau.steel.entities;

import ee.ponceau.steel.GraphicsEngine;
import ee.ponceau.steel.definitions.Entity;
import ee.ponceau.steel.util.Vector2D;
import ee.ponceau.steel.util.Vector3D;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * @author Evan
 */
public class Person extends Entity{
  private double radius;
  
  public Person(double x, double y, double radius) {
    position = new Vector3D(x, y, 0);
    dimension = new Vector2D(radius, radius);
    this.radius = radius;
  }

  @Override
  public void update(double delta) {
    super.update(delta);
  }

  @Override
  public void draw(GraphicsEngine engine, Graphics2D g) {
    g.drawOval(position.xi(), position.yi(),(int) radius, (int) radius);
  }

  @Override
  public boolean collides(Entity e) {
    if(e instanceof Person){
      return position.to2D().distance(e.position) < ((Person) e).radius + radius;
    } else if (e instanceof Wall) {
      
    }
    return false;
  }
}
