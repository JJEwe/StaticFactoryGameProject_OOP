package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

/**
 * An action that allows the player to teleport to a random location within the current map using Theseus.
 *
 * <p>
 * This class represents the action of teleporting to a random location within the current map using Theseus.
 * </p>
 * Created by:
 * @author Muhammed Naveed Hassan
 */
public class TeleportAction extends Action {
    private static final Random random = new Random();
    private final Item item;

    /**
     * Constructor.
     *
     * @param item the Item calling the Action
     */
    public TeleportAction(Item item) {
        this.item = item;
    }

    /**
     * Executes the teleport action.
     *
     * <p>
     * This method handles the process of randomly selecting a valid teleport location and moving the actor to that location.
     * </p>
     *
     * @param actor the actor performing the action
     * @param map the current game map
     * @return a message indicating the result of the teleport action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location randomLocation = getRandomLocationInMap(map);

        if (randomLocation.containsAnActor()){
            return "Teleport failed";
        }
        // Move the actor to the destination location
        map.moveActor(actor, randomLocation);


        return actor + " teleports to a random location.";
    }

    /**
     * Returns a description of the teleport action for display in the game menu.
     *
     * @param actor the actor performing the action
     * @return a description of the teleport action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Teleport to random location with " + item;
    }

    /**
     * Gets a random location within the specified map.
     *
     * @param map the map
     * @return a random location within the map
     */
    private Location getRandomLocationInMap(GameMap map) {
        int x = random.nextInt(map.getXRange().max() + 1);
        int y = random.nextInt(map.getYRange().max() + 1);
        return map.at(x, y);
    }
}
