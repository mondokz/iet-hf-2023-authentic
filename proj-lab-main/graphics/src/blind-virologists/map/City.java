package map;

import citizens.Citizen;
import citizens.Virologist;
import effects.Effect;
import items.*;
import main.Main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * A jatek vezerleset megvalosito osztaly
 *
 * @author Kovacs Aron
 * @since 2022-04-22
 */
public class City {
    private ArrayList<Citizen> players;
    private ArrayList<Field> fields;
    private int codeCount;

    /**
     * A City osztaly konstruktora
     */
    public City() {
        players = new ArrayList<>();
        fields = new ArrayList<>();
    }

    /**
     * A City osztalyhoz tartozo parameteres konstruktor
     *
     * @param players lista a jatekosokrol
     * @param fields  lista a mezokrol
     * @param codes   a kodok max szama
     */
    public City(ArrayList<Citizen> players, ArrayList<Field> fields, int codes) {
        this.players = players;
        this.fields = fields;
    }

    /**
     * Visszaadja a jatekosok listajat
     */
    public ArrayList<Citizen> getPlayers() {
        return players;
    }

    /**
     * Beallitja egy jatekos listat a cityhez
     *
     * @param players beallitja a jatekos listat
     */
    public void setPlayers(ArrayList<Citizen> players) {
        this.players = players;
    }

    /**
     * Visszaadja a city field listajat
     */
    public ArrayList<Field> getFields() {
        return fields;
    }

    /**
     * Beallit egy field listat a cityhez
     *
     * @param fields beallitani kivant field lista
     */
    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    /**
     * Beallitja a kodok maximalis szamat
     *
     * @param codes a kodok max szama
     */
    public void setCodeCount(int codes) {
        this.codeCount = codes;
    }

    /**
     * Visszaadja a kodok maximalis szamat
     */
    public int getCodeCount() {
        return codeCount;
    }

    /**
     * Hozzaad egy jatekost a city listajahoz
     *
     * @param e a hozzaadni kivant jatekos
     */
    public void addPlayer(Citizen e) {
        players.add(e);
    }

    /**
     * Hozzaad egy mezot a city listajahoz
     *
     * @param f a hozzaadni kivant mezo
     */
    public void addField(Field f) {
        fields.add(f);
    }

    /**
     * Letrehozza palya mezoit, a rajtuk talalhato targyakat es a jatekosokat
     */
    public void generateMap() {

        System.out.println("Hany virologus legyen a palyan?\n");
        Scanner sc = new Scanner(System.in);
        int playerCount;
        playerCount = sc.nextInt();

        System.out.println("Mekkora legyen a palya?\n");
        Scanner sc2 = new Scanner(System.in);
        int fieldCount;
        fieldCount = sc2.nextInt();

        for (int i = 0; i < playerCount - 1; i++) {
            players.add(new Virologist());
        }

        for (int i = 0; i < fieldCount - 1; i++) {
            fields.add(new Empty());
        }

        Shelter s1 = new Shelter();
        fields.add(s1);
        s1.setEquipment(new Cape());

        Shelter s2 = new Shelter();
        fields.add(s2);
        s2.setEquipment(new Gloves());

        Warehouse w1 = new Warehouse();
        fields.add(w1);
        w1.setMaterial(new Aminoacid());

        Warehouse w2 = new Warehouse();
        fields.add(w2);
        w2.setMaterial(new Aminoacid());

        Laboratory l1 = new Laboratory();
        fields.add(l1);
        l1.setCode(new Code(new Vaccine(), 3, 3));

        Laboratory l2 = new Laboratory();
        fields.add(l2);
        l2.setCode(new Code(new Vaccine(), 3, 3));

        InfectedLaboratory il1 = new InfectedLaboratory();
        fields.add(il1);
        il1.setCode(new Code(new Virus(), 3, 2));

        InfectedLaboratory il2 = new InfectedLaboratory();
        fields.add(il2);
        il2.setCode(new Code(new Virus(), 3, 2));


        Random random = new Random();
        for (int i = 0; i < playerCount - 1; i++) {
            boolean found = false;
            while (!found) {
                Field temp = fields.get(random.nextInt(fieldCount - 1));
                if (temp.getCitizen() == null) {
                    players.get(i).setCurrentField(temp);
                    players.get(i).getCurrentField().setCitizen(players.get(i));
                    found = true;
                }
            }
        }


        Random random2 = new Random();
        for (int i = 0; i < fieldCount - 1; i++) {
            int temp = random2.nextInt(6 - 3 + 1) + 3;
            for (int x = 0; x < temp - 1; x++) {
                boolean foundN = false;
                while (!foundN) {
                    Field temp2 = fields.get(random.nextInt(fieldCount - 1));
                    if (fields.get(i).getNeighbors().contains(temp2) != true) {
                        fields.get(i).addNeighbor(temp2);
                        foundN = true;
                    }
                }
            }
        }


    }

    /**
     * Kiirja a tesztesetekhez szukseges informaciokat az adott objektumrol
     */
    /*
    public String toString(){
    }
    */

    /**
     * Elinditja a jatekot
     * @param window az ablak, ahol fut a jatek.
     */
    public void startGame(JFrame window) {
        generateMap();
        nextRound(window);
    }

    /**
     * Befejezi a jatekot
     * @param window az ablak, ahol fut a jatek.
     *
     * @author Eros Pal
     */
    public void endGame(JFrame window) {
        Icon icon = new ImageIcon(Main.wdPath+"/assets/menu/cup.png");
        JOptionPane optionPane = new JOptionPane("Victory!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, icon);
        JDialog jDilaog = optionPane.createDialog("Game over");
        jDilaog.setVisible(true);
        window.dispose();
    }

    /**
     * Uj kor kezdodik, veget er a jatek vagy frissulnek a virologusok allapotai
     * @param window az ablak, ahol fut a jatek
     */
    public void nextRound(JFrame window) {
        for (Citizen v : players) {
            v.nextRound();
            if (((Virologist) v).getCodes().size() == codeCount) {
                endGame(window);
            }
        }
    }

    /**
     * Noveli a jatekban talalhato kodok szamat
     */
    public void increaseCodeCount() {
        codeCount++;
    }
}
