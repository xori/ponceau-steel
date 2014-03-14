package ee.ponceau.steel;

import ee.ponceau.steel.networking.ServiceHandler;
import ee.ponceau.steel.networking.ServiceAnnotation.Endpoint;
import static ee.ponceau.steel.util.Log.*;

/**
 *
 * @author Evan
 */
public class TestRunner {
  public static void main (String [] args) {
    ServiceHandler handler = new ServiceHandler();
    OUT(handler.addToApi(new TestRunner()), " methods found.");
    OUT(handler.get("random").$run());
  }
  
  @Endpoint(ailias="random")
  public int random() {
    return 5;
  }
}
