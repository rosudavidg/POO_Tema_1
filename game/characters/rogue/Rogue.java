package game.characters.rogue;

import game.characters.Character;
import game.characters.Skill;
import game.characters.Visitor;
import game.characters.rogue.skills.Backstab;
import game.characters.rogue.skills.Paralysis;
import game.map.Map;
import game.map.Position;
import game.map.Zone;

public class Rogue extends Character {
    public Rogue(final Position newPosition) {
        final int newMaxHp           = 600;
        final int newIncreaseHp      = 40;
        final double newLandModifier = 1.15;

        this.initCharacter(newPosition, newMaxHp, newIncreaseHp, newLandModifier);

        this.skills[0] = new Backstab();
        this.skills[1] = new Paralysis();
    }

    @Override
    public final char getRaceChar() {
        return 'R';
    }

    @Override
    public final boolean isOnHisZone(final Map map) {
        return (map.getZone(getPosition()) == Zone.WOODS);
    }

    @Override
    public final void updateRaceModifier(final Skill skill) {
        skill.setRaceModifier(skill.getRaceRogue());
    }

    @Override
    public final boolean accept(final Visitor v) {
        return v.visit(this);
    }
}
