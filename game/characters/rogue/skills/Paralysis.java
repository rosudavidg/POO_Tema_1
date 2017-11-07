package game.characters.rogue.skills;

import game.characters.Character;
import game.characters.Skill;
import game.map.Map;

public class Paralysis extends Skill {
    private int noRounds;
    private int minRounds;
    private int maxRounds;

    public Paralysis() {
        final double newRaceRogue      = 0.9;
        final double newRaceKnight     = 0.8;
        final double newRacePyromancer = 1.2;
        final double newRaceWizard     = 1.25;

        final double newBaseDamage         = 40;
        final double newIncreaseBaseDamage = 10;
        final int    newMinRounds          = 3;
        final int    newMaxRounds          = 6;

        this.baseDamage         = newBaseDamage;
        this.increaseBaseDamage = newIncreaseBaseDamage;
        this.minRounds          = newMinRounds;
        this.maxRounds          = newMaxRounds;

        this.initSkill(newRaceRogue, newRaceKnight, newRacePyromancer, newRaceWizard);
    }

    @Override
    public final void updateOvertime() {
        setOvertime(true, damage, noRounds);
    }

    @Override
    public final void levelUpSkill() {
        levelUpBaseDamage();
    }

    @Override
    public final void updateDamage(final Character player, final Character enemy,
                                   final Map map) {
        damage = baseDamage * raceModifier * terrainModifier;

        if (player.isOnHisZone(map)) {
            noRounds = maxRounds;
        } else {
            noRounds = minRounds;
        }
    }
}
