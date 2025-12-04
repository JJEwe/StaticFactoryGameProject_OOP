package game;

import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class to manage GameMaps and their associated names.
 * Created by:
 * @author Muhammed Naveed Hassan
 */
public class MapManager {
    // List to store game maps
    private static List<GameMap> gameMaps = new ArrayList<>();
    // List to store names of the game maps
    private static List<String> mapNames = new ArrayList<>();

    /**
     * Adds a new GameMap and its associated name to the manager.
     *
     * @param map the GameMap to add
     * @param mapName the name associated with the GameMap
     */
    public static void addMap(GameMap map, String mapName) {
        gameMaps.add(map);
        mapNames.add(mapName);
    }

    /**
     * Retrieves a list of GameMaps excluding the specified current map.
     *
     * @param currentMap the current GameMap that should be excluded
     * @return a list of GameMaps excluding the current map
     */
    public static List<GameMap> getAvailableMaps(GameMap currentMap) {
        return gameMaps.stream().filter(map -> !map.equals(currentMap)).collect(Collectors.toList());
    }

    /**
     * Retrieves the name associated with the specified GameMap.
     *
     * @param map the GameMap whose name is to be retrieved
     * @return the name associated with the GameMap, or null if the map is not found
     */
    public static String getMapName(GameMap map) {
        int index = gameMaps.indexOf(map);
        if (index != -1) {
            return mapNames.get(index);
        }
        return null;
    }
}
