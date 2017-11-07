package game.characters;

import game.characters.knight.Knight;
import game.characters.pyromancer.Pyromancer;
import game.characters.rogue.Rogue;
import game.characters.wizard.Wizard;

public class DeflectSkillHandler implements Visitor {
    public final boolean visit(final Wizard w) {
        return false;
    }

    public final boolean visit(final Rogue r) {
        return true;
    }

    public final boolean visit(final Knight k) {
        return true;
    }

    public final boolean visit(final Pyromancer p) {
        return true;
    }
}
