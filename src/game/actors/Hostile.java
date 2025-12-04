package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviour.WanderBehaviour;
import game.Status;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Modified hash map to tree map
 * modified by Maryam Vazeem
 */
public abstract class Hostile extends Actor {

    private final int damage;
    private final String verb;
    private final int hr;
    Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * The constructor of the Hostile class.
     *
     * @param name        the name of the Hostile
     * @param displayChar the character that will represent the Hostile in the display
     * @param hitPoints   the Hostile's starting hit points
     * @param damage      the Hostile's starting damage
     * @param verb        the Hostile's Intrinsic Weapon verb
     */
    public Hostile(String name, char displayChar, int hitPoints, int damage, String verb, int hitRate) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        this.hr = hitRate;
        this.damage = damage;
        this.verb = verb;
        this.behaviours.put(999, new WanderBehaviour());
    }
    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(damage, verb, hr);
    }


    /**
     *
     * @param actions    collection of possible Actions for this Hostile
     * @param lastAction The Action this Hostile took last turn.
     * @param map        the map containing the Hostile
     * @param display    the display
     * @return           actions
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display){
        // Get the current location of the Hostile
        Location currentLocation = map.locationOf(this);

        // Get the list of exits from the current location
        List<Exit> exits = currentLocation.getExits();

        // Iterate over all exits
        for (Exit exit : exits) {
            // Get the destination location from the exit
            Location destination = exit.getDestination();

            // Check if the destination location contains an actor (other than the Huntsman Spider itself)
            if (destination.containsAnActor() && !destination.getActor().equals(this)) {
                Actor targetActor = destination.getActor();
                if (targetActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    // If an actor is found, attack it
                    return new AttackAction(destination.getActor(), exit.getName(), this.getIntrinsicWeapon());
                }
            }
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        // If no actor is found in any exit location, do nothing
        return new DoNothingAction();
    }


    /**
     * Retrieves the list of allowable actions for the hostile actor.
     *
     * @param otherActor the actor being interacted with
     * @param direction  the direction of interaction
     * @param map        the game map
     * @return the list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor != null && otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !otherActor.equals(this)) {
            actions.add(new AttackAction(this, direction, otherActor.getIntrinsicWeapon()));
        }
        return actions;
    }
//  void function that takes an integer as an parameter which is the key another is the behaviour
    public void addBehaviour(int key ,Behaviour behaviour){
        this.behaviours.put(key, behaviour);
    }
}


