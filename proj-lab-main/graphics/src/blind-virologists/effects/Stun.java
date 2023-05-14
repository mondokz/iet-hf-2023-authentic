package effects;

import citizens.Citizen;

import java.util.Random;

/**
 * A benito effektet valositja meg. Ha ervenyben van, akkor az ido lejartaig a jatekos nem tud mozogni.
 *
 * @author Eros Pal
 * @since 2022-04-22
 */
public class Stun extends Effect {

    /**
     * A Stun osztaly konstruktora, 3 meretu durationnal.
     */
    public Stun() {
        duration = 3;
        eID++;
        int i = eID;
        setID("s" + i);
    }

    /**
     * A Stun osztaly konstruktora.
     * @param dur ilyen hosszan tart az effekt.
     */
    public Stun(int dur) {
        duration = dur;
    }

    /**
     * A Stun osztaly konstruktora, 3 meretu durationnal.
     * @param id az id-ja az adott peldanynak.
     */
    public Stun(String id) {
        duration = 3;
        setID(id);
    }

    /**
     * A Stun osztaly konstruktora.
     * @param dur ilyen hosszan tart az effekt.
     * @param id az id-ja az adott peldanynak.
     */
    public Stun(int dur, String id) {
        duration = dur;
        setID(id);
    }

    /**
     * A Stun osztaly konstruktora.
     * @param citizen akire hat az effekt.
     * @param dur ilyen hosszan tart az effekt.
     * @param id az id-ja az adott peldanynak.
     */
    public Stun(Citizen citizen, int dur, String id) {
        duration = dur;
        citizen.setStunned(true);
        citizen.addEffect(this);
        setID(id);
    }

    /**
     * Lekerdezi az aktualis mezot, majd beallitja celmezonek.
     * @param affectedCitizen ennek allitja at a celmezojet az aktualis mezore.
     */
    @Override
    public void applyEffect(Citizen affectedCitizen) {
        Random random = new Random();
        double randomNumber = random.nextDouble()*1000;
        if( randomNumber > affectedCitizen.getResistance() *10) {
            affectedCitizen.setDirection(affectedCitizen.getCurrentField());
            affectedCitizen.setStunned(duration != 0);
        }
    }

    @Override
    public String toString(){
        return "Stun";
    }
}
