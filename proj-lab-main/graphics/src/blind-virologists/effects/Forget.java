package effects;

import citizens.Citizen;
import citizens.Virologist;

import java.util.Random;

/**
 * A felejto virus altal kifejtett hatas, a megfertozott virologus az altala osszegyujtött genetikai kodokat elfelejti.
 *
 * @author Eros Pal
 * @since 2022-04-22
 */
public class Forget extends Effect {

    /**
     * A Forget osztaly konstruktora.
     */
    public Forget() {
        duration = 1;
        eID++;
        int i = eID;
        setID("f" + i);
    }

    /**
     * A Forget osztaly konstruktora.
     * @param id az id-ja az adott peldanynak.
     */
    public Forget(String id) {
        duration = 1;
        setID(id);
    }

    /**
     * Kitorli a virologus osszes ismert genetikai kodjat.
     * @param affectedCitizen felejti el a kodokat.
     */
    @Override
    public void applyEffect(Citizen affectedCitizen) {
        ((Virologist)affectedCitizen).clearCodes();
    }

    @Override
    public String toString(){
        return "Forget";
    }
}
