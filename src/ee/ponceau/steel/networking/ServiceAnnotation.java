package ee.ponceau.steel.networking;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Evan
 */
public class ServiceAnnotation {
  @Retention(RetentionPolicy.RUNTIME)
  @Target(ElementType.METHOD)
  public @interface SubscribableEndpoint {
    
  }
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Endpoint {
    public String ailias();
  }
}
