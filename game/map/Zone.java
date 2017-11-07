package game.map;

/**
 * Enum pentru zonele definite in problema.
 */
public enum Zone {
    LAND,     // Pentru Knight
    VOLCANIC, // Pentru Pyromancer
    DESERT,   // Pentru Wizard
    WOODS;    // Pentru Rogue

    public boolean equals(final Zone clone) {
        return (this == clone);
    }
}
