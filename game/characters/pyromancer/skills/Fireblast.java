package game.characters.pyromancer.skills;

import game.characters.Character;
import game.characters.Skill;
import game.map.Map;

public class Fireblast extends Skill {
    public Fireblast() {
        final double newRaceRogue      = 0.8;
        final double newRaceKnight     = 1.2;
        final double newRacePyromancer = 0.9;
        final double newRaceWizard     = 1.05;

        final double newBaseDamage         = 350;
        final double newIncreaseBaseDamage = 50;

        this.baseDamage         = newBaseDamage;
        this.increaseBaseDamage = newIncreaseBaseDamage;

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
    }
}
