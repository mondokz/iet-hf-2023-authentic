package items;

import citizens.Citizen;
import effects.*;
import main.Main;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * A zsákot megvalósító osztály.
 *
 * @author Feher Norbert
 * @since 2022-04-22
 */
public class Bag extends Equipment {
    /**
     * A fuggveny létrehoz egy IncreaseBag effect-et és visszaadja azt.
     */
    public Bag(){
        try {
            this.img= ImageIO.read(new File(Main.wdPath+"/assets/equipment/bag.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        e = new IncreaseBag(10);
    }
    public Bag(String _ID){
        try {
            this.img= ImageIO.read(new File(Main.wdPath+"/assets/equipment/bag.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        e = new IncreaseBag(10);
        setID(_ID);
    }
    public Effect use(Citizen target) {
        return e;
    }

    @Override
    public String toString(){
        return "Bag";
    }
}
