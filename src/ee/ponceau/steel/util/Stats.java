package ee.ponceau.steel.util;

/**
 *
 * @author Evan
 */
public class Stats {
  private long last = System.currentTimeMillis();
  private long time = System.currentTimeMillis();
  public double average, max = 0;
  
  public void tick() {
    time = System.currentTimeMillis();
    double thistick = time - last;
    if(max < thistick) {
      max = thistick;
    }
    average = (thistick + average) / 2;
    max *= 0.99;
    last = time;
  }
}
