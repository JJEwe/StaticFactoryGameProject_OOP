package game.items.consumable;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actions.ConsumeAction;
import game.items.transaction.sellable.Sellable;


/**
 * A class representing a large fruit
 * Modified by: Ewe Jun Jie
 * @since 27.05.2024
 */
public class LargeFruit extends Item implements Consumable, Sellable {
    private final int HEAL_POINTS = 2;
    private static final int SELLING_PRICE = 30;

    /**
     * Constructor.
     */
    public LargeFruit(){
        super("LargeFruit", 'O', true);
        this.addCapability(Status.CONSUMABLE);
        this.addCapability(Status.SELLABLE);
    }

    /**
     * Determines heal points the item will provide for the palyer.
     *
     * @param actor which will be consuming the item
     */
    @Override
    public String consume(Actor actor){
        actor.removeItemFromInventory(this);
        actor.heal(2);
        return actor + " consumed the " + this + " and heals by " + HEAL_POINTS;
    }

    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actions = super.allowableActions(actor);

        // Check if the actor has the fruit in their inventory
        if (actor.getItemInventory().contains(this) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new ConsumeAction(this)); // Heal player by 1 hit point when consumed
        }

        return actions;
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
