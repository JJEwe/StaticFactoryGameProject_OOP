
package game.actions.spawner;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.HuntsmanSpider;
import java.util.Random;

/** A class representing a HuntsmanSpider Spawner
 *
 * <p>
 *     This class defines a Spawner that spawns HuntsmanSpider from a crater.
 * </p>
 *
 * @author Ewe Jun Jie
 */
public class HuntsmanSpiderSpawner implements Spawner{
    private static final double HUNTSMAN_SPIDER_SPAWNING_CHANCES = 0.05;
    private Random random = new Random();

    /**
     * Spawns a Huntsman Spider actor at the given location.
     *
     * @param location The location where the Huntsman Spider will be added
     */
    @Override
    public void spawnActor(Location location) {
        // Ensure the location is empty before spawning
        if (!location.containsAnActor() && random.nextDouble() <= HUNTSMAN_SPIDER_SPAWNING_CHANCES) {
            location.addActor(new HuntsmanSpider());
        }
    }
}