package ee.ponceau.steel;

import ee.ponceau.steel.definitions.Entity;
import ee.ponceau.steel.util.Q;
import java.util.Collection;
import java.util.List;
import static ee.ponceau.steel.util.Log.*;
/**
 * 
 * @author Evan
 */
public class Physics {
  private Stage stage;
  
  public Physics(Stage stage) {
    this.stage = stage;
  } private Physics() {}
  
  public void update(double delta) {
    Collection<Entity> physicals = new Q(stage).Where(new Q.Predicate<Entity>() {
      public boolean evaluate(Entity item) {
        return item.body != null;
    }});
    Collection<Entity> dynamics = new Q(physicals).Where(new Q.Predicate<Entity>() {
      public boolean evaluate(Entity item) {
        return item.velocity.x != 0 || item.velocity.y != 0;
      }
    });
    
    for(Entity e : dynamics){   
      e.position.x += e.velocity.x * delta;
      for(Entity f : physicals) {
        if(e != f) {
          if(e.body.collides(f.body)){
            LOG("collide x");
            e.position.x -= e.velocity.x * delta; 
            e.velocity.x *= -0.5;
            break;
          }
        }
      }
      
      e.position.y += e.velocity.y * delta;
      for(Entity f : physicals) {
        if(e != f) {
          if(e.body.collides(f.body)){
            LOG("collide y");
            e.position.y -= e.velocity.y * delta; 
            e.velocity.y *= -0.5;
            break;
          }
        }
      }
    }
    
  }
}
