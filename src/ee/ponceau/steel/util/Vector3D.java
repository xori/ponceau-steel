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
}
