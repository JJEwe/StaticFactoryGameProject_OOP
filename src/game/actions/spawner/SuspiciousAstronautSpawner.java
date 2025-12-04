package game.actions.spawner;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.SuspiciousAstronaut;
import java.util.Random;
/** A class representing a SuspiciousAstronaut Spawner
 *
 * <p>
 *     This class defines a Spawner that spawns SuspiciousAstronaut from a crater.
 * </p>
 *
 * @author Ewe Jun Jie
 */
public class SuspiciousAstronautSpawner implements Spawner{
    private static final double SUSPICIOUS_ASTRONAUT_SPAWNING_CHANCES = 0.05;
    private Random random = new Random();

    /**
     * Spawns a Huntsman Spider actor at the given location.
     *
     * @param location The location where the Huntsman Spider will be added
     */
    @Override
    public void spawnActor(Location location) {
        // Ensure the location is empty before spawning
        if (!location.containsAnActor() && random.nextDouble() <= SUSPICIOUS_ASTRONAUT_SPAWNING_CHANCES) {
            location.addActor(new SuspiciousAstronaut());
        }
    }
}