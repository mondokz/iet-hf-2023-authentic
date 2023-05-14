package items;

import citizens.Citizen;
import effects.*;

import java.awt.image.BufferedImage;

/**
 * A kontrollalhatatlan tancot okozo virus effektjet megvalosito osztaly.
 *
 * @author Feher Norbert
 * @since 2022-04-22
 */
public abstract class Equipment {
    protected BufferedImage img;

    private String ID;
    /**
     * A fuggveny létrehozza a megfelelő effect-et és visszaadja azt.
     */
    protected Effect e;

    public Equipment(BufferedImage img, Effect e) {
        this.img = img;
        this.e = e;
    }

    public Equipment() {
        this.e = null;
    }

    public abstract Effect use(Citizen target);
    public String getID(){
        return ID;
    }
    public void setID(String _ID){
        ID = _ID;
    }
    public String toString(){
        String out= "\tef_";
        if(e == null){
            out += "null";
        }
        else {
            out += e.getID();
        }
        return out;
    }

    public BufferedImage getImg() {
        return img;
    }
}