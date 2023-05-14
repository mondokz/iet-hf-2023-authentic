package citizens;

import map.*;

/**
 * A Visitor pattern Visitor resze
 *
 * @author Barta Daniel
 * @since 2022-03-26
 */
public interface Visitor {
    /**
     * Overload visit fuggveny
     *
     * @param lab
     */
    public void visit(Laboratory lab);

    /**
     * Overload visit fuggveny
     *
     * @param warehouse
     */
    public void visit(Warehouse warehouse);

    /**
     * Overload visit fuggveny
     *
     * @param empty
     */
    public void visit(Empty empty);

    /**
     * Overload visit fuggveny
     *
     * @param shelter
     */
    public void visit(Shelter shelter);

    /**
     * Overload visit fuggveny
     *
     * @param infectedLaboratory
     */
    public void visit(InfectedLaboratory infectedLaboratory);
}
