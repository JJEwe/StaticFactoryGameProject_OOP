package game.ground.inheritree;


import game.behaviour.DropBehaviour;
import game.items.consumable.LargeFruit;
/**
 * Class representing a mature Inheritree.
 * Mature Inheritree can drop large fruits.
 * @author Maryam Vazeem
 *  @since 3.06.2024
 *  @version 1.0
 *   created by Alicia tiopan and modified by Maryam Vazeem
 */
public class MatureInheritree extends Inheritree {

    private static final double LARGE_FRUIT_DROP_CHANCE = 0.2;

    /**
     * Constructor for MatureInheritree.
     * Adds the DropBehaviour to drop large fruits.
     */
    public MatureInheritree() {
        super('T');
        addBehaviour(2, new DropBehaviour(new LargeFruit(), LARGE_FRUIT_DROP_CHANCE));
    }
}
