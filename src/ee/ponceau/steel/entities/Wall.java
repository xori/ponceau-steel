package ee.ponceau.steel.entities;

import ee.ponceau.steel.Physics;
import ee.ponceau.steel.definitions.Entity;
import ee.ponceau.steel.definitions.PhysicalEntity;
import ee.ponceau.steel.definitions.PhysicsProxy;

/**
 *
 * @author Evan
 */
public class Wall extends Entity implements PhysicalEntity{
  public PhysicsProxy proxy;
  @Override
  public PhysicsProxy getSimulationProxy() {
    if(proxy == null ){
      proxy = new PhysicsProxy(this, Physics.StaticDef(), 
              Physics.PolygonFixture(dimension.xf(), dimension.yf()));
    }
    return proxy;
  }

  @Override
  public boolean isSimulating() {
    return isSimulating;
  }
  public boolean isSimulating = true;

}
