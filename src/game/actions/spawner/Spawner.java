package game.actions.spawner;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Class representing a Spawner to add an actor to a location.
 * <p>
 *     This class defines a Spawner that adds defined actor to the location specified.
 * </p>
 *
 * @author Ewe Jun Jie
 */

public interface Spawner {
    /**
     * Spawns the specified actor at the given location.
     *
     * @param location the location where the actor will be added on
     */
    void spawnActor(Location location);
}