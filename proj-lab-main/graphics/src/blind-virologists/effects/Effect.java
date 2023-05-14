package effects;

import citizens.Citizen;

/**
 * Az agenseken es felszereleseken keresztul megkaphato effektetek reprezentalo absztrakt osztaly.
 * Az adott korben megkapott effekt a kovetkezo korben fejti ki a hatasat.
 *
 * @author Eros Pal
 * @since 2022-04-22
 */
public abstract class Effect {
    protected int duration;
    private String ID;
    protected static int eID = 100;

    /**
     * Minden korben meghivodik, eggyel csokkenti a Duration tagvaltozot.
     * @return Ha letelik a hatásido, igazat ad vissza.
     */
    public boolean update() {
        duration--;
        return duration == 0;
    }

    /**
     * Beallitja a Duration-t.
     * @param duration hosszu ideig hat az effect.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Visszaadja a Duration-t
     */
    public int getDuration() {
        return duration;
    }

    /**
     * A parameterkent kapott Citizen tulajdonsagait modositja, mindig a korok vegen hivodnak meg.
     * @param affectedCitizen Ennek a tulajdonsagait modositja.
     */
    public abstract void applyEffect(Citizen affectedCitizen);

    /**
     * Beallítja az Id erteket.
     * @param id ez lesz az Id.
     */
    public void setID(String id){
        ID = id;
    }

    /**
     * Visszaadja az Id erteket.
     * @return az Id.
     */
    public String getID(){
        return ID;
    }

    /**
     * A kiirashoz kell
     * @return az id stringkent osszefuzve
     */
    @Override
    public String toString(){
        String out = "\tef_";
        out+=getID();
        return out;
    }

    public static void resetEID(){
        eID=100;
    }
}
