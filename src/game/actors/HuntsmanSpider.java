package game.actors;

import game.Ability;
import game.behaviour.AttackBehaviour;
import game.behaviour.WanderBehaviour;

/**
 * A class representing HuntsmanSpider, a type of Hostile.
 */
public class HuntsmanSpider extends Hostile {

    public HuntsmanSpider() {

        super("Huntsman Spider", '8', 1, 1, "kicks", 25);
        this.addCapability(Ability.RESTRICTED_ENTRY);
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
    }

}
