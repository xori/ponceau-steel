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
  
  public Vector2D add(Vector2D v2) {
    return new Vector2D(x + v2.x, y + v2.y);
  }
  
  public Vector2D scale(double d) {
    return new Vector2D(x * d, y * d);
  }
  
  public Vector2D limit(double lower, double upper){
    x = x < lower ? lower : x > upper ? upper : x;
    y = y < lower ? lower : y > upper ? upper : y;
    return this;
  }
}
