package game.items.transaction.sellable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.items.transaction.sellable.Sellable;

import java.util.Random;

/**
 * A class representing Metal, a type of item.
 * Modified by: Ewe Jun Jie
 * @since 27.05.2024
 */
public class Metal extends Item implements Sellable {
    private static int SELLING_PRICE = 20;
    private static Random random = new Random();

    /**
     * Constructor.
     */
    public Metal() {
        super("Metal Sheets", '%', true);
        this.addCapability(Status.SELLABLE);
    }

    @Override
    public int getSellingPrice() {
        if (random.nextDouble() <= 0.60) {
            SELLING_PRICE = 10;
        }
        return SELLING_PRICE;
    }

    @Override
    public String sell(Actor actor, int sellingPrice) {

        actor.addBalance(sellingPrice);
        actor.removeItemFromInventory(this);
        return this + " sold successfully. " + sellingPrice + " credits added to your account.";

    }
}

