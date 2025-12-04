package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actions.spawner.AlienBugSpawner;
import game.actions.spawner.HuntsmanSpiderSpawner;
import game.actions.spawner.SuspiciousAstronautSpawner;
import game.actors.HuntsmanSpider;
import game.actors.Player;
import game.ground.*;
import game.ground.inheritree.SproutInheritree;
import game.items.consumable.JarOfPickles;
import game.items.consumable.PotOfGold;
import game.items.transaction.sellable.Bolts;
import game.items.transaction.sellable.Metal;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Muhammed Naveed Hassan
 *
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        Bolts bolts = new Bolts();
        Metal metal = new Metal();

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new ComputerTerminal(), new SproutInheritree());

        List<String> polymorphiaMap = Arrays.asList(
                "...~~~~.........~~~...........",
                "...~~~~.......................",
                "...~~~........................",
                "..............................",
                ".............#####............",
                ".............#___#...........~",
                ".............#=__#..........~~",
                ".............##_##.........~~~",
                "...........,.....~~........~~~",
                ".................~~~~.......~~~",
                ".............~~~~~~~........~~",
                "......~.....~~~~~~~~.........~",
                ".....~~~...~~~~~~~~~..........",
                ".....~~~~~~~~~~~~~~~~........~",
                ".....~~~~~~~~~~~~~~~~~~~....~~");

        GameMap gameMap = new GameMap(groundFactory, polymorphiaMap);
        world.addGameMap(gameMap);
        MapManager.addMap(gameMap, "Polymorphia");

        Location boltsLocation = gameMap.at(10, 10); // Adjust coordinates as needed
        boltsLocation.addItem(bolts);

        Location metalLocation = gameMap.at(5, 10); // Adjust coordinates as needed
        metalLocation.addItem(metal);
        gameMap.at(1,4).addItem(new PotOfGold());
        gameMap.at(1,5).addItem(new PotOfGold());
        gameMap.at(2,5).addItem(new JarOfPickles());

        gameMap.at(10,5).setGround(new Crater(new HuntsmanSpiderSpawner()));
        gameMap.at(7,5).setGround(new Crater(new AlienBugSpawner()));

        gameMap.at(4,9).setGround(new Crater(new SuspiciousAstronautSpawner()));

        gameMap.at(7, 9).addActor(new HuntsmanSpider());


        // Define factory map
        List<String> factoryMap = Arrays.asList(
                ".......",
                ".#####.",
                ".#___#.",
                ".#=__#.",
                ".##_##.",
                ".......",
                ".......",
                ".......",
                ".......",
                ".......");

        GameMap factoryGameMap = new GameMap(groundFactory, factoryMap);
        world.addGameMap(factoryGameMap);
        MapManager.addMap(factoryGameMap, "Factory Spaceship Parking Lot");
        factoryGameMap.at(4,5).setGround(new HumanoidFigure());


        // Define new moon map
        List<String> refactorioMap = Arrays.asList(
                "..........................~~~~",
                "..........................~~~~",
                "..........................~~~~",
                "~..........................~..",
                "~~...........#####............",
                "~~~..........#___#............",
                "~~~..........#=__#............",
                "~~~..........##_##............",
                "~~~.........,........~~.......",
                "~~~~................~~~~......",
                "~~~~...............~~~~~......",
                "..~................~~~~.......",
                "....................~~........",
                ".............~~...............",
                "............~~~~..............");

        GameMap newMoonGameMap = new GameMap(groundFactory, refactorioMap);
        world.addGameMap(newMoonGameMap);
        MapManager.addMap(newMoonGameMap, "Refactorio");

        Location boltsLocation2 = newMoonGameMap.at(10, 10); // Adjust coordinates as needed
        boltsLocation2.addItem(bolts);

        Location metalLocation2 = newMoonGameMap.at(5, 10); // Adjust coordinates as needed
        metalLocation2.addItem(metal);
        newMoonGameMap.at(1,4).addItem(new PotOfGold());
        newMoonGameMap.at(1,5).addItem(new PotOfGold());
        newMoonGameMap.at(2,5).addItem(new JarOfPickles());

        newMoonGameMap.at(10,5).setGround(new Crater(new HuntsmanSpiderSpawner()));
        newMoonGameMap.at(7, 9).addActor(new HuntsmanSpider());

        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Player player = new Player("Intern", '@', 4);
        world.addPlayer(player, gameMap.at(15, 6));

        world.run();
    }
}
