package items;


/**
 * A kontrollalhatatlan tancot okozo virus effektjet megvalosito osztaly.
 *
 * @author Feher Norbert
 * @since 2022-03-26
 */
public class Code {
    protected Agent agent;
    protected int nucleoCost;
    protected int aminoCost;
    private String ID;


    /**
     * Konstruktor. A fuggveny letrehoz egy kodot, a parameterek szerint.
     *
     * @param agent      a kodban tarolt agens
     * @param nucleoCost a kod nukleotid ara
     * @param aminoCost  a kod aminosav ara
     */
    public Code(Agent agent, int nucleoCost, int aminoCost) { //new code(new agent(mew effect))
        this.agent = agent;
        this.nucleoCost = nucleoCost;
        this.aminoCost = aminoCost;
    }

    /**
     * Konstruktor. A fuggveny letrehoz egy kodot, a parameterek szerint.
     *
     * @param agent      a kodban tarolt agens
     * @param nucleoCost a kod nukleotid ara
     * @param aminoCost  a kod aminosav ara
     * @param ID         a kod azonositoja
     */
    public Code(Agent agent, int nucleoCost, int aminoCost, String ID) { //new code(new agent(mew effect))
        this.agent = agent;
        this.nucleoCost = nucleoCost;
        this.aminoCost = aminoCost;
        this.ID = ID;
    }

    /**
     * Konstruktor. A fuggveny letrehoz egy kodot, a parameter szerinti ID-val.
     *
     * @param ID a kod azonositoja
     */
    public Code(String ID) {
        this.agent = null;
        this.nucleoCost = 0;
        this.aminoCost = 0;
        this.ID = ID;
    }

    /**
     * Konstruktor. A fuggveny letrehoz egy kodot, a parameterek nelkul a tesztesetekhez.
     */
    public Code() {
        this.agent = null;
        this.nucleoCost = 0;
        this.aminoCost = 0;
    }

    /**
     * Getter. A fuggveny visszaadja a magaban eltarolt agens egy masolatát.
     */
    public Agent getAgent() {
        return agent;
    }

    /**
     * Getter. A fuggveny visszaadja a magaban eltarolt agens nukleotid arat.
     */
    public int getNucleoCost() {
        return nucleoCost;
    }

    /**
     * Getter. A fuggveny visszaadja a magaban eltarolt agens aminosav arat.
     */
    public int getAminoCost() {
        return aminoCost;
    }

    /**
     * Getter. A fuggveny visszaadja a magaban eltarolt agens azonositojat.
     */
    public String getID() {
        return ID;
    }

    /**
     * Setter. A fuggveny beallitja az agens azonositojat.
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * A fuggveny segitsegevel tortenik az objektum azonositasa a tesztesetekhez
     *
     * @return out az objektum azonositoja
     */
    @Override
    public String toString() {
        return "Code - " + agent.effect.toString();
    }
}
