package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Display;
import game.behaviour.PickUpBehaviour;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import game.Status;
import java.util.*;

/**
 * AlienBug is a hostile creature that randomly picks up scraps from the ground,
 * prioritizing scrap collection over following an intern unless it is in the same location as the intern.
 * Each AlienBug has a unique name to identify it.
 */
/**
 * AlienBug represents a hostile creature within the game environment that can perform various actions like picking up items and following players.
 * This class extends {@link Hostile}, inheriting basic hostile attributes and methods, adding unique behaviors like item collection.
 *
 * @author Maryam Vazeem
 * @since 9.05.2024
 * @version 1.0
 */
public class AlienBug extends Hostile {

    /**
     * Constructor for AlienBug. It initializes the AlienBug with a unique name, basic stats, and the primary behavior of picking up items.
     */


    public AlienBug() {
        super(generateUniqueName(), 'a', 2, 0, "kicks", 25);

        this.behaviours.put(1, new PickUpBehaviour());


        this.addCapability(Status.HOSTILE_TO_PLAYER);
    }
    /**
     * Generates a unique name for each instance of AlienBug.
     * @return A unique name string formatted as "Feature-XXX" where XXX is a random number between 100 and 999.
     */

    private static String generateUniqueName() {
        Random random = new Random();
        return "Feature-" + (random.nextInt(900) + 100);  // Generates a number between 100 and 999
    }

    /**
     * Defines the behavior of the AlienBug in each turn.
     * The AlienBug will attempt to execute actions based on its current behaviors, such as picking up items or following another actor.
     *
     * @param actions The list of actions that can be performed this turn
     * @param lastAction The last action performed by this actor, if any
     * @param map The map the actor is currently on
     * @param display The interface to manage the game's display
     * @return The action to be executed this turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {




        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }


        return new DoNothingAction();
    }
    /**
     * Handles the dropping of all items in the inventory onto the ground at the actor's current location.
     * This method is typically called when the AlienBug becomes unconscious.
     *
     * @param actor The actor that causes the item drop
     * @param map The game map to determine the location of the drop
     */


    public void dropItem(Actor actor, GameMap map){
        for(Item i:this.getItemInventory()){
            map.locationOf(this).addItem(i);
        }

    }
    /**
     * Describes what happens when the AlienBug goes unconscious.
     * It calls {@link #dropItem(Actor, GameMap)} to drop all items and then uses the parent class's method to handle additional effects.
     *
     * @param actor The actor that caused the unconsciousness
     * @param map The game map where the event occurs
     * @return A string description of the unconsciousness event
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        dropItem(actor , map);
        return super.unconscious(actor , map);
    }



    /**
     * Determines the actions that can be performed on this AlienBug by another actor.
     * If the other actor is hostile to enemies, the AlienBug will dynamically add a follow behavior.
     *
     * @param otherActor The actor interacting with this AlienBug
     * @param direction The relative direction of the other actor to this one
     * @param map The game map for context
     * @return An {@link ActionList} containing allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        if ( otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) ) {
            addBehaviour(2,new game.behaviour.FollowBehaviour(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);
    }

}
