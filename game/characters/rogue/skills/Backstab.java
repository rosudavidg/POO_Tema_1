package game.characters.rogue.skills;

import game.characters.Character;
import game.characters.Skill;
import game.map.Map;
import game.map.Zone;

public class Backstab extends Skill {
    private double criticalPercent;
    private int    strike;
    private int    noRoundsCritic;

    public Backstab() {
        final double newRaceRogue      = 1.2;
        final double newRaceKnight     = 0.9;
        final double newRacePyromancer = 1.25;
        final double newRaceWizard     = 1.25;

        final double newBaseDamage         = 200;
        final double newIncreaseBaseDamage = 20;
        final double newCriticalPercent    = 1.5;
        final int    newNoRoundsCritic     = 3;
        final int    newStartStrike        = 2;


        this.baseDamage         = newBaseDamage;
        this.increaseBaseDamage = newIncreaseBaseDamage;
        this.criticalPercent    = newCriticalPercent;
        this.noRoundsCritic     = newNoRoundsCritic;
        this.strike             = newStartStrike;

        this.initSkill(newRaceRogue, newRaceKnight, newRacePyromancer, newRaceWizard);
    }

    @Override
    public final void levelUpSkill() {
        levelUpBaseDamage();
    }

    @Override
    public final void updateDamage(final Character player, final Character enemy,
                                   final Map map) {
        damage = baseDamage * raceModifier * terrainModifier;

        if (map.getZone(player.getPosition()).equals(Zone.WOODS)
                && (((strike + 1) % noRoundsCritic) == 0)) {
            damage *= criticalPercent;
        }
    }

    @Override
    public final void useSkill(final Character enemy) {
        enemy.decreaseHpWith(damage);
        strike++;
    }
}
