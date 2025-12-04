package game.behaviour;




import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Behaviour;

/**
 * FollowBehaviour enables an Actor to follow a target Actor when the target is within one exit.
 * /**
 *  * FollowBehaviour is a behavior implementation that enables an actor to follow a target actor when the target is within one exit.
 *  * This behavior calculates the distance between the actor and the target, and if the target is reachable within one move, it returns a MoveActorAction
 *  * to move the actor towards the target.
 *  @author Maryam Vazeem
 *    @since 9.05.2024
 *   @version 1.0
 *
 */
public class FollowBehaviour implements Behaviour {
    /**
     * Constructor to create a FollowBehaviour instance.
     *
     * @param target the target actor to follow
     */
    private final Actor target;


    public Actor getTarget() {
        return target;
    }

    /**
     * Constructor to create a FollowBehaviour instance.
     *
     * @param target the Actor to follow
     *   @author Maryam Vazeem
     *   @since 9.05.2024
     *   @version 1.0
     */

    public FollowBehaviour(Actor target) {
        this.target = target;
    }
    /**
     * Returns an action for the actor to follow the target actor if the target is within one exit.
     *
     * @param actor the actor performing the behavior
     * @param map   the current game map
     * @return an Action to follow the target actor, or null if the target is not reachable within one exit
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = distance(here, there);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, there);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }

        return null;
    }

    /**
     * Calculates the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the second location
     * @return the Manhattan distance between the two locations
     */

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
