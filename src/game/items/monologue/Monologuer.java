package game.items.monologue;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A class representing a Monologue interface.
 *
 *
 * Created by:
 * @author Alicia Tiopan
 * @since 27/05/2024
 */
public interface Monologuer {
    /**
     *
     * @param actor the actor "conversing" with the entity
     * @return a string describing the result of the monologue
     */
    String getMonologue(Actor actor);
}
