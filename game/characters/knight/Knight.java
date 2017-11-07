package game.characters.knight;

import game.characters.Character;
import game.characters.Skill;
import game.characters.Visitor;
import game.characters.knight.skills.Execute;
import game.characters.knight.skills.Slam;
import game.map.Map;
import game.map.Position;
import game.map.Zone;

public class Knight extends Character {

    public Knight(final Position newPosition) {
        final int newMaxHp           = 900;
        final int newIncreaseHp      = 80;
        final double newLandModifier = 1.15;

        this.initCharacter(newPosition, newMaxHp, newIncreaseHp, newLandModifier);

        this.skills[0] = new Execute();
        this.skills[1] = new Slam();
    }

    @Override
    public final char getRaceChar() {
        return 'K';
    }

    @Override
    public final boolean isOnHisZone(final Map map) {
        return (map.getZone(getPosition()) == Zone.LAND);
    }

    @Override
    public final void updateRaceModifier(final Skill skill) {
        skill.setRaceModifier(skill.getRaceKnight());
    }

    @Override
    public final boolean accept(final Visitor v) {
        return v.visit(this);
    }
}
