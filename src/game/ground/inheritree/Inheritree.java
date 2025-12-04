package game.ground.inheritree;


import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.behaviour.TreeBehaviour;

import java.util.Map;
import java.util.TreeMap;

/**
 * Abstract class representing an Inheritree in the game.
 * Inheritree can exhibit various behaviors such as growing and dropping fruits.
 * @author Maryam Vazeem
 * @since 3.06.2024
 * @version 1.0
 * created by Alicia tiopan and modified by Maryam Vazeem
 */

public abstract class Inheritree extends Ground {
    private Map<Integer, TreeBehaviour >behaviours = new TreeMap<>();


    /**
     * Constructor for Inheritree.
     *
     * @param displayChar the character to display for this type of ground
     */


    public Inheritree(char displayChar) {
        super(displayChar);
    }
    /**
     * Perform the tick action, which updates the tree's state.
     * This method calls the execute method of all added behaviors in priority order.
     *
     * @param location the location of the ground
     */
    @Override
    // plant behaviour
    public void tick(Location location) {
        for (TreeBehaviour behaviour : behaviours.values()) {
            // call execute function and assign to boolean variable
            boolean result = behaviour.execute(location);
            // check if boolean is true
            if (result) {
                return;
            }
        }
    }
    /**
     * Add a behavior to the Inheritree with a specific priority.
     *
     * @param key       the priority of the behavior
     * @param behaviour the behavior to add
     */

    public void addBehaviour(int key , TreeBehaviour behaviour){
        this.behaviours.put(key,behaviour );
    }

}
