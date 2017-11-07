package game.characters;

import game.characters.knight.Knight;
import game.characters.pyromancer.Pyromancer;
import game.characters.rogue.Rogue;
import game.characters.wizard.Wizard;

/**
 * [Double-dispatch].
 */
public interface Visitor {
    boolean visit(Wizard w);

    boolean visit(Rogue r);

    boolean visit(Knight k);

    boolean visit(Pyromancer p);
}
