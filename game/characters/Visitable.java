package game.characters;

public interface Visitable {
    boolean accept(Visitor v);
}
