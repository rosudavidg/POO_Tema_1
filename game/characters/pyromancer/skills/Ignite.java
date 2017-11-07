package game.characters.pyromancer.skills;

import game.characters.Character;
import game.characters.Skill;
import game.map.Map;

public class Ignite extends Skill {
    private double baseDamageOvertime;
    private double increaseDamageOvertime;
    private double damageOvertime;

    public Ignite() {
        final double newRaceRogue      = 0.8;
        final double newRaceKnight     = 1.2;
        final double newRacePyromancer = 0.9;
        final double newRaceWizard     = 1.05;

        final double newBaseDamage             = 150;
        final double newIncreaseBaseDamage     = 20;
        final double newDamageOvertime         = 50;
        final double newIncreaseDamageOvertime = 30;

        this.baseDamage             = newBaseDamage;
        this.increaseBaseDamage     = newIncreaseBaseDamage;
        this.baseDamageOvertime     = newDamageOvertime;
        this.increaseDamageOvertime = newIncreaseDamageOvertime;

        this.initSkill(newRaceRogue, newRaceKnight, newRacePyromancer, newRaceWizard);
    }

    @Override
    public final void updateOvertime() {
        final int newNoRounds = 2;

        damageOvertime = Math.round(baseDamageOvertime * raceModifier * terrainModifier);

        setOvertime(false, damageOvertime, newNoRounds);
    }

    @Override
    public final void levelUpSkill() {
        levelUpBaseDamage();

        damageOvertime += increaseDamageOvertime;
    }

    @Override
    public final void updateDamage(final Character player, final Character enemy,
                                   final Map map) {
        damage = baseDamage * raceModifier * terrainModifier;
    }
}
