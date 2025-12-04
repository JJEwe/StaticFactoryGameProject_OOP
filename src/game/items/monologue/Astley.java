package game.items.monologue;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.MonologueAction;
import game.items.transaction.purchasable.Purchasable;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class representing Astley, an AI Device. It is able to be
 * purchased. Once purchased, Actor is subscribed to it, and will
 * have the option to listen to its monologue.
 *
 * Astley extends Item and implements both Purchasable and Monologuer.
 *
 * Created by:
 * @author Alicia Tiopan
 * @since 26/05/2024
 *
 * Modified by:
 * @author Alicia Tiopan
 * @since 31/05/2024
 */
public class Astley extends Item implements Purchasable, Monologuer {
    private static final int PRICE = 50;
    private static final int SUBSCRIPTION_FEE = 1;
    private static final int SUBSCRIPTION_INTERVAL = 5;
    private int tickCounter = 0;
    private boolean subscriptionStatus = true;


    /**
     * Constructor.
     * Initialise Astley with all its attributes.
     */
    public Astley(){
        super("Astley", 'z', true);
    }

    /**
     * Returns the price of the item.
     *
     * @return price of the purchasable.
     */
    @Override
    public int getPrice(){
        return PRICE;
    }

    /**
     * Determines the effects of purchasing this item.
     * Reduces the actor's balance by price amount and adds item into actor's inventory.
     *
     * @param actor the actor purchasing the item
     * @param price the price to be deducted from the actor's wallet
     * @return a string representation of the result of transaction
     */
    @Override
    public String purchase (Actor actor, int price){
        actor.deductBalance(PRICE);
        actor.addItemToInventory(this);
        return this + " purchased successfully. " + price + " credits debited from your account.";
    }

    /**
     * This method, at every tick, checks if it meets certain conditions to perform certain functionality.
     *
     * In this case,in each tick, it checks if Player is eligible to subscribe to this item.
     * If conditions are met, counter is incremented.
     * Once tick reaches 5, a subscription fee is to be paid, and a description is to be displayed.
     *
     * @param currentLocation current location of item.
     * @param actor the actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor){
        if (isSubscriptionActive(actor) && actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            tickCounter++;
            if (tickCounter == SUBSCRIPTION_INTERVAL){
                if (actor.getBalance() >= SUBSCRIPTION_FEE){
                    actor.deductBalance(SUBSCRIPTION_FEE);
                    Display display = new Display();
                    display.println("Subscription payment received!");
                    tickCounter = 0;
                } else {
                    Display display = new Display();
                    display.println("Subscription payment unsuccessful.");
                }
            }
        }
    }

    /**
     * Checks if actor is eligible to subscribe to this item.
     *
     * @param actor actor carrying this item.
     * @return true if actor is eligible to subscribe
     */
    public boolean isSubscriptionActive(Actor actor){
        if (actor.getItemInventory().contains(this)){
            subscriptionStatus = true;
        } else{
            subscriptionStatus = false;
        }
        return subscriptionStatus;
    }

    /**
     * Determines which line of monologue will the item say to the actor.
     *
     * @param actor the actor "conversing" with the entity
     * @return string of monologue
     */
    @Override
    public String getMonologue(Actor actor){
        Random random = new Random();

        ArrayList<String> monologues = new ArrayList<String>();
        monologues.add("The factory will never gonna give you up, valuable intern!");
        monologues.add("We promise we never gonna let you down with a range of staff benefits.");
        monologues.add("We never gonna run around and desert you, dear intern!");


        if (actor.getItemInventory().size() > 10){
            monologues.add("We never gonna make you cry with unfair compensation.");
        }

        if (actor.getBalance() > 50){
            monologues.add("Trust is essential in this business. We promise we never gonna say goodbye to a valuable intern like you.");
        }

        if (actor.getAttribute(BaseActorAttributes.HEALTH) < 2){
            monologues.add("Don't worry, we never gonna tell a lie and hurt you, unlike those hostile creatures.");
        }

        int randInt = random.nextInt(monologues.size());

        return monologues.get(randInt);
    }

    /**
     * Generates a list of allowable action for this monologue item.
     *
     * Checks for the item in actor's inventory and validate if it's a Player.
     * Allows item to have the option to Monologue with actor if both conditions are met.
     *
     * @param actor the actor that owns the item
     * @return action that this item can perform on said actor
     */
    @Override
    public ActionList allowableActions(Actor actor){
        ActionList actions = super.allowableActions(actor);

        if (actor.getItemInventory().contains(this) && actor.hasCapability(Status.HOSTILE_TO_ENEMY) && actor.getBalance() >= SUBSCRIPTION_FEE) {
            actions.add(new MonologueAction(this));
        }

        return actions;
    }

}
