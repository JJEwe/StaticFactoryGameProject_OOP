package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.transaction.sellable.Sellable;
import java.util.Random;
/**
 * An action for selling an item in the game.
 *
 * This action allows an actor to sell a specified item and receive credits in return.
 * The selling price is determined by the item being sold.
 *
 *
 * @author Ewe Jun Jie
 */
public class SellAction extends Action{
    private Sellable itemToPrint;
    private int sellingPrice;
    private static Random random = new Random();


    /**
     * Constructor.
     *
     * @param itemToPrint the item to be purchased
     */

    public SellAction(Sellable itemToPrint) {
        this.itemToPrint = itemToPrint;
        this.sellingPrice = itemToPrint.getSellingPrice();
    }

    /**
     * Executes the sell action.
     *
     * <p>
     * If the actor has sufficient credits, the purchase is processed. If the item is a special item (denoted by 'x' display character),
     * there is a chance that the sell fails. If successful, the item is added to the actor's inventory, and the selling price is add
     * from the actor's balance.
     * </p>
     *
     * @param actor the actor performing the action
     * @param map the current game map
     * @return a message indicating the result of the purchase action
     */

    @Override
    public String execute(Actor actor, GameMap map) {

        return itemToPrint.sell(actor, sellingPrice);
    }


    /**
     * Returns a description of the purchase action for display in the game menu.
     *
     * @param actor the actor performing the action
     * @return a description of the selling action
     */

    @Override
    public String menuDescription(Actor actor) {
        return "Sell item: " + itemToPrint + " (Selling Price: " + sellingPrice + " credits)";
    }

}
