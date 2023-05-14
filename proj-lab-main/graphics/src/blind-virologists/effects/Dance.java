package effects;

import citizens.Citizen;
import map.Field;

import java.util.ArrayList;
import java.util.Random;

/**
 * A kontrollalhatatlan tancot okozo virus effektjet megvalosito osztaly.
 *
 * @author Eros Pal
 * @since 2022-04-22
 */
public class Dance extends Effect {

    /**
     * Az osztaly konstruktora, letrehozza az adott peldanyt 3 meretu durationnal.
     */
    public Dance() {
        duration = 3;
        eID++;
        int i = eID;
        setID("d" + i);
    }

    /**
     * Az osztaly konstruktora, letrehozza az adott peldanyt 3 meretu durationnal.
     * @param id az id-ja az adott peldanynak.
     */
    public Dance(String id) {
        duration = 3;
        setID(id);
    }

    /**
     * Az osztaly konstruktora, letrehozza az adott peldanyt a parameter meretu durationnal.
     * @param dur ilyen hosszan tart az effekt.
     */
    public Dance(int dur) {
        duration = dur;
    }

    /**
     * Az osztaly konstruktora, letrehozza az adott peldanyt a parameter meretu durationnal.
     * @param dur ilyen hosszan tart az effekt.
     * @param id az id-ja az adott peldanynak.
     */
    public Dance(int dur, String id) {
        duration = dur;
        setID(id);
    }

    /**
     * A Dance osztaly konstruktora.
     * @param citizen akire hat az effekt.
     * @param dur ilyen hosszan tart az effekt.
     * @param id az id-je az adott peldanynak
     */
    public Dance(Citizen citizen, int dur, String id) {
        duration = dur;
        citizen.addEffect(this);
        setID(id);
    }

    /**
     * Megvaltoztatja a jatekos altal korabban beallitott lepest egy veletlenszeru szomszedos mezore.
     * @param affectedCitizen lepeset allitja at.
     */
    @Override
    public void applyEffect(Citizen affectedCitizen) {
        Random random = new Random();
        double randomNumber = random.nextDouble()*1000;
        if( randomNumber > affectedCitizen.getResistance() *10) {
            ArrayList<Field> neighborsFreeFields;
            neighborsFreeFields = affectedCitizen.getMoves();
            Random rand = new Random();
            Field randomNeighborsField = neighborsFreeFields.get(rand.nextInt(neighborsFreeFields.size()));
            affectedCitizen.setDirection(randomNeighborsField);
        }
    }

    @Override
    public String toString(){
        return "Dance";
    }
}
