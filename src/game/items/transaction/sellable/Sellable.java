package game.items.transaction.sellable;

import edu.monash.fit2099.engine.actors.Actor;
/**
 * An interface representing a sellable item in the game.
 *
 * Classes that implement this interface can be sold by actors, with a defined selling price.
 *
 *
 * @author Ewe Jun Jie
 * @since 27.05.2024
 */
public interface Sellable {

    /**
     * Handles the logic for selling the item.
     *
     * @param actor the actor selling the item
     * @return a message indicating the result of the selling
     */
    String sell(Actor actor, int sellingPrice);

    /**
     * Gets the selling price of the item.
     *
     * @return the selling price of the item
     */
    int getSellingPrice();
}
