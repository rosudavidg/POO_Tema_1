package game.map;

/**
 * Orice jucator are o pozitie (x, y):
 * x este numarul liniei
 * y este numarul coloanei.
 */
public class Position {
    private int x;
    private int y;

    public Position(final int newX, final int newY) {
        this.x = newX;
        this.y = newY;
    }

    public Position(final Position newPosition) {
        this(newPosition.x, newPosition.y);
    }

    /**
     * Modificarea pozitiei actuale a unui jucator.
     * @param addX La x se adauga acest parametru.
     * @param addY La y se adauga acest parametru.
     */
    public final void movePositionWith(final int addX, final int addY) {
        x += addX;
        y += addY;
    }

    /**
     * Verificare daca doua pozitii sunt egale.
     * @param clone Pozitia cu care se verifica
     * @return Egalitatea celor doua pozitii
     */
    public final boolean equals(final Position clone) {
        return ((x == clone.x) && y == clone.y);
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }
}
