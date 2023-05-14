package effects;

import citizens.Citizen;

// ki kell kommentezni, ha az osszes metodus keszen van

/**
 * A kopeny hatasat megvalosito osztaly. A tamadasok ellen 82,3%-os vedelmet nyujt.
 *
 * @author Eros Pal
 * @since 2022-04-22
 */
public class Protection extends Effect {

    /**
     * A Protection osztaly konstruktora, 3 meretu durationnal.
     */
    public Protection() {
        duration = 3;
        eID++;
        int i = eID;
        setID("p" + i);
    }

    /**
     * A Protection osztaly konstruktora.
     * @param dur ilyen hosszan tart az effekt.
     */
    public Protection(int dur) {
        duration = dur;
    }

    /**
     * A Protection osztaly konstruktora, 3 meretu durationnal.
     * @param id az id-ja az adott peldanynak.
     */
    public Protection(String id) {
        duration = 3;
        setID(id);
    }

    /**
     * A Protection osztaly konstruktora.
     * @param dur ilyen hosszan tart az effekt.
     * @param id az id-ja az adott peldanynak.
     */
    public Protection(int dur, String id) {
        duration = dur;
        setID(id);
    }

    /**
     * A Protection osztaly konstruktora.
     * @param citizen akire hat az effekt.
     * @param dur ilyen hosszan tart az effekt.
     * @param id az id-ja az adott peldanynak.
     */
    public Protection(Citizen citizen, int dur, String id) {
        duration = dur;
        citizen.addEffect(this);
        setID(id);
    }

    /**
     * A Citizen resistance tagvaltozojat 82.3-ra allitja.
     * @param affectedCitizen resistance tagvaltozojat allitja at.
     */
    @Override
    public void applyEffect(Citizen affectedCitizen) {
        if (duration == 0){
            if(affectedCitizen.getResistance() == 82.3) {
                affectedCitizen.setResistance(0);
            }
        }else {
            affectedCitizen.setResistance(82.3);
        }
    }

    @Override
    public String toString(){
        return "Protection";
    }
}
