package game.ground.inheritree;

import game.behaviour.GrowBehaviour;

/**
 * Class representing a young Inheritree.
 * Young Inheritree can grow into a mature Inheritree.
 * @author Maryam Vazeem
 *   @since 3.06.2024
 *     @version 1.0
 */

public class YoungInheritree extends Inheritree {

    /**
     * Class representing a young Inheritree.
     * Young Inheritree can grow into a mature Inheritree.
     */
    public YoungInheritree() {
        super('y');
        addBehaviour(1, new GrowBehaviour(6, new MatureInheritree()));
    }


}
