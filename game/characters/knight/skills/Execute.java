package game.characters.knight.skills;

import game.characters.Character;
import game.characters.Skill;
import game.map.Map;

public class Execute extends Skill {
    private double maxPercent;
    private double increaseMaxPercent;
    private double maxValuePercent;

    public Execute() {
        final double newRaceRogue      = 1.15;
        final double newRaceKnight     = 1;
        final double newRacePyromancer = 1.1;
        final double newRaceWizard     = 0.8;

        final double newBaseDamage         = 200;
        final double newIncreaseBaseDamage = 30;
        final double newMaxPercent         = 0.2;
        final double newIncreaseMaxPercent = 0.01;
        final double newMaxValuePercent    = 0.4;

        this.baseDamage         = newBaseDamage;
        this.increaseBaseDamage = newIncreaseBaseDamage;
        this.maxPercent         = newMaxPercent;
        this.increaseMaxPercent = newIncreaseMaxPercent;
        this.maxValuePercent    = newMaxValuePercent;

        this.initSkill(newRaceRogue, newRaceKnight, newRacePyromancer, newRaceWizard);
    }

    @Override
    public final void levelUpSkill() {
        this.levelUpBaseDamage();

        if (maxPercent < maxValuePercent) {
            maxPercent += increaseMaxPercent;
        }
    }

    @Override
    public final void updateDamage(final Character player, final Character enemy,
                                   final Map map) {
        damage = baseDamage * raceModifier * terrainModifier;

        if (enemy.getHp() < maxPercent * enemy.getMaxHp()) {
            damage = enemy.getHp();
        }
    }
}
