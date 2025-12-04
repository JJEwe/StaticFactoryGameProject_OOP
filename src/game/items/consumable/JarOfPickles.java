package game.items.consumable;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actions.ConsumeAction;
import game.items.transaction.sellable.Sellable;
import java.util.Random;

/**
 * A class representing a jar of pickles, which is a consumable.
 * A jar of pickles can either hurt or heal the actor consuming it.
 *
 * A jar of pickles extends Item and implements Consumable.
 *
 * Created by:
 * @author Alicia Tiopan
 * @since 7/05/2024
 * Modified by: Ewe Jun Jie
 *
 */
public class JarOfPickles extends Item implements Consumable, Sellable {
    private final int HEAL_POINTS = 1;
    private final int HURT_POINTS = 1;
    private static int SELLING_PRICE = 25;
    private static Random random = new Random();

    /**
     * Constructor.
     * Iawdasdwasdwasntialise a jar of pickles with its attributes.
     */
    public JarOfPickles(){
        super("Jar of Pickles", 'n', true);
        this.addCapability(Status.SELLABLE);
    }

    /**
     * Determines the health points the item will provide/reduce to/from the player.
     * Heals or hurt the actor based on a randomly selected boolean value.
     *
     * @param actor which will be consuming the item
     * @return a string describing the result of the action performed
     */
    @Override
    public String consume(Actor actor){
        Random random = new Random();
        boolean randBool = random.nextBoolean();

        actor.removeItemFromInventory(this);
        if(randBool == true){
            actor.heal(HEAL_POINTS);
            return actor + " consumed the " + this + " and heals by " + HEAL_POINTS;
        } else{
            actor.hurt(HURT_POINTS);
            return actor + " consumed the " + this + " and hurt by " + HURT_POINTS;
        }
    }

    /**
     * Generates a list of allowable action for this consumable item.
     *
     * Checks for the item in actor's inventory and validate if it's a Player.
     * Adds ConsumeAction if its satisfies both conditions.
     *
     * @param actor the actor that performing the action
     * @return list of allowable actions on this consumable item
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actions = super.allowableActions(actor);
        if (actor.getItemInventory().contains(this) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }
    @Override
    public int getSellingPrice() {
        if (random.nextDouble() <= 0.50) {
            SELLING_PRICE *= 2;
        }
        return SELLING_PRICE;
    }

    @Override
    public String sell(Actor actor, int sellingPrice) {



        actor.addBalance(sellingPrice);
        actor.removeItemFromInventory(this);
        return this + " sold successfully. " + sellingPrice + " credits added to your account.";

    }
}