package game.characters.knight.skills;

import game.characters.Character;
import game.characters.Skill;
import game.map.Map;

public class Slam extends Skill {
    public Slam() {
        final double newRaceRogue      = 0.8;
        final double newRaceKnight     = 1.2;
        final double newRacePyromancer = 0.9;
        final double newRaceWizard     = 1.05;

        final double newBaseDamage         = 100;
        final double newIncreaseBaseDamage = 40;

        this.baseDamage         = newBaseDamage;
        this.increaseBaseDamage = newIncreaseBaseDamage;

        this.initSkill(newRaceRogue, newRaceKnight, newRacePyromancer, newRaceWizard);
    }

    @Override
    public final void updateOvertime() {
        setOvertime(true, 0, 1);
    }

    @Override
    public final void levelUpSkill() {
        levelUpBaseDamage();
    }

    @Override
    public final void updateDamage(final Character player, final Character enemy,
                                   final Map map) {
        damage = baseDamage * raceModifier * terrainModifier;
    }
}
