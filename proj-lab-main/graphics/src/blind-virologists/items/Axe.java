package items;

import citizens.Citizen;
import citizens.Virologist;
import effects.Effect;
import main.Main;
import map.Empty;
import map.Laboratory;
import map.Shelter;
import map.Warehouse;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.security.spec.RSAOtherPrimeInfo;

/**
 * A baltát megvalósító osztály.
 *
 * @author Feher Norbert
 * @since 2022-04-22
 */

public class Axe extends Equipment{

    private boolean used = false;

    public Axe(){
        try {
            this.img= ImageIO.read(new File(Main.wdPath+"/assets/equipment/axe.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        e = null;
    }
    public Axe(String _ID){
        try {
            this.img= ImageIO.read(new File(Main.wdPath+"/assets/equipment/axe.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        e = null;
        setID(_ID);
    }
    public boolean getUsed(){
        return used;
    }

    public Effect use(Citizen target) {

        used = true;
        return null;
    }

    @Override
    public String toString(){
        return "Axe";
    }
}
