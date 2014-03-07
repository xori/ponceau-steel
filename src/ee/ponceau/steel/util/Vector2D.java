package ee.ponceau.steel.util;

/**
 *
 * @author Evan
 */
public class Vector2D {
  public double x = 0,y = 0;
  
  public Vector2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public Vector2D() {  }
  
  public int xi(){
    return (int) x;
  }
  public int yi(){
    return (int) y;
  }
  
  public Vector2D add(Vector2D v2) {
    return new Vector2D(x + v2.x, y + v2.y);
  }
  
  public Vector2D $add(Vector2D v2) {
    x += v2.x;    y += v2.y;
    return this;
  }
  
  public Vector2D scale(double d) {
    x *= d; y *= d;
    return this;
  }
  
  public Vector2D limit(double lower, double upper){
    x = (x < lower) ? lower : (x > upper) ? upper : x;
    y = (y < lower) ? lower : (y > upper) ? upper : y;
    return this;
  }
  
  public double distance(Vector2D v) {
    double _x = Math.abs(v.x - x);
    double _y = Math.abs(v.y - y);
    return Math.sqrt(_x * _x + _y * _y);
  }
  
  public String toString() {
    return xi() + ", " + yi();
  }
}
