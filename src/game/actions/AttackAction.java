package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.Random;

/**
 * An action that represents an actor attacking another actor with a weapon.
 */
public class AttackAction extends Action {

    private Actor target;
    private String direction;
    private Weapon weapon;
    private Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target   the target actor to attack
     * @param direction the direction of the attack
     * @param weapon    the weapon used for the attack
     */
    public AttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Executes the attack action.
     *
     * @param actor the actor performing the attack
     * @param map   the game map
     * @return the result of the attack
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (weapon == null){
            weapon = actor.getIntrinsicWeapon();
        }

        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
            return actor + " misses " + target + ".";
        }

        int damage = weapon.damage();
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map);
        }

        return result;
    }

    /**
     * Provides a description of the action for the menu.
     *
     * @param actor the actor performing the action
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with " + (weapon.toString() != "no weapon" ? weapon : "Intrinsic Weapon");
    }
}
