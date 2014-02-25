package ee.ponceau.steel.util;

/**
 *
 * @author Evan
 */
public class Cooldown {
  public int value;
  public int init_value;
  
  public Cooldown(int cooldown){
    init_value = value = cooldown;
  }
  public boolean tick(double delta) {
    value -= delta;
    return value <= 0;
  }
  public void reset() {
    value = init_value;
  }
}
