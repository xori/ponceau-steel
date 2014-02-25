package ee.ponceau.steel.scenes;

import ee.ponceau.steel.GraphicsEngine;
import ee.ponceau.steel.Stage;
import ee.ponceau.steel.controls.GenericControls;
import ee.ponceau.steel.definitions.Controller;
import ee.ponceau.steel.definitions.Entity;
import ee.ponceau.steel.definitions.Scene;
import ee.ponceau.steel.util.VirtualKeyboard;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

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
    this.player = new Entity(50, 50, 10, 10) {
      // normally this would be a separate class.
      @Override
      public void draw(GraphicsEngine engine, Graphics2D g) {
        g.setColor(Color.red);
        super.draw(engine, g);
      }
    };
    stage.add(player);
  }
  
  @Override
  public void onUpdate(double delta) {
    while(!myControls.keys.isEmpty()){
      VirtualKeyboard key = myControls.keys;
      if(key.isPressed(KeyEvent.VK_W))
        player.velocity.y -= delta * 2;
      if(key.isPressed(KeyEvent.VK_S))
        player.velocity.y += delta * 2;
      if(key.isPressed(KeyEvent.VK_A))
        player.velocity.x -= delta * 2;
      if(key.isPressed(KeyEvent.VK_D))
        player.velocity.x += delta * 2;
      player.velocity.limit(-5, 5);
    }
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
