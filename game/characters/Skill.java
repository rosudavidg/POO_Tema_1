package game.characters;

import game.Overtime;
import game.map.Map;

public abstract class Skill {
    private double   racePyromancer;     // Modificator Pyromancer
    private double   raceKnight;         // Modificator Knight
    private double   raceWizard;         // Modificator Wizard
    private double   raceRogue;          // Modificator Rogue

    protected double raceModifier;       // Modificatorul de rasa final
    protected double terrainModifier;    // Modificatorul de teren final

    protected double damage;             // Valoarea atacului reala (finala)
    protected double baseDamage;         // Valoarea de baza a atacului.
    protected double increaseBaseDamage; // Valoarea de crestere a atacului de baza
    private Overtime overtime;           // Overtime-ul abilitatii

    /**
     * Initializarea unei abilitati.
     * @param newRaceRogue Modificator Rogue
     * @param newRaceKnight Modificator Knight
     * @param newRacePyromancer Modificator Pyromancer
     * @param newRaceWizard Modificator Wizard
     */
    protected final void initSkill(final double newRaceRogue, final double newRaceKnight,
                                final double newRacePyromancer, final double newRaceWizard) {
        this.racePyromancer = newRacePyromancer;
        this.raceKnight     = newRaceKnight;
        this.raceWizard     = newRaceWizard;
        this.raceRogue      = newRaceRogue;

        this.overtime       = new Overtime();
    }

    /**
     * Actualizarea unei abilitatii in functie de parametrii.
     * @param player Jucatorul care aplica abilitatea
     * @param enemy Jucatorul caruia i se aplica abilitatea
     * @param map Harta
     */
    final void updateSkill(final Character player, final Character enemy, final Map map) {
        updateModifiers(player, enemy, map);
        updateDamage(player, enemy, map);
        updateOvertime();
    }

    protected final void setOvertime(final boolean newStun, final double newDamage,
                                  final int newNoRounds) {
        overtime.setOvertime(newStun, newDamage, newNoRounds);
    }

    /**
     * Actualizarea modificatorilor de rasa si teren.
     * @param player Jucatorul care aplica abilitatea
     * @param enemy Jucatorul caruia i se aplica abilitatea
     * @param map Harta
     */
    private void updateModifiers(final Character player, final Character enemy,
                                 final Map map) {
        updateRaceModifier(enemy);
        updateTerrainModifier(player, map);
    }

    /**
     * [Avoid instanceof]
     * Fiecare adversar trimite inapoi un modificator, in functie de rasa acestuia.
     * @param enemy Adversarul
     */
    private void updateRaceModifier(final Character enemy) {
        enemy.updateRaceModifier(this);
    }

    public final void updateTerrainModifier(final Character player, final Map map) {
        if (player.isOnHisZone(map)) {
            terrainModifier = player.getTerrainModifier();
        } else {
            resetTerrainModifier();
        }
    }

    /**
     * Reseteaza modificatorii la valoarea implicita (1).
     */
    public final void resetModifiers() {
        this.resetTerrainModifier();
        this.resetRaceModifier();
    }

    private void resetTerrainModifier() {
        this.terrainModifier = 1;
    }

    private void resetRaceModifier() {
        this.raceModifier = 1;
    }

    /**
     * Evolueaza doar atacul de baza al abilitatii.
     */
    protected final void levelUpBaseDamage() {
        this.baseDamage += this.increaseBaseDamage;
    }

    /**
     * [Avoid instanceof]
     * Fiecare abilitate isi actualizeaza overtime-ul.
     */
    public void updateOvertime() {
    }

    /**
     * [Avoid instanceof]
     * Fiecare abilitate isi actualizeaza valorile de baza
     * ale atacului. (atacurilor)
     */
    public void levelUpSkill() {
    }

    /**
     * In functie de parametrii, fiecare abilitate ce urmeaza a fi
     * folosita isi calculeaza valoarea reala a atacului.
     * @param player Jucatorul care foloseste abilitatea
     * @param enemy Jucatorul caruia i se aplica abilitatea
     * @param map Harta
     */
    public void updateDamage(final Character player, final Character enemy,
                             final Map map) {
    }

    /**
     * Scad din viata adversarului valoarea anterior calculata (vezi updateDamage), adica
     * valoarea reala a atacului.
     * Daca abilitatea are Overtime, acesta va fi asociat adversarului
     * si va fi activ incepand cu runda urmatoare.
     * @param enemy Adversarul
     */
    public void useSkill(final Character enemy) {
        roundDamage();
        enemy.decreaseHpWith(this.damage);

        if (this.overtime.isActive()) {
            enemy.setOvertime(this.overtime);
        }
    }

    /**
     * Aproximare a valorii atacului unei abilitati.
     */
    public void roundDamage() {
        this.damage = Math.round(this.damage);
    }

    public final void setRaceModifier(final double newRaceModifier) {
        raceModifier = newRaceModifier;
    }

    public final double getDamage() {
        return this.damage;
    }

    public final double getRacePyromancer() {
        return this.racePyromancer;
    }

    public final double getRaceKnight() {
        return this.raceKnight;
    }

    public final double getRaceWizard() {
        return this.raceWizard;
    }

    public final double getRaceRogue() {
        return this.raceRogue;
    }
}
