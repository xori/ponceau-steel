package ee.ponceau.steel.scenes;

import ee.ponceau.steel.GraphicsEngine;
import ee.ponceau.steel.Stage;
import ee.ponceau.steel.controls.GenericControls;
import ee.ponceau.steel.definitions.Controller;
import ee.ponceau.steel.definitions.Entity;
import ee.ponceau.steel.definitions.Scene;
import ee.ponceau.steel.entities.Person;
import ee.ponceau.steel.util.VirtualKeyboard;
import java.awt.event.KeyEvent;
import static ee.ponceau.steel.util.Log.*;

/**
 *
 * @author Evan
 */
public class MainMenu implements Scene{
  Stage stage;
  GraphicsEngine ge;
  public Entity player;
  
  @Override
  public void onLoad(Stage stage, GraphicsEngine g) {
    this.stage = stage;
    this.ge = g;
    this.player = new Person(50,50,50);
    stage.add(player);
    //TODO We need to define a map loading lib.
    // maybe a Tiled library already exists?
    stage.add(new Entity(200, 0, 10, 400) {});
    stage.add(new Entity(0, 200, 200, 10) {});
    stage.add(new Entity(200, 200, 10, 200) {});
    stage.add(new Entity(200, 200, 10, 200) {});
  }
  
  @Override
  public void onUpdate(double delta) {
    if(!myControls.keys.isEmpty()){
      VirtualKeyboard key = myControls.keys;
      if(key.isPressed(KeyEvent.VK_W))
        player.velocity.y -= delta * 3;
      if(key.isPressed(KeyEvent.VK_S))
        player.velocity.y += delta * 3;
      if(key.isPressed(KeyEvent.VK_A))
        player.velocity.x -= delta * 3;
      if(key.isPressed(KeyEvent.VK_D))
        player.velocity.x += delta * 3;
    }
    player.velocity.limit(-10, 10);
    // TODO velocity not right.
    LOG(player.velocity.x, player.velocity.y);
    ge.camera.pointAt(player);
  }

  @Override
  public void onDestroy() {
    // clean up loaded resources.
  }

  @Override
  public void onPause() {
    onDestroy();
  }

  @Override
  public Controller getController() {
    // this must return the SAME instance each time.
    return myControls;
  }
  private Controller myControls = new GenericControls();
}
