package game.ground;


import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.spawner.Spawner;
import java.util.List;
import java.util.Random;

/**
 * A class representing a crater.
 *
 * <p>
 *     This class spawns in a hostile on available exits.
 * </p>
 *
 * @author Alicia Tiopan
 * Modified by: Ewe Jun Jie
 */

public class Crater extends Ground {

    Random random = new Random();

    private Spawner creatureSpawner;


    /**
     * Constructor.
     */
    public Crater(Spawner creatureSpawner){
        super('u');
        this.creatureSpawner = creatureSpawner;
    }

    /**
     * Determines the behaviour of the crater per tick of the game.
     *
     * @param location The location of the ground.
     */
    @Override
    public void tick(Location location){
        super.tick(location);
        List<Exit> exits = location.getExits();

        if (exits.isEmpty()){
            return;
        }

        // Get adjacent locations to validate spawning
        Exit selectedExit = exits.get(random.nextInt(exits.size()));

        Location spawnLoc = selectedExit.getDestination();
        if (!spawnLoc.containsAnActor())
        {
            creatureSpawner.spawnActor(spawnLoc);
        }else{return;}



    }

}