package ee.ponceau.steel.entities;

import ee.ponceau.steel.definitions.Entity;
import ee.ponceau.steel.definitions.PhysicalBody;
import static ee.ponceau.steel.definitions.PhysicalBody.Types.POLYGON;
/**
 *
 * @author Evan
 */
public class Wall extends Entity{
  public Wall() {
    this.body = new PhysicalBody(this, POLYGON, Person.class);
  }
  public Wall(double x, double y, double w, double h) {
    this.body = new PhysicalBody(this, POLYGON, Person.class);
    position.x = x;
    position.y = y;
    dimension.x = w;
    dimension.y = h;
  }
}
