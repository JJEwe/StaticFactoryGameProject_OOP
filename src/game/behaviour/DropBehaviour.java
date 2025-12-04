package game.behaviour;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.items.Item;

import java.util.List;
import java.util.Random;
/**
 * Class representing the drop behavior for a tree.
 * This behavior allows a tree to drop a fruit at a random adjacent location with a certain probability.
 * @author Maryam Vazeem
  * @since 3.06.2024
 * @version 1.0
 */
public class DropBehaviour implements TreeBehaviour {
    private Random random = new Random();
    private Item fruit;
    private double dropChance;
    /**
     * Constructor for DropBehaviour.
     *
     * @param fruit      the fruit item to be dropped
     * @param dropChance the probability of the fruit being dropped
     */
    public DropBehaviour(Item fruit, double dropChance) {
        this.fruit = fruit;
        this.dropChance = dropChance;
    }
    /**
     * Executes the drop behavior, potentially dropping a fruit at a random adjacent location.
     *
     * @param location the location where the behavior is executed
     * @return false as this behavior does not change the tree's state
     */
    @Override
    public boolean execute(Location location) {
        List<Exit> exits = location.getExits();

        if (exits.isEmpty()) {
            return false;
        }
        // Randomly select 1 exit to drop the fruit on
        Exit selectedExit = exits.get(random.nextInt(exits.size()));


        // Ensuring that the fruit drop is by determined chance
        if (random.nextDouble() <= dropChance){
            // Get the location where the fruit is going to be placed on
            Location surrounding = selectedExit.getDestination();
            // Add item to said location
            surrounding.addItem(spawnFruit());
        }
        return false ;
    }
    /**
     * Spawns a fruit item.
     *
     * @return the fruit item to be dropped
     */

    protected Item spawnFruit() {
        return fruit;
    }
}

