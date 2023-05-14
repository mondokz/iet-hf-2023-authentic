package items;

import citizens.Citizen;
import effects.*;
import main.Main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * A kesztyűt megvalósító osztály.
 *
 * @author Feher Norbert
 * @since 2022-04-22
 */
public class Gloves extends Equipment {
    /**
     * A fuggveny létrehoz egy Reflect effect-et és visszaadja azt.
     */
    public Gloves(){
        try {
            this.img= ImageIO.read(new File(Main.wdPath+"/assets/equipment/gloves.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        e = new Reflect(10, this);
    }
    public Gloves(String _ID){
        try {
            this.img= ImageIO.read(new File(Main.wdPath+"/assets/equipment/gloves.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        e = new Reflect(10, this);
        setID(_ID);
    }
    public Effect use(Citizen target) {
        return e;
    }

    @Override
    public String toString(){
        return "Gloves";
    }
}
