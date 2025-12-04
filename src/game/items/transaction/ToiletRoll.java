package game.items.transaction;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.items.transaction.purchasable.Purchasable;
import game.items.transaction.sellable.Sellable;

import java.util.Random;

/**
 * A class representing Toilet Roll, a type of item.
 *
 * @author Muhammed Naveed Hassan
 *
 * Modified by: Ewe Jun Jie
 *
 */
public class ToiletRoll extends Item implements Purchasable, Sellable {

    private static Random random = new Random();
    private static int PRICE = 5;
    private static int SELLING_PRICE = 1;



    /**
     * Constructor.
     */
    public ToiletRoll() {
        super("Toilet Roll", 's', true);
        this.addCapability(Status.SELLABLE);
    }

    /**
     * Gets the price for printing a toilet paper roll, with a chance of discount.
     *
     * @return the price for printing a toilet paper roll
     */
    @Override
    public int getPrice() {
        if (random.nextDouble() <= 0.75) {
            PRICE = 1;
        }
        return PRICE;
    }

    @Override
    public String purchase(Actor actor, int price) {
        actor.deductBalance(price);
        actor.addItemToInventory(this);
        return this + " purchased successfully. " + price + " credits debited from your account.";
    }

    @Override
    public int getSellingPrice() {

        return SELLING_PRICE;
    }

    @Override
    public String sell(Actor actor, int sellingPrice) {

        if (random.nextDouble() <= 0.5) {
            actor.hurt(Integer.MAX_VALUE);
            return this + " bro got killed over a toilet paper, L rizz " + sellingPrice + " credits added to your account.";
        }else{
            return this + " sold successfully" + sellingPrice + " credits added to your account.";
        }


    }
}