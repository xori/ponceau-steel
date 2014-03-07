package ee.ponceau.steel.entities;

import ee.ponceau.steel.GraphicsEngine;
import ee.ponceau.steel.definitions.Entity;
import ee.ponceau.steel.definitions.PhysicalBody;
import ee.ponceau.steel.util.Vector2D;
import ee.ponceau.steel.util.Vector3D;
import java.awt.Graphics2D;
import static ee.ponceau.steel.definitions.PhysicalBody.Types.POLYGON;

/**
 *
 * @author Evan
 */
public class Person extends Entity{
  private double radius;
  public double friction = 0.3; // % of initial speed after 1 second.
  public double speed = 200; // pixels per second squared.
  
  public Person(double x, double y, double radius) {
    position = new Vector3D(x, y, 0);
    dimension = new Vector2D(radius, radius);
    this.radius = radius;
    this.body = new PhysicalBody(this, PhysicalBody.Types.CIRCLE, Person.class, Wall.class);
  }

  @Override
  public void update(double delta) {
    if(velocity.y != 0)
//      velocity.y *= Math.exp(-Math.abs(friction /velocity.y) * delta);
      velocity.y *= Math.pow(friction, delta);
    if(velocity.x != 0)
//      velocity.x *= Math.exp(-Math.abs(friction /velocity.x) * delta);
      velocity.x *= Math.pow(friction, delta);
    if(Math.abs(velocity.x) < friction / 30) velocity.x = 0;
    if(Math.abs(velocity.y) < friction / 30) velocity.y = 0;
    velocity.limit(-100, 100);
  }

  @Override
  public void draw(GraphicsEngine engine, Graphics2D g) {
    g.drawOval(position.xi(), position.yi(),(int) radius * 2, (int) radius * 2);
  }
}
