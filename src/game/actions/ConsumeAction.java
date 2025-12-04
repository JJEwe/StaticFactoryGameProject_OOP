package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.consumable.Consumable;

/**
 * A class representing an action where Player consumes a special item.
 * Actor needs to be able to CONSUME to perform this action.
 *
 * Created by:
 * @author Alicia Tiopan
 * @since 30/04/2024
 *
 * Modified by:
 * @author Alicia Tiopan
 * @since 7/05/2024
 *
 */
public class ConsumeAction extends Action{
    private Consumable fruit;


    /**
     * Constructor.
     *
     * @param fruit the consumable (fruit) to be consumed
     */
    public ConsumeAction(Consumable fruit){
        this.fruit = fruit;
    }

    /**
     * Allows Actor to execute the action on the game map.
     * Once action is completed, item is removed from actor's inventory
     *
     * @param actor the actor performing the action
     * @param map   the game map on which the action is performed on
     * @return a string description of the action result
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return fruit.consume(actor);
    }

    /**
     * Description of the action displayed on the game menu
     *
     * @param actor the actor performing the action
     * @return a string describing the action for display
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + fruit.toString();
    }
}
