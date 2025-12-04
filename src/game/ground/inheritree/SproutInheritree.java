package game.ground.inheritree;


import game.behaviour.GrowBehaviour;
/**
 * Class representing a sprout Inheritree.
 * Sprout Inheritree can grow into a sapling Inheritree.
 * @author Maryam Vazeem
 *   @since 3.06.2024
 *  @version 1.0
 *
 */
public class SproutInheritree extends Inheritree {


    public SproutInheritree() {
        super(',');
        addBehaviour(1, new GrowBehaviour(3, new SaplingInheritree()));
    }


}


