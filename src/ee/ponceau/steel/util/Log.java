package ee.ponceau.steel.util;

/**
 *
 * @author Evan
 */
public class Log {
  public static enum Level {
    LOG, WARNING, ERROR, FATAL;
  }
  public static Level STATE = Level.FATAL;
  private static final String SEPARATOR = ", ";
  
  public static void LOG(Object ... o) {
    if(STATE.ordinal() > Level.LOG.ordinal()) return;
    for(int i = 0; i < o.length; i++){
      if(i != 0)
         System.out.print(SEPARATOR);
      System.out.print(o[i]);
    }
    System.out.println();
  }
  
  public static void WARNING(Object ... o) {
    if(STATE.ordinal() > Level.WARNING.ordinal()) return;
    LOG(o);
  }
  
  public static void ERROR(Object ... o) {
    if(STATE.ordinal() > Level.ERROR.ordinal()) return;
    LOG(o);
  }
  
  public static void FATAL(Object ... o) {
    for(int i = 0; i < o.length; i++){
      if(i != 0)
         System.err.print(SEPARATOR);
      System.err.print(o[i]);
    }
    System.out.println();
  }
  
  public static boolean isDebug() {
    return STATE == Level.LOG;
  }
}
