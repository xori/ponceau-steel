package ee.ponceau.steel;

import ee.ponceau.steel.definitions.Controller;
import ee.ponceau.steel.definitions.Scene;
import ee.ponceau.steel.definitions.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  A single stage for managing multiple "scenes".
 *  Having a pause menu shouldn't mean hard-coding in the case. It should be as
 * simple as Stage.switchScene(new PauseMenu()) then after the player is done
 * Stage.switchScene(gameScene);
 *  I also wanted this separate from the GraphicsEngine because I don't think
 * it should be exclusively owned by that class.
 * 
 * //TODO create 'endScene' method to not save a scene.
 *    should also call the 'onDestroy()' method for the currentScene 
 *    before switching.
 * @author Evan
 */
public class Stage extends ArrayList<Entity>{
  public HashMap<Scene, ArrayList<Entity>> storedScenes = new HashMap<>();
  public Scene currentScene;
  
  public void switchScene(Scene newScene) {
    if(currentScene != null){
      currentScene.onPause();
      if(!storedScenes.containsKey(currentScene)){
        storedScenes.put(currentScene, new ArrayList<Entity>());
      }
      storedScenes.get(currentScene).addAll(this.subList(0, this.size()));
    }
    this.clear();
    if(storedScenes.containsKey(newScene)){
      this.addAll(storedScenes.get(newScene));
    }
    newScene.onLoad(this, Main.i.graphics);
    switchControls(newScene.getController());
    currentScene = newScene;
  }
  
  /**
   * Swap current input listeners with the given one.
   * If you call this function you are taking responsibility of the controls
   * of the given scene. The stage will no longer be able to remove the 
   * controls you give it.
   * @param newControls A new key/mouse binding.
   */
  public void switchControls(Controller newControls) {
    if(currentScene != null) {
      Controller c = currentScene.getController();
      Main.i.canvas.removeMouseListener(c);
      Main.i.canvas.removeMouseWheelListener(c);
      Main.i.canvas.removeKeyListener(c);
    }
    Main.i.canvas.addKeyListener(newControls);
    Main.i.canvas.addMouseWheelListener(newControls);
    Main.i.canvas.addMouseListener(newControls);
  }
  /**
   * This will delete the entity from the stage at a convenient time.
   * This is done because the physics or graphics engines are looping through
   * this list of items and they'll get an exception thrown if you just go and
   * remove an item.
   * @param e 
   */
  public void markForDelete(Entity e) {
    deleteSoon.add(e);
  }
  private List<Entity> deleteSoon = new ArrayList<>();
  public void purgeMarked() {
    this.removeAll(deleteSoon);
  }
}
