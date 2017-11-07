package game.characters;

import game.Overtime;
import game.map.Direction;
import game.map.Map;
import game.map.Position;

public abstract class Character implements Visitable {
    private Position position;      // Pozitia
    private int hp;                 // Viata
    private int maxHp;              // Viata maxima
    private int increaseHp;         // Viata maxima creste cu aceasta valoare
    private double terrainModifier; // Modificatorul de teren

    private int level;              // Nivelul
    private int xp;                 // Experienta

    private boolean stun;           // Se poate / nu se poate misca runda aceasta

    protected Skill[] skills = new Skill[2];    // Cele doua abilitati

    private Overtime overtime = new Overtime(); // Un nou overtime

    /**
     * Initializez un nou jucator.
     * @param newPosition Pozitia noua
     * @param newMaxHp Viata maxima noua
     * @param newIncreaseHp Valoarea cu cat creste viata la level up.
     * @param newTerrainModifier Modificatorul de teren.
     */
    protected final void initCharacter(final Position newPosition, final int newMaxHp,
                                       final int newIncreaseHp, final double newTerrainModifier) {
        this.position        = new Position(newPosition);
        this.maxHp           = newMaxHp;
        this.hp              = newMaxHp;
        this.increaseHp      = newIncreaseHp;
        this.terrainModifier = newTerrainModifier;

        this.level = 0;
        this.xp    = 0;
        this.stun  = false;
    }

    /**
     * Aplic overtime, daca exista (numarul de runde este diferit de 0).
     */
    public final void applyOvertime() {
        if (overtime.getNoRounds() > 0) {
            stun = overtime.hasStun();
            hp  -= overtime.getDamage();

            overtime.decreaseNoRounds();
        } else {
            stun = false;
        }
    }

    /**
     * Scade din viata jucatorului.
     * @param damage Valoarea care trebuie scazuta din viata jucatorului
     */
    public final void decreaseHpWith(final double damage) {
        hp -= damage;
    }

    /**
     * Schimba pozitia jucatorului (muta jucatorul).
     * @param move Directia in care merge jucatorul (enum de tip Direction, ex: UP).
     */
    public final void movePosition(final Direction move) {
        switch (move) {
            case UP:
                position.movePositionWith(-1, 0);
                break;
            case DOWN:
                position.movePositionWith(1, 0);
                break;
            case LEFT:
                position.movePositionWith(0, -1);
                break;
            case RIGHT:
                position.movePositionWith(0, 1);
                break;
            case NODIRECTION:
                break;
            default:
                break;
        }
    }

    /**
     * Setez un nou overtime.
     * @param newOvertime Noul overtime
     */
    final void setOvertime(final Overtime newOvertime) {
        overtime.setOvertime(newOvertime.hasStun(), newOvertime.getDamage(),
                newOvertime.getNoRounds());
    }

    /**
     * Daca jucatorul isi infrange adversarul, acesta primeste experienta
     * calculata in aceasta metoda.
     * @param enemy Adversarul
     */
    private void setXp(final Character enemy) {
        final int multiplier = 40;
        final int range      = 200;

        double max = range - (level - enemy.level) * multiplier;

        if (max < 0) {
            max = 0;
        }

        xp += max;

        tryLevelUp();
    }

    /**
     * Se incearca cresterea in nivel a jucatorului.
     */
    private void tryLevelUp() {
        final int base       = 250;
        final int multiplier = 50;

        while (xp >= base + level * multiplier) {

            level++;

            skills[0].levelUpSkill();
            skills[1].levelUpSkill();

            maxHp += increaseHp;
            hp     = maxHp;
        }
    }

    /**
     * Se actualizeaza abilitatile jucatorilor.
     * @param enemy Adversarul
     * @param map Harta
     */
    public final void updateSkills(final Character enemy, final Map map) {
        skills[0].updateSkill(this, enemy, map);
        skills[1].updateSkill(this, enemy, map);
    }

    /**
     * Se folosesc abilitatile pe adversar.
     * @param enemy Adversar
     */
    public final void useSkills(final Character enemy) {
        skills[0].useSkill(enemy);
        skills[1].useSkill(enemy);
    }

    /**
     * Se verifica daca jucatorul a castigat.
     * @param enemy Adversarul
     */
    public final void checkIfWin(final Character enemy) {
        if (isAlive() && !enemy.isAlive()) {
            setXp(enemy);
        }
    }

    /**
     * Verifica daca doi jucatori se pot ataca.
     * @param enemy Adversar
     * @return True daca se pot ataca, false altfel.
     */
    public final boolean canFightWith(final Character enemy) {
        return (isAlive() && enemy.isAlive() && hasSamePositionWith(enemy));
    }

    /**
     * Verific daca jucatorul se poate muta (nu are stun si este in viata).
     * @return True daca se poate muta, false altfel
     */
    public final boolean canMove() {
        return (!hasStun() && isAlive());
    }

//    Avoid instanceof
    /**
     * In functie de rasa jucatorului, intorc litera corespunzatoare acesteia.
     * @return Litera corespunzatoare rasei.
     */
    public char getRaceChar() {
        return '\0';
    }
    /**
     * Pentru fiecare tip de jucatorul, verific daca se afla pe zona lui
     * specifica (ex: Knight, LAND).
     * @param map Harta
     * @return True daca se afla pe zona lui, false altfel
     */
    public boolean isOnHisZone(final Map map) {
        return false;
    }
    /**
     * Actualizez modificatorul de rasa pentru fiecare tip de jucator.
     * @param skill Abilitatea
     */
    public void updateRaceModifier(final Skill skill) {
    }

//    Getters
    /**
     * Verific daca doi jucatori sunt in acelasi loc.
     * @param enemy Adversarul.
     * @return True daca sunt in acelasi loc, false altfel.
     */
    private boolean hasSamePositionWith(final Character enemy) {
        return position.equals(enemy.position);
    }

    /**
     * Verific daca jucatorul mai este in viata (viata > 0).
     * @return True daca este in viata, false altfel.
     */
    public final boolean isAlive() {
        return this.hp > 0;
    }

    public final double getTerrainModifier() {
        return terrainModifier;
    }

    private boolean hasStun() {
        return stun;
    }

    public final Position getPosition() {
        return position;
    }

    public final int getHp() {
        return hp;
    }

    public final int getMaxHp() {
        return maxHp;
    }

    public final int getLevel() {
        return level;
    }

    public final int getXp() {
        return xp;
    }

    public final Skill getSkill(final int index) {
        return skills[index];
    }
}
