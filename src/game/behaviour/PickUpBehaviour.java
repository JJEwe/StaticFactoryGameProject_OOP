package game.behaviour;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors .Behaviour;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
/**
 * A behavior that enables an actor to pick up the first available item from the ground at its current location.
 * This behavior scans the current location for items and allows the actor to pick up the first item found.
 * @author Maryam Vazeem
 *   @since 9.05.2024
 * @version 1.0
 */
public class PickUpBehaviour implements Behaviour {
    /**
     * Determines an action for an actor to pick up an item if available at the actor's current location on the map.
     *
     * @param actor the actor executing the pick-up
     * @param map the game map where the actor and items are located
     * @return an Action to pick up an item, or null if no items are available to pick up
     */

    @Override
    public Action getAction (Actor actor , GameMap map ){
        Location currentLocation = map.locationOf(actor);
        if (!currentLocation.getItems().isEmpty()){
            for (Item item: currentLocation.getItems()){
                return new PickUpAction(item);
            }
        }
        return null;
    }

}
