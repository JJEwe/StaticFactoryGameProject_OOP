package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.items.transaction.purchasable.Purchasable;

import java.util.Random;

/**
 * A class representing a Dragon Slayer Sword Replica.
 *
 * <p>
 * This class represents a weapon item that can be used by actors in the game.
 * </p>
 *
 * @author Muhammed Naveed Hassan
 */
public class DragonSlayerSwordReplica extends WeaponItem implements Purchasable {
    private static final int DAMAGE = 50;
    private static final int PRICE = 100;

    private static final String VERB = "swings";
    private static final int CHANCE_TO_HIT = 75; // 75% accuracy

    private static Random random = new Random();


    /**
     * Constructor.
     */
    public DragonSlayerSwordReplica() {
        super("Dragon Slayer Sword Replica", 'x', DAMAGE, VERB, CHANCE_TO_HIT);
    }

    /**
     * Generates a list of allowable actions for the actor using this weapon.
     *
     * @param otherActor the actor being attacked
     * @param location the location where the attack is taking place
     * @return a list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if (otherActor != null && location.getActor() == otherActor) {
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }
        return actions;
    }

    /**
     * Gets the price for the Dragon Slayer Sword Replica.
     *
     * @return the price of the Dragon Slayer Sword Replica
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public String purchase(Actor actor, int price) {
        if (random.nextDouble() < 0.5) {
            actor.deductBalance(price);
            return "Oops, Purchase failed. " + price + " debited from your account.";
        } else {
            actor.deductBalance(price);
            actor.addItemToInventory(this);
            return this + " purchased successfully. " + price + " credits debited from your account.";
        }
    }
}
