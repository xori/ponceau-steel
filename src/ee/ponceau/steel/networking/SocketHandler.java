package ee.ponceau.steel.networking;

import java.net.DatagramSocket;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Evan
 */
public class SocketHandler extends Thread{
  ConcurrentLinkedQueue<String> q;
  
  public SocketHandler(ConcurrentLinkedQueue queue, DatagramSocket socket) {
    q = queue;
  }
  
  public void run(){
    
  }

}
