package game.behaviour;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.Status;

import java.util.ArrayList;

public class AttackBehaviour implements Behaviour {
    /**
     *
     * @param actor the Actor
     * @param map the GameMap containing the Actor
     * @return actions
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        ArrayList<Action> actions = new ArrayList<>();
        // Check exits for otherActors
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            Actor otherActor = destination.getActor();
            if (otherActor != null && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                actions.add(new AttackAction(otherActor, exit.getName(), actor.getIntrinsicWeapon()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(0);
        }
        return null;

    }
}
