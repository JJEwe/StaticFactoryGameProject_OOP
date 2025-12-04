package game.ground.inheritree;


import game.behaviour.DropBehaviour;
import game.behaviour.GrowBehaviour;

import game.items.consumable.SmallFruit;
/**
 * Class representing a sapling Inheritree.
 * Sapling Inheritree can drop small fruits and grow into a young Inheritree.
 * @author Maryam Vazeem
 *  @since 3.06.2024
 *  @version 1.0
 *   created by Alicia tiopan and modified by Maryam Vazeem
 */
public class SaplingInheritree extends Inheritree {
    private static final double SMALL_FRUIT_DROP_CHANCE = 0.3;
    /**
     * Constructor for SaplingInheritree.
     * Adds the GrowBehaviour to grow into a young Inheritree and DropBehaviour to drop small fruits.
     */

    public SaplingInheritree() {
        super('t');
        addBehaviour(1, new GrowBehaviour(5, new YoungInheritree()));
        addBehaviour(2, new DropBehaviour(new SmallFruit(), SMALL_FRUIT_DROP_CHANCE));
    }



}