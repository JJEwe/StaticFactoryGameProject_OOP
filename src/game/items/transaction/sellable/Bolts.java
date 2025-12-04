package game.items.transaction.sellable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.items.transaction.sellable.Sellable;

/**
 * A class representing bolts, a type of item.
 * Modified by: Ewe Jun Jie
 * @since 27.05.2024
 */
public class Bolts extends Item implements Sellable {

    private static final int SELLING_PRICE = 25;


    /**
     * Constructor.
     */
    public Bolts() {
        super("Large Bolts", '+', true);
        this.addCapability(Status.SELLABLE);
    }

    @Override
    public int getSellingPrice() {
        return SELLING_PRICE;
    }

    @Override
    public String sell(Actor actor, int sellingPrice) {
        actor.addBalance(sellingPrice);
        actor.removeItemFromInventory(this);
        return this + " sold successfully. " + sellingPrice + " credits added to your account.";

    }
}
