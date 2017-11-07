package game.characters.wizard.skills;

import game.characters.Character;
import game.characters.Skill;
import game.map.Map;

public class Drain extends Skill {

    private double percent;
    private double increasePercent;


    public Drain() {
        final double newRaceRogue      = 0.8;
        final double newRaceKnight     = 1.2;
        final double newRacePyromancer = 0.9;
        final double newRaceWizard     = 1.05;

        final double newBaseDamage         = 0;
        final double newIncreaseBaseDamage = 0;
        final double newPercent            = 0.2;
        final double newIncreasePercent    = 0.05;

        this.baseDamage         = newBaseDamage;
        this.increaseBaseDamage = newIncreaseBaseDamage;
        this.percent            = newPercent;
        this.increasePercent    = newIncreasePercent;

        this.initSkill(newRaceRogue, newRaceKnight, newRacePyromancer, newRaceWizard);
    }

    @Override
    public final void levelUpSkill() {
        levelUpBaseDamage();

        percent += increasePercent;
    }

    @Override
    public final void updateDamage(final Character player, final Character enemy,
                                   final Map map) {
        final double subPercent = 0.3;
        double subDamage = subPercent * enemy.getMaxHp();

        if (enemy.getHp() < subDamage) {
            subDamage = enemy.getHp();
        }

        baseDamage = percent * subDamage;

        damage = baseDamage * raceModifier * terrainModifier;
    }
}
