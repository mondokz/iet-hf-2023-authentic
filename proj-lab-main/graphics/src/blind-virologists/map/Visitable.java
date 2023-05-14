package map;

import citizens.Visitor;

/**
 * A visitor patternhez tartozo interface
 *
 * @author Hajos Daniel
 * @since 2022-03-26
 */
public interface Visitable {
    /**
     * Elfogadja a parameterkent kapott Visitort
     *
     * @param v az elfogadando Visitor
     */
    public void accept(Visitor v);
}
