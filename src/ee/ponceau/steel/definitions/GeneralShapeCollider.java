package ee.ponceau.steel.definitions;

import ee.ponceau.steel.util.Vector2D;

/**
 *
 * @author Evan
 */
public class GeneralShapeCollider {
  private PhysicalBody myself;
  
  public GeneralShapeCollider(PhysicalBody body) {
    myself = body;
  }
  
  public boolean CircleCollision(PhysicalBody circularbody) {
    if(myself.shapeType == PhysicalBody.Types.CIRCLE) {
      return circularbody.proxy.position.distance(myself.proxy.position)
              < myself.proxy.dimension.x / 2.0 + circularbody.proxy.dimension.x / 2.0;
    } else if (myself.shapeType == PhysicalBody.Types.POLYGON) {
      // special Case.
      return PolygonCircleCollision(circularbody.proxy, myself.proxy);
    } else {
      return false;
    }
  }
  
  public boolean PolygonCollision(PhysicalBody polybody) {
    if(myself.shapeType == PhysicalBody.Types.CIRCLE) {
     // special case.
      return PolygonCircleCollision(myself.proxy, polybody.proxy);
    } else if (myself.shapeType == PhysicalBody.Types.POLYGON) {
       return myself.proxy.position.x > polybody.proxy.position.x + polybody.proxy.dimension.x ? false :
            myself.proxy.position.y > polybody.proxy.position.y + polybody.proxy.dimension.y ? false :
            myself.proxy.position.x + myself.proxy.dimension.x < polybody.proxy.position.x ? false :
            myself.proxy.position.y + myself.proxy.dimension.y < polybody.proxy.position.y ? false : true;
    } else {
      return false;
    }
  }
  
  // Credit, where credit is due.
  // http://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection
  public boolean PolygonCircleCollision(Entity circle, Entity rect) {
    double cx = circle.position.x + circle.dimension.x, 
            cy =  circle.position.y + circle.dimension.y;
    double left = rect.position.x, right = rect.position.x + rect.dimension.x, 
        top = rect.position.y, bottom = rect.position.y + rect.dimension.y;   
    
    double closestX = (cx < left ? left : (cx > right ? right : cx));
    double closestY = (cy < top ? top : (cy > bottom ? bottom : cy));
    double dx = closestX - cx;
    double dy = closestY - cy;

    return ( dx * dx + dy * dy ) <= circle.dimension.y * circle.dimension.y;
  }
  
  public boolean OtherCollision(PhysicalBody body) {
    return false;
  }
}
