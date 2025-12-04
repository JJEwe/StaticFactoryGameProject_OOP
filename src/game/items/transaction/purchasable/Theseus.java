package game.items.transaction.purchasable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.items.transaction.purchasable.Purchasable;

/**
 * Theseus class representing an item that can be purchased and used for teleportation.
 * Implements the Purchasable interface to encapsulate purchase-related methods.
 * Created by:
 * @author Muhammed Naveed Hassan
 */
public class Theseus extends Item implements Purchasable {
    /**
     * The price of the Theseus item.
     */
    private static final int PRICE = 100;

    /**
     * Constructor.
     * Initializes the Theseus item with a name, display character, and a capability
     * that allows teleportation.
     */
    public Theseus() {
        super("THESEUS", '^', true);
        this.addCapability(Status.TELEPORT);
    }

    /**
     * Returns the price of the Theseus item.
     *
     * @return the price of the item
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

    /**
     * Handles the purchase logic for the Theseus item.
     * Deducts the price from the actor's balance and adds the item to the actor's inventory.
     *
     * @param actor the actor purchasing the item
     * @param price the price of the item
     * @return a string indicating the success of the purchase
     */
    @Override
    public String purchase(Actor actor, int price) {
        actor.deductBalance(price);
        actor.addItemToInventory(this);
        return this + " purchased successfully. " + price + " credits debited from your account.";
    }
}
