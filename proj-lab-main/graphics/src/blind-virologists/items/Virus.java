package items;

import effects.*;

import java.util.Scanner;

/**
 * A virust megvalosito osztaly.
 *
 * @author Kovacs Aron
 * @since 2022-04-22
 */
public class Virus extends Agent {
    /**
     * Konstruktor. A fuggveny letrehoz egy vakcinat.
     */
    public Virus() {
    }

    /**
     * Konstruktor. A fuggveny letrehoz egy virust.
     *
     * @param effect a kivant effect
     */
    public Virus(Effect effect) {
        super(effect);
    }

    /**
     * Konstruktor. A fuggveny letrehoz egy virust, a parameterben megadott azonositoval
     *
     * @param ID azonosito
     */
    public Virus(String ID) {
        this.setID(ID);
    }

    /**
     * Konstruktor. A fuggveny letrehoz egy virust, a parameterben megadott azonositoval es effekttel
     *
     * @param effect a kivant effect
     * @param ID aonosito
     */
    public Virus(Effect effect, String ID) {
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
        return "Virus - " + effect.toString();
    }

}
