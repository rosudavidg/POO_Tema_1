package game.characters.wizard;

import game.characters.Character;
import game.characters.Skill;
import game.characters.Visitor;
import game.characters.wizard.skills.Deflect;
import game.characters.wizard.skills.Drain;
import game.map.Map;
import game.map.Position;
import game.map.Zone;

public class Wizard extends Character {
    public Wizard(final Position newPosition) {
        final int newMaxHp           = 400;
        final int newIncreaseHp      = 30;
        final double newLandModifier = 1.1;

        this.initCharacter(newPosition, newMaxHp, newIncreaseHp, newLandModifier);

        this.skills[0] = new Drain();
        this.skills[1] = new Deflect();
    }

    @Override
    public final char getRaceChar() {
        return 'W';
    }

    @Override
    public final boolean isOnHisZone(final Map map) {
        return (map.getZone(getPosition()) == Zone.DESERT);
    }

    @Override
    public final void updateRaceModifier(final Skill skill) {
        skill.setRaceModifier(skill.getRaceWizard());
    }

    @Override
    public final boolean accept(final Visitor v) {
        return v.visit(this);
    }
}
