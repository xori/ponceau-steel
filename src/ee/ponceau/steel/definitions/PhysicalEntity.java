package ee.ponceau.steel.definitions;

/**
 *
 * @author Evan
 */
public interface PhysicalEntity {
  public PhysicsProxy getSimulationProxy();
  public boolean isSimulating();
}
