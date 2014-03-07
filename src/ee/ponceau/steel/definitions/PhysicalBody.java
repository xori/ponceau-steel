package ee.ponceau.steel.definitions;

import ee.ponceau.steel.entities.Person;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Evan
 */
public class PhysicalBody implements CollisionFunction{
  ArrayList<Class> markAsCollidable = new ArrayList<>();
  public Types shapeType; // used for collision.
  public Entity proxy;
  public GeneralShapeCollider collider;
  
  public final static class AllItems { }
  public enum Types {
    POLYGON, CIRCLE, PIXEL
  }
  
  public PhysicalBody(Entity proxy, Types type, Class ... collidableItems) {
    this.proxy = proxy;
    collider = new GeneralShapeCollider(this);
    Collections.addAll(markAsCollidable, collidableItems);
    shapeType = type;
  }
  
  @Override
  public boolean collides(PhysicalBody body) {
    Class c = body.proxy.getClass();
    if(!markAsCollidable.contains(c)) return false;
    switch(body.shapeType) {
      case  CIRCLE: return collider.CircleCollision (body);
      case POLYGON: return collider.PolygonCollision(body);
      default:      return collider.OtherCollision  (body);
    }
  }
}
