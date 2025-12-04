package game.items.consumable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actions.ConsumeAction;
import game.items.transaction.purchasable.Purchasable;

import java.util.Random;

/**
 * A class representing an Energy Drink Consumable.
 *
 * <p>
 * This class represents an energy drink item that can be consumed by actors in the game.
 * </p>
 *
 * @author Muhammed Naveed Hassan
 * Modified by: Ewe Jun Jie
 */
public class EnergyDrink extends Item implements Consumable, Purchasable {
    private final int HEAL_POINTS = 1;
    private static Random random = new Random();
    private static int PRICE = 10;


    /**
     * Constructor.
     */
    public EnergyDrink() {
        super("Energy Drink", '*', true);
        this.addCapability(Status.CONSUMABLE);
    }

    /**
     * Gets the price for printing an energy drink, with a chance of doubling the price.
     *
     * @return the price for printing an energy drink
     */
    @Override
    public int getPrice() {
        if (random.nextDouble() <= 0.2) {
            PRICE *= 2;
        }
        return PRICE;
    }

    @Override
    public String purchase(Actor actor, int price) {
        actor.deductBalance(price);
        actor.addItemToInventory(this);
        return this + " purchased successfully. " + price + " credits debited from your account.";
    }

    /**
     * Determines heal points the item will provide for the player.
     *
     * @param actor which will be consuming the item
     * @return a message indicating the result of the consumption
     */
    @Override
    public String consume(Actor actor) {
        actor.removeItemFromInventory(this);
        actor.heal(HEAL_POINTS);
        return actor + " consumed the " + this + " and heals by " + HEAL_POINTS;
    }

    /**
     * Generates a list of allowable actions for the actor using this item.
     *
     * @param actor the actor using the item
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actions = super.allowableActions(actor);

        // Check if the actor has the energy drink in their inventory and is hostile to enemies
        if (actor.getItemInventory().contains(this) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new ConsumeAction(this)); // Heal player by 1 hit point when consumed
        }

        return actions;
    }
}
