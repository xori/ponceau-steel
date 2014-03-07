package ee.ponceau.steel.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Query Class.
 *  Cannot wait till Java 1.8
 * @author Evan
 */
public class Q <E> {
  Collection<E> collection;
  public Q(Collection<E> collection){
    this.collection = collection;
  }
  public Collection<E> Where(Predicate<E> p) {
    ArrayList<E> out = new ArrayList<>();
    for(E e : collection) {
      if(p.evaluate(e))
        out.add(e);
    }
    return out;
  }
  
  public <T> Collection<T> Select(TransformMap<E, T> map) {
    ArrayList<T> out = new ArrayList<>();
    for(E e : collection) {
      out.add(map.evaluate(e));
    }
    return out;
  }
          
  
  public interface Predicate<E> {
    public boolean evaluate(E item);
  }
  public interface TransformMap<A,B> {
    public B evaluate(A item);
  }
}
