package game.items.transaction.purchasable;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface representing a purchasable item.
 *
 * @author Muhammed Naveed Hassan
 */
public interface Purchasable {

    /**
     * Handles the logic for purchasing the item.
     *
     * @param actor the actor purchasing the item
     * @return a message indicating the result of the purchase
     */
    String purchase(Actor actor, int price);

    /**
     * Gets the price of the item.
     *
     * @return the price of the item
     */
    int getPrice();
}
