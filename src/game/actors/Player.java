package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.FancyMessage;
import game.Status;
import game.actions.AttackAction;
import game.actions.TeleportAction;

import java.util.List;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Muhammed Naveed Hassan
 *
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addBalance(10000);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // Check if there's enemy in adjacent locations
        // Attacks if located.
        for (Exit exit : map.locationOf(this).getExits()) {
            Location adjacentLoc = exit.getDestination();
            if (adjacentLoc.containsAnActor() && adjacentLoc.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                return new AttackAction(adjacentLoc.getActor(), exit.getName(), this.getIntrinsicWeapon());
            }
        }

        Location currentLocation = map.locationOf(this);

        List<Item> itemsAtLocation = currentLocation.getItems();
        for (Item item : itemsAtLocation) {
            if (item.hasCapability(Status.TELEPORT)) {
                actions.add(new TeleportAction(item));
                break; // Exit the loop since we found THESEUS
            }
        }


        // Print Players Info
        System.out.println(this.details());


        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }


    /**
     * Determines if the Player is conscious.
     *
     * @param actor the actor
     * @param map the map where actor is acting on
     * @return string description when player is unconscious.
     */
    @Override
    public String unconscious(Actor actor, GameMap map){
        super.unconscious(actor, map);

        if (!isConscious()){
            return FancyMessage.YOU_ARE_FIRED;
        }
        return " ";
    }

    public String details() {
        return this.name + "\nHP: " + this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.HEALTH) + "\nBalance = " + this.getBalance();
    }

    /**
     * Retrieves the Player's Intrinsic Weapon.
     *
     * @return the intrinsic weapon the Player possesses
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(1,"punches",5);
    }
}

