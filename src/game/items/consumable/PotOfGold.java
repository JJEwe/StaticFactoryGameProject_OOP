package game.items.consumable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actions.ConsumeAction;
import game.items.transaction.sellable.Sellable;
import java.util.Random;

/**
 * A class representing a pot of gold, which is a consumable.
 * A pot of gold will add the actors wallet balance.
 *
 * A pot of gold extends Item and implements Consumable.
 *
 * Created by:
 * @author Alicia Tiopan
 * @since 7/05/2024
 * Modified by: Ewe Jun Jie
 */
public class PotOfGold extends Item implements Consumable, Sellable {
    private final int ADD_BALANCE_AMOUNT = 10;
    private static int SELLING_PRICE = 500;
    private static Random random = new Random();

    /**
     * Constructor.
     * Intialise a pot of gold with its attributes.
     */
    public PotOfGold(){
        super("Pot of Gold", '$', true);
        this.addCapability(Status.SELLABLE);
    }

    /**
     * Adds the actor's wallet balance once action is executed.
     *
     * @param actor which will be consuming the item
     * @return a string describing the result of the action performed
     */
    @Override
    public String consume(Actor actor){
        actor.removeItemFromInventory(this);
        actor.addBalance(ADD_BALANCE_AMOUNT);
        return actor + " consumed the " + this + " and adds balance by " + ADD_BALANCE_AMOUNT +
                '\n' + actor + " current balance: " + actor.getBalance();
    }

    /**
     * Generates a list of allowable action for this consumable item.
     *
     * Checks for the item in actor's inventory and validate if it's a Player.
     * Adds ConsumeAction if its satisfies both conditions.
     *
     * @param actor the actor that performing the action
     * @return list of allowable actions on this consumable item
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actions = super.allowableActions(actor);

        // Check if the actor has the fruit in their inventory
        if (actor.getItemInventory().contains(this) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }
    @Override
    public int getSellingPrice() {
        if (random.nextDouble() <= 0.25) {
            SELLING_PRICE *= 0;
        }
        return SELLING_PRICE;
    }

    @Override
    public String sell(Actor actor, int sellingPrice) {



        actor.addBalance(sellingPrice);
        actor.removeItemFromInventory(this);
        return this + " broski successfully being finessed. " + sellingPrice + " credits added to your account.";

    }
}
