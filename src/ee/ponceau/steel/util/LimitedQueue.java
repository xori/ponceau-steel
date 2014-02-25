package ee.ponceau.steel.util;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Evan
 */
public class LimitedQueue<E> extends ConcurrentLinkedQueue<E> {
  private int limit;

  public LimitedQueue(int limit) {
    this.limit = limit;
  }

  @Override
  public boolean add(E o) {
    super.add(o);
    while (size() > limit) {
      super.remove();
    }
    return true;
  }

  /** 
   * Specifically for KeyInput 
   */
  private E lastElement = null;
  public boolean addIfDifferent(E o) {
    if(!o.equals(lastElement)){
      lastElement = o;
      return add(o);
    } else return false;
  }

  @Override
  public E remove() {
    lastElement = null;
    return super.remove();
  }

  @Override
  public boolean remove(Object o) {
    lastElement = null;
    return super.remove(o);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    lastElement = null;
    return super.remove(c);
  }
}
