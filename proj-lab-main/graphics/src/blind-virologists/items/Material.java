package items;

import citizens.Virologist;

/**
 * Az anyagokat megvalosito osztaly.
 *
 * @author Feher Norbert
 * @since 2022-04-22
 */
public abstract class Material {

    private String ID;
    /**
     * Megnezi, hogy van-e elegendo anyag valamilyen agens elkeszitesehez,
     * a leszarmazottakban levo fuggvenyek felulirjak
     */
    public abstract void prepareForCraft(Virologist v);
    public String getID(){
        return ID;
    }
    public void setID(String _ID){
        ID = _ID;
    }


}
