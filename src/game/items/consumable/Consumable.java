package game.items.consumable;

import edu.monash.fit2099.engine.actors.Actor;


/**
 * Interface representing items which are consumable in the game.
 *
 * This interface defines methods that must be implemented by consumable items.
 * Implementation of this interface should define how actor consumes the item and its resulting effects.
 *
 * Created by:
 * @author Alicia Tiopan
 * @since 30/04/2024
 *
 * Modified by:
 * @author Alicia Tiopan
 * @since 7/05/2024
 */
public interface Consumable{
    /**
     * Defines the behaviour it induces to actor when it consumes the item.
     * This method defines the effects the item has on the actor when consuming it.
     *
     * @param actor the actor consuming the item
     * @return a string describing the result of consuming the item
     */
    String consume(Actor actor);

    /**
     * Returns the string representation of the consumable item.
     *
     * @return the string representation of the consumable item
     */
    String toString();

}