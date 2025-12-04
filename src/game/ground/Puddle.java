package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeAction;
import game.items.consumable.Consumable;

/**
 *
 * A class representing a puddle on the ground, which is consumable, infinitely.
 * Puddles can increase the maximum health attribute of the actors who consumes it.
 *
 * This class extends Ground and implements the Consumable interface.
 *
 * Modified by:
 * @author Alicia Tiopan
 * @since 7/05/2024
 *
 */
public class Puddle extends Ground implements Consumable {
    private final int ADD_MAX_POINTS = 1;

    /**
     * Constructor.
     * Initialise Puddle with its appropriate symbol.
     */
    public Puddle() {
        super('~');
    }

    /**
     * Defines the effect it has on actor when consumed.
     * In this case, it increases the maximum helath attribute of the actor by 1.
     *
     * @param actor the actor consuming the item
     * @return a string representation to describe the action performed
     */
    @Override
    public String consume(Actor actor){
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, ADD_MAX_POINTS);
        return actor + " consumed the " + this.toString() + " and maximum health increased by "+ ADD_MAX_POINTS;
    }

    /**
     * Returns a string representation of this consumable.
     *
     * @return a string representation of the puddle
     */
    @Override
    public String toString(){
        return "puddle";
    }

    /**
     * Generates a list of allowable action for this Ground.
     *
     * Checks for an actor at a specific location and validate if it's a Player.
     * Adds ConsumeAction if its satisfies both conditions.
     *
     * @param actor the Actor performing the action
     * @param location the current Location where the puddle is
     * @param direction the direction of the Ground from the Actor
     * @return a list of allowable actions for the ground
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (location.containsAnActor() && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }
}
