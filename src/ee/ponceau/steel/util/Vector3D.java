package ee.ponceau.steel.util;

/**
 *
 * @author Evan
 */
public class Vector3D extends Vector2D{
  public double z = 0;
  
  public Vector3D(double x, double y, double z) {
    super(x, y);
    this.z = z;
  }
  
  public int zi() {
    return (int) z;
  }
  
  public String toString() {
    return x + " " + y + " " + z;
  }

  public Vector3D add(Vector2D v2) {
    return add(to3D(v2));
  }
  public Vector3D add(Vector3D v2) {
    return new Vector3D(x + v2.x, y + v2.y, z + v2.z);
  }
  
  public Vector3D scale(double d) {
    super.scale(d);
    z *= d;
    return this;
  }
  
  public static Vector3D to3D(Vector2D v){
    return new Vector3D(v.x, v.y, 0);
  }
  
  public Vector2D to2D(){
    return this;
  }

  @Override
  public Vector3D limit(double lower, double upper) {
    super.limit(lower, upper);
    z = (z < lower) ? lower : (z > upper) ? upper : z;
    return this;
  }
  
  public double distance(Vector2D v) {
    return distance(to3D(v));
  }
  public double distance(Vector3D v) {
    double _x = Math.abs(v.x - x);
    double _y = Math.abs(v.y - y);
    double _z = Math.abs(v.z - z);
    return Math.sqrt(_x * _x + _y * _y + _z * _z);
  }
}
