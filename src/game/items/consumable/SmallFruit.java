package game.items.consumable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actions.ConsumeAction;

/**
 * A class representing a small fruit.
 */
public class SmallFruit extends Item implements Consumable {
    private final int HEAL_POINTS = 1;
    /**
     * Constructor.
     */
    public SmallFruit(){
        super("SmallFruit", 'o', true);
        this.addCapability(Status.CONSUMABLE);
    }

    /**
     * Determines the health points the item will provide to the player.
     *
     * @param actor which will be consuming the item
     */
    @Override
    public String consume(Actor actor){
        actor.removeItemFromInventory(this);
        actor.heal(1);
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

}
