package ee.ponceau.steel.util;

/**
 *
 * @author Evan
 */
public class Vector3D extends Vector2D{
  public double z;
  
  public Vector3D(double x, double y, double z) {
    super(x, y);
    this.z = z;
  }
  
  public int zi() {
    return (int) z;
  }
  
  public String toString() {
    return xi() + " " + yi() + " " + zi();
  }

  public Vector3D add(Vector2D v2) {
    return add(to3D(v2));
  }
  public Vector3D add(Vector3D v2) {
    return new Vector3D(x + v2.x, y + v2.y, z + v2.z);
  }
  
  public Vector3D scale(Vector2D v) {
    return new Vector3D(x*v.x, y*v.y, z);
  }
  public Vector3D scale(Vector3D v) {
    return new Vector3D(x*v.x, y*v.y, z*v.z);
  }
  
  public Vector3D to3D(Vector2D v){
    return new Vector3D(v.x, v.y, 0);
  }

  @Override
  public Vector3D limit(double lower, double upper) {
    super.limit(lower, upper);
    z = z < lower ? lower : z > upper ? upper : z;
    return this;
  }
}
