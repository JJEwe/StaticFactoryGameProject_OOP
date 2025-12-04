package game.ground;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellAction;
import game.Status;
import edu.monash.fit2099.engine.items.Item;
import game.items.transaction.sellable.Sellable;

/**
 * Represents a humanoid figure on the ground that allows the player to perform sell actions.
 * The humanoid figure can sell various items such as bolts, metal, large fruits, jars of pickles,
 * metal pipes, pots of gold, and toilet rolls.
 *
 * This class extends the Ground class, meaning it represents a type of ground that actors can interact with.
 *
 * @author Ewe Jun Jie
 * @since 27.05.2024
 */
public class HumanoidFigure extends Ground{
    public HumanoidFigure() {
        super('H');
    }

    /**
     * Produces the actions that the player can perform with the computer terminal.
     *
     * @param location the location of the ground
     * @return a list of actions that the actor can do with the computer terminal
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        // Add purchase actions for energy drink, dragon slayer sword replica, and toilet roll

        for (Item item : actor.getItemInventory()) {
            if (item.hasCapability(Status.SELLABLE)) {
                actions.add(new SellAction((Sellable) item));
            }
        }

        return actions;
    }
}


