package ee.ponceau.steel.networking;

import ee.ponceau.steel.networking.ServiceAnnotation.Endpoint;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import static ee.ponceau.steel.util.Log.*;

/**
 *
 * @author Evan
 */
public class ServiceHandler {
  HashMap<String, APIEndpoint> api = new HashMap<>();
  
  public int addToApi(Object o) {
    int methodsAddedToAPI = 0;
    Class dclass = o.getClass();
    for(Method m : dclass.getMethods()) {
      Endpoint a = m.getAnnotation(ServiceAnnotation.Endpoint.class);
      if(a != null && !api.containsKey(a.ailias())) {
        api.put(a.ailias(), new APIEndpoint(o, m));
        methodsAddedToAPI++;
      }
    }
    return methodsAddedToAPI;
  }
  
  public APIEndpoint get(String ailias) {
    return api.get(ailias);
  }
  
  public static class APIEndpoint {
    public APIEndpoint(Object o, Method m) {
      base = o;
      method = m;
    }
    public Object base;
    public Method method;
    
    public Object run (Object ... arguments) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
      return method.invoke(base, arguments);
    }
    
    public Object $run (Object ... arguments) {
      Object o = null;
      try {
        o = run(arguments);
      } catch (Exception e) {
        WARNING(method.getName() + " failed to execute.", e.getMessage());
        o = null;
      }
      return o;
    }
  }
}
