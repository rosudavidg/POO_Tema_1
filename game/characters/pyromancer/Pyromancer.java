package game.characters.pyromancer;

import game.characters.Character;
import game.characters.Skill;
import game.characters.Visitor;
import game.characters.pyromancer.skills.Fireblast;
import game.characters.pyromancer.skills.Ignite;
import game.map.Map;
import game.map.Position;
import game.map.Zone;

public class Pyromancer extends Character {
    public Pyromancer(final Position newPosition) {
        final int newMaxHp           = 500;
        final int newIncreaseHp      = 50;
        final double newLandModifier = 1.25;

        this.initCharacter(newPosition, newMaxHp, newIncreaseHp, newLandModifier);

        this.skills[0] = new Fireblast();
        this.skills[1] = new Ignite();
    }

    @Override
    public final char getRaceChar() {
        return 'P';
    }

    @Override
    public final boolean isOnHisZone(final Map map) {
        return (map.getZone(getPosition()) == Zone.VOLCANIC);
    }

    @Override
    public final void updateRaceModifier(final Skill skill) {
        skill.setRaceModifier(skill.getRacePyromancer());
    }

    @Override
    public final boolean accept(final Visitor v) {
        return v.visit(this);
    }
}
