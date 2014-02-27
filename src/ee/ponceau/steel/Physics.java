package ee.ponceau.steel;

import ee.ponceau.steel.definitions.Entity;
import ee.ponceau.steel.definitions.PhysicalEntity;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import static ee.ponceau.steel.util.Log.*;

/**
 * 
 * @author Evan
 */
public class Physics {
  public static World world;
  
  int velocityIterations = 60;
  int positionIterations = 20;
  
  public Physics() {
    // create World;
    world = new World(new Vec2(0,0));
  }
  
  // <editor-fold desc="Some quick definitions for physics simulations.">
  public static BodyDef StaticDef(){
    return new BodyDef() {{
      type = BodyType.STATIC;    
      position.set(0, 0);
    }};
  }
  public static BodyDef DynamicDef(){
    return new BodyDef() {{
      type = BodyType.DYNAMIC;    
      position.set(0, 0);
    }};
  }
  public static BodyDef KinematicDef() {
    return new BodyDef() {{
      type = BodyType.KINEMATIC;
      fixedRotation = true;
      position.set(0, 0);
    }};
  }
  public static FixtureDef CircleFixture(final float radius){
   return new FixtureDef() {{
     shape = new CircleShape(){{
       setRadius(radius);
     }};
     friction = 0;
     density = 0;
   }};
  }
   public static FixtureDef PolygonFixture(final float width, final float height){
   return new FixtureDef() {{
     shape = new PolygonShape(){{ 
       setAsBox(width, height);
     }};
     friction = 0;
     density = 0;
   }};
  }
// </editor-fold>
 
  public void update (double delta) {
    for(Entity e : Main.i.stage) {
      if(e instanceof PhysicalEntity && ((PhysicalEntity) e).isSimulating()){
        ((PhysicalEntity) e).getSimulationProxy().update(delta);
        LOG(((PhysicalEntity) e).getSimulationProxy().simulationBody.getPosition());
      }
    }
    world.step((float)delta, velocityIterations, positionIterations);
  }
}
