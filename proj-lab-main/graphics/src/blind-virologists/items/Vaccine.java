package items;

import effects.Effect;

/**
 * A vakcinat megvalosito osztaly.
 *
 * @author Kovacs Aron
 * @since 2022-04-22
 */
public class Vaccine extends Agent {
    /**
     * Konstruktor. A fuggveny letrehoz egy vakcinat.
     *
     * @param effect a kivant effect
     */
    public Vaccine(Effect effect) {
        super(effect);
    }

    /**
     * Konstruktor. A fuggveny letrehoz egy vakcinat.
     */
    public Vaccine() {
    }

    /**
     * Konstruktor. A fuggveny letrehoz egy vakcinat, a parameterben megadott azonositoval
     *
     * @param ID azonosito
     */
    public Vaccine(String ID) {
        this.setID(ID);
    }

    /**
     * Konstruktor. A fuggveny letrehoz egy vakcinat.
     *
     * @param effect a kivant effect
     * @param ID     aonosito
     */
    public Vaccine(Effect effect, String ID) {
        super(effect);
        this.setID(ID);
    }

    /**
     * A fuggveny a megfelelo effect-et visszaadja.
     */
    @Override
    public Effect use() {
        return effect;
    }

    /**
     * A fuggveny segitsegevel tortenik az objektum azonositasa a tesztesetekhez
     *
     * @return out az objektum azonositoja
     */
    @Override
    public String toString() {
        return "Vaccine - " + effect.toString();
    }
}
