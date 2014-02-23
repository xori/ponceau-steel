package ee.ponceau.steel.util;

/**
 *
 * @author Evan
 */
public class Vector2D {
  public double x,y;
  
  public Vector2D(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  public int xi(){
    return (int) x;
  }
  public int yi(){
    return (int) y;
  }
}
