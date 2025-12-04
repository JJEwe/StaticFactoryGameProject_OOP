package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.*;
import game.MapManager;
import game.actions.PurchaseAction;
import game.actions.TravelAction;
import game.items.monologue.Astley;
import game.items.consumable.EnergyDrink;
import game.items.transaction.ToiletRoll;
import game.items.transaction.purchasable.Theseus;
import game.weapons.DragonSlayerSwordReplica;
import java.util.List;


/**
 * A class representing a computer terminal.
 *
 * <p>
 * This class represents a computer terminal item that allows players to perform actions such as purchasing items.
 * </p>
 * Created by:
 * @author Muhammed Naveed Hassan
 * Modified by:
 * @author Muhammed Naveed Hassan
 *
 * Modified by:
 * @author Alicia Tiopan
 * @since 2/06/2024
 */
public class ComputerTerminal extends Ground {
    /**
     * Constructor.
     */
    public ComputerTerminal() {
        super('=');
    }

    /**
     * Produces the actions that the player can perform with the computer terminal.
     *
     * @param location the location of the ground
     * @return a list of actions that the actor can do with the computer terminal
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        GameMap currentMap = location.map();

        // Add purchase actions for energy drink, dragon slayer sword replica, and toilet roll
        actions.add(new PurchaseAction(new EnergyDrink()));
        actions.add(new PurchaseAction(new DragonSlayerSwordReplica()));
        actions.add(new PurchaseAction(new ToiletRoll()));
        actions.add(new PurchaseAction(new Theseus()));
        actions.add(new PurchaseAction(new Astley()));
        // Add travel actions for each game map excluding the current map
        List<GameMap> availableMaps = MapManager.getAvailableMaps(currentMap);
        for (GameMap gameMap : availableMaps) {
            String mapName = MapManager.getMapName(gameMap);
            actions.add(new TravelAction(gameMap, mapName));
        }
        return actions;
    }
}
