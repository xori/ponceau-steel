package ee.ponceau.steel.definitions;

import ee.ponceau.steel.Physics;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;

/**
 *
 * @author Evan
 */
public class PhysicsProxy {
  public Entity proxy;
  public boolean allowPositionModification = false;
  
  public Body simulationBody;
  
  private PhysicsProxy() {}
  public PhysicsProxy(Entity e, BodyDef bodyDef, FixtureDef fixtureDef) {
    proxy = e;
    bodyDef.position.set(e.position.toVec());
    simulationBody = Physics.world.createBody(bodyDef);
    simulationBody.createFixture(fixtureDef);
  }
  
  public void update(double delta) {
    if(proxy.velocity.x != 0 || proxy.velocity.y != 0)
      simulationBody.applyForceToCenter(proxy.velocity.toVec());
    if(!allowPositionModification) {
      Vec2 pos = simulationBody.getPosition();
      proxy.position.x = pos.x;
      proxy.position.y = pos.y;
    } else {
      simulationBody.setTransform(proxy.position.toVec(), 0);
    }
  }
}
