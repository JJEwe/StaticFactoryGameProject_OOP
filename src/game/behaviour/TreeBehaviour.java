package game.behaviour;

import edu.monash.fit2099.engine.positions.Location;
/**
 * Interface representing a behavior that can be executed by a tree.
 *@author Maryam Vazeem
 *  @since 3.06.2024
 *  @version 1.0
 */
public interface TreeBehaviour {
    /**
     * Executes the behavior on the given location.
     *
     * @param location the location where the behavior is executed
     * @return true if the behavior was successfully executed, false otherwise
     */

    boolean execute(Location location);
}
