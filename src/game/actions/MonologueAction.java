package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.monologue.Monologuer;

/**
 * A class representing an action where Player can listen to a game entity.
 *
 * Created by:
 * @author Alicia Tiopan
 * @since 27/05/2024
 */
public class MonologueAction extends Action {
    private Monologuer monologuer;

    /**
     * Constructor.
     *
     * @param monologuer the entity to listen to
     */
    public MonologueAction(Monologuer monologuer) {
        this.monologuer = monologuer;
    }

    /**
     * Allows actor to execute action on the game map.
     *
     * @param actor the actor the action is performed on.
     * @param map the map the actor is on.
     * @return the string of words that Actor can listen to
     */
    @Override
    public String execute(Actor actor, GameMap map){
        return monologuer.getMonologue(actor);
    }

    /**
     *
     * @param actor the actor the action is performed on.
     * @return string representation of action on menu
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " listens to " + monologuer + ", an AI Device.";
    }
}
