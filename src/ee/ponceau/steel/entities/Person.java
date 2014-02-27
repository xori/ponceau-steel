package ee.ponceau.steel.entities;

import ee.ponceau.steel.GraphicsEngine;
import ee.ponceau.steel.Physics;
import ee.ponceau.steel.definitions.Entity;
import ee.ponceau.steel.definitions.PhysicalEntity;
import ee.ponceau.steel.definitions.PhysicsProxy;
import ee.ponceau.steel.util.Vector2D;
import ee.ponceau.steel.util.Vector3D;
import java.awt.Graphics2D;

/**
 *
 * @author Evan
 */
public class Person extends Entity implements PhysicalEntity{
    
  public Person(double x, double y, double radius) {
    position = new Vector3D(x, y, 0);
    dimension = new Vector2D(radius, radius);
  }

  @Override
  public void update(double delta) {
    super.update(delta);
  }

  @Override
  public void draw(GraphicsEngine engine, Graphics2D g) {
    g.drawOval(position.xi(), position.yi(),(int) dimension.xi(), (int) dimension.yi());
  }

  @Override
  public PhysicsProxy getSimulationProxy() {
    if(proxy == null) {
      proxy = new PhysicsProxy(this, Physics.DynamicDef(), 
              Physics.CircleFixture(dimension.xf()));
    }
    return proxy;
  }
  PhysicsProxy proxy;

  @Override
  public boolean isSimulating() {
    return isSimulating;
  }
  boolean isSimulating = true;
}
