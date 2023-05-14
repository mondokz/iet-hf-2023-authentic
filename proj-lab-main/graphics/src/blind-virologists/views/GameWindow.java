package views;

import javax.swing.text.View;
import java.awt.*;
import java.util.ArrayList;

/**
 * A jatekter kirajzolasaert felelos osztaly
 *
 * @author Eros Pal
 * @since 2022-05-12
 */
public class GameWindow {
    public ArrayList<Drawable> lowerDrawables;
    public ArrayList<Drawable> upperDrawables;

    /**
     * Konstruktor
     */
    public GameWindow(){
        lowerDrawables = new ArrayList<>();
        upperDrawables = new ArrayList<>();
    }

    /**
     * Vegigmegy es kirajzolja az osszes elemet.
     * @param g ide kell kirajzolnia.
     */
    public void update(Graphics2D g){
        for (Drawable lowerDrawable : lowerDrawables) {
            lowerDrawable.draw(g);
        }
        for (Drawable upperDrawable : upperDrawables) {
            upperDrawable.draw(g);
        }
    }

    /**
     * Hozzaad egy uj elemet az also listahoz.
     * @param view uj elem.
     */
    public void addLowerView(View view){
        lowerDrawables.add((Drawable) view);
    }
    /**
     * Hozzaad egy uj elemet a felso listahoz.
     * @param view uj elem.
     */
    public void addUpperView(View view){
        upperDrawables.add((Drawable) view);
    }
}
