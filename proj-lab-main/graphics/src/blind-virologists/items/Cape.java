package items;

import citizens.Citizen;
import effects.*;
import main.Main;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * A köpenyt megvalósító osztály.
 *
 * @author Feher Norbert
 * @since 2022-04-22
 */
public class Cape extends Equipment {
    /**
     * A fuggveny letrehoz egy Protection effect-et és visszaadja azt.
     */
    public Cape(){
        try {
            this.img= ImageIO.read(new File(Main.wdPath+"/assets/equipment/cape.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        e = new Protection(10);
    }
    public Cape(String _ID){
        try {
            this.img= ImageIO.read(new File(Main.wdPath+"/assets/equipment/cape.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        e = new Protection(10);
        setID(_ID);
    }
    public Effect use(Citizen target) {
        return e;
    }

    @Override
    public String toString(){
        return "Cape";
    }
}
