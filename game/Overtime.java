package game;

public class Overtime {
    private int     noRounds; // numarul de runde
    private double  damage;   // valoarea atacului
    private boolean stun;     // imposibilitate de miscare

    public Overtime() {
        this.damage   = 0;
        this.noRounds = 0;
        this.stun     = false;
    }

    /**
     * Setez (actualizez) un overtime deja creat.
     * @param newStun Noua valoare pentru stun
     * @param newDamage Noua valoare pentru damage
     * @param newNoRounds Noua valoare pentru numarul de runde
     */
    public final void setOvertime(final boolean newStun, final double newDamage,
                                  final int newNoRounds) {
        this.noRounds = newNoRounds;
        this.damage   = newDamage;
        this.stun     = newStun;
    }

    public final void decreaseNoRounds() {
        noRounds--;
    }

    public final boolean hasStun() {
        return stun;
    }

    public final double getDamage() {
        return damage;
    }

    public final int getNoRounds() {
        return noRounds;
    }

    public final boolean isActive() {
        return (noRounds > 0);
    }
}
