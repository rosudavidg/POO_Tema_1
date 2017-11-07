package game.characters.wizard.skills;

import game.characters.Character;
import game.characters.DeflectSkillHandler;
import game.characters.Skill;
import game.characters.Visitor;
import game.map.Map;

public class Deflect extends Skill {
    private double percent;
    private double increasePercent;
    private double maxPercent;

    public Deflect() {
        final double newRaceRogue      = 1.2;
        final double newRaceKnight     = 1.4;
        final double newRacePyromancer = 1.3;
        final double newRaceWizard     = 0;

        final double newBaseDamage         = 0;
        final double newIncreaseBaseDamage = 0;
        final double newPercent            = 0.35;
        final double newIncreasePercent    = 0.02;
        final double newMaxPercent         = 0.7;

        this.baseDamage         = newBaseDamage;
        this.increaseBaseDamage = newIncreaseBaseDamage;
        this.percent            = newPercent;
        this.increasePercent    = newIncreasePercent;
        this.maxPercent         = newMaxPercent;

        this.initSkill(newRaceRogue, newRaceKnight, newRacePyromancer, newRaceWizard);
    }

    @Override
    public final void levelUpSkill() {
        levelUpBaseDamage();

        if (percent + increasePercent <= maxPercent) {
            percent += increasePercent;
        }
    }

    @Override
    public final void updateDamage(final Character player, final Character enemy,
                                   final Map map) {

        Visitor deflectSkillHandler = new DeflectSkillHandler();

        if (enemy.accept(deflectSkillHandler)) {
            enemy.getSkill(0).resetModifiers();
            enemy.getSkill(1).resetModifiers();

            enemy.getSkill(0).updateTerrainModifier(enemy, map);
            enemy.getSkill(1).updateTerrainModifier(enemy, map);

            enemy.getSkill(0).updateDamage(enemy, player, map);
            enemy.getSkill(1).updateDamage(enemy, player, map);

            enemy.getSkill(0).roundDamage();
            enemy.getSkill(1).roundDamage();

            double damage0 = enemy.getSkill(0).getDamage();
            double damage1 = enemy.getSkill(1).getDamage();

            this.damage = Math.round(Math.round(damage0 * this.percent
                    * this.raceModifier) * this.terrainModifier)
                    + Math.round(Math.round(damage1 * this.percent
                    * this.raceModifier) * this.terrainModifier);

            enemy.updateSkills(player, map);
        }
    }
}
