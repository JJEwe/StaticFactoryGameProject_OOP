package game.actors;

import game.Ability;
/**
 * SuspiciousAstronaut represents a unique type of hostile entity within the game.
 * This actor is characterized by extremely high health and an infinite energy level, making it a formidable opponent.
 * It also has a special capability that restricts entry to certain areas of the game environment.
 * The Suspicious Astronaut uses a custom character symbol 'ඞ' to be represented on the game map.
 * @author Maryam Vazeem
 *  @since 9.05.2024
 *   @version 1.0
 */
public class SuspiciousAstronaut extends Hostile {
    private static final int INTRINSIC_WEAPON_DAMAGE = Integer.MAX_VALUE;

    /**
     * Constructs a SuspiciousAstronaut with predefined attributes.
     * The astronaut is initialized with the name "Suspicious Astronaut", uses a special character 'ඞ',
     * and is assigned extremely high health and energy values.
     * It possesses a unique ability that allows it to restrict entry to certain parts of the game.
     * The attack strategy of the Suspicious Astronaut is to "eliminate", and it deals significant damage upon attacking.
     */
    public SuspiciousAstronaut() {
        super("Suspicious Astronaut", 'ඞ', 99, INTRINSIC_WEAPON_DAMAGE, "eliminates", 100);
        this.addCapability(Ability.RESTRICTED_ENTRY);

    }
}