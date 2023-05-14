package main;

import views.MenuView;

import java.io.File;

/**
 * <h1>A vilagtalan virologusok vilaga</h1>
 * <h2>Laborfeladat</h2>
 * <h3>Szoftver projekt laboratorium</h3>
 * <h3>Budapesti Muszaki es Gazdasagtudomanyi Egyetem Villamosmernoki es Informatikai kar</h3>
 *
 * @author 19 - alma
 * @author Barta Daniel Zsolt
 * @author Eros Pal
 * @author Feher Norbert Zsolt
 * @author Hajos Daniel
 * @author Kovacs Aron
 * @since 2022-05-15
 */
public class Main {

    public static final String wdPath = new File(System.getProperty("user.dir")).getParent() /*+ "/proj-lab/graphics"*/;

    public static void main(String[] args) {
        System.out.println(wdPath);
        MenuView game = new MenuView();
    }
}