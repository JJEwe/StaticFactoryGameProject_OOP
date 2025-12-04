package game.items.transaction.sellable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Status;
import game.actions.AttackAction;
import game.items.transaction.sellable.Sellable;


/**
 * A class representing a special item, metal pipe, which can be used as a weapon.
 * Modified by: Ewe Jun Jie
 * @since 27.05.2024
 */
public class MetalPipe extends WeaponItem implements Sellable {
    /**
     * Constructor determining its attributes.
     */
    public MetalPipe() {
        super("MetalPipe", '!',1,"hits", 20);
        this.addCapability(Status.SELLABLE);
    }
        private static final int SELLING_PRICE = 35;
    /**
     * Determines a list of actions that can be performed using this item.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return actions that can be performed with this item
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if (otherActor != null && location.getActor() == otherActor) {
            actions.add(new AttackAction(otherActor,location.toString(), this));
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
