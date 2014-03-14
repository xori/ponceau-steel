package ee.ponceau.steel.networking;

import ee.ponceau.steel.Main;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fourthline.cling.UpnpService;
import org.fourthline.cling.UpnpServiceImpl;
import org.fourthline.cling.registry.RegistryListener;
import org.fourthline.cling.support.igd.PortMappingListener;
import org.fourthline.cling.support.model.PortMapping;
import static ee.ponceau.steel.util.Log.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.SocketTimeoutException;

/**
 *
 * @author Evan
 */
public class NAT implements Runnable{
  public static UpnpService service;
  public static PortMapping portmap;
  
  public final static NAT thread = new NAT();
  private ConcurrentLinkedQueue<String> incoming = new ConcurrentLinkedQueue<>();
  
  public static void PunchThrough() {
    
    portmap = new PortMapping(17000, 
            "0.0.0.0", 
            PortMapping.Protocol.UDP, 
            "ponceau-steel");
    
    service = new UpnpServiceImpl(new PortMappingListener(portmap));
    service.getControlPoint().search(); 
    
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run () {
        service.shutdown();
      }
    });
  }
  
  @Override
  public void run() {
    DatagramSocket socket = null;
    DatagramPacket packet = null;
    try {
      socket = new DatagramSocket(17000);
      socket.setSoTimeout(1000);
    } catch (SocketException ex) {
      FATAL(ex.getMessage());
      return;
    }
    
    while(Main.GAME_RUNNING) {
      try {
        packet = null;
        socket.receive(packet);
      } catch (SocketTimeoutException se) {
      } catch (IOException ex) {
        WARNING(ex.getMessage());
      }
      
      if(packet != null) {
        incoming.add(packet.getData().toString());
      }
    }
  }
}
