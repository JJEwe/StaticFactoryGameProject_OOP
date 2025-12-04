package game.behaviour;

import edu.monash.fit2099.engine.positions.Location;
import game.ground.inheritree.Inheritree;
/**
 * Class representing the grow behavior for a tree.
 * This behavior allows a tree to grow into another type of tree after a certain number of ticks.
 * @author Maryam Vazeem
 *   @since 3.06.2024
 *   @version 1.0
 */
public class GrowBehaviour implements TreeBehaviour {

    /**
     * Constructor for GrowBehaviour.
     *
     * @param ticks the number of ticks after which the tree grows
     * @param type  the type of tree the current tree grows into
     */
    private int age;
    private int ticks;
    private Inheritree type;
    public GrowBehaviour(int ticks ,Inheritree type) {
        this.age = 0;
        this.ticks = ticks;
        this.type = type;
    }
    /**
     * Executes the grow behavior, incrementing the age of the tree and changing the ground
     * to the new tree type if the required number of ticks has passed.
     *
     * @param location the location where the behavior is executed
     * @return true if the tree has grown into a new type, false otherwise
     */
    @Override
    public boolean execute(Location location) {
        this.age++;
        if(age> ticks){
            location.setGround(type);
            return true;
        }
        return false;
    }



}
