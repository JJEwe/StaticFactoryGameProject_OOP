package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class representing a travel action to move between different locations.
 * <p>
 * This class allows an actor to travel from their current location to a specified
 * destination map, finding a suitable starting location within the destination map.
 * </p>
 * Created by:
 * @author Muhammed Naveed Hassan
 */
public class TravelAction extends Action {
    private final String mapName;
    private GameMap destinationMap;
    private Location startingLocationInsideShip;

    /**
     * Constructor.
     *
     * @param destinationMap the destination map to travel to
     * @param mapName the name of the destination map
     */
    public TravelAction(GameMap destinationMap, String mapName) {
        this.destinationMap = destinationMap;
        this.mapName = mapName;
    }

    /**
     * Executes the travel action.
     *
     * <p>
     * This method handles the process of removing the actor from their current map
     * and placing them at a suitable starting location within the destination map.
     * </p>
     *
     * @param actor the actor performing the action
     * @param map the current game map
     * @return a message indicating the result of the travel action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Remove the actor from the current map
        map.removeActor(actor);

        // Find a suitable starting location within the ship's map coordinates
        for (int x = 0; x < destinationMap.getXRange().max(); x++) {
            for (int y = 0; y < destinationMap.getYRange().max(); y++) {
                Location location = destinationMap.at(x, y);
                if (location.getGround().getDisplayChar() == '_') {
                    startingLocationInsideShip = location;
                    destinationMap.addActor(actor, startingLocationInsideShip);
                    return actor + " travels to " + mapName;
                }
            }
        }
        return "Travel not possible";
    }

    /**
     * Returns a description of the travel action for display in the game menu.
     *
     * @param actor the actor performing the action
     * @return a description of the travel action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Travel to " + mapName;
    }
}
