package game.actions;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.transaction.purchasable.Purchasable;

/**
 * A class representing an action: PurchaseAction
 *
 * @author Muhammed Naveed Hassan
 */
public class PurchaseAction extends Action {
    private Purchasable itemToPrint;
    private int price;

    /**
     * Constructor.
     *
     * @param itemToPrint the item to be purchased
     */
    public PurchaseAction(Purchasable itemToPrint) {
        this.itemToPrint = itemToPrint;
        this.price = itemToPrint.getPrice();
    }


    /**
     * Executes the purchase action.
     *
     * <p>
     * If the actor has sufficient credits, the purchase is processed. If the item is a special item (denoted by 'x' display character),
     * there is a chance that the purchase fails. If successful, the item is added to the actor's inventory, and the price is deducted
     * from the actor's balance.
     * </p>
     *
     * @param actor the actor performing the action
     * @param map the current game map
     * @return a message indicating the result of the purchase action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getBalance() < price) {
            return "Insufficient credits to purchase the item.";
        }
        return itemToPrint.purchase(actor, price);
    }


    /**
     * Returns a description of the purchase action for display in the game menu.
     *
     * @param actor the actor performing the action
     * @return a description of the purchase action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Purchase item: " + itemToPrint + " (Cost: " + price + " credits)";
    }
}

