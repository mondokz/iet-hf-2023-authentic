package views;

import citizens.Virologist;
import items.*;
import main.Main;
import map.City;
import map.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * A menu megjeleniteseert felelos osztaly.
 *
 * @author Eros Pal
 * @since 2022-05-09
 */
public class MenuView{
    private static MapGenerator mapGenerator;
    private City city;
    boolean generateMapBool = false;
    private JFrame window;
    private JPanel panelButton, panelProperty, panelMap, panelCodes, panelAgents, panelEquipment, panelMaterial;
    private JLabel labelNucleotide, labelAminoacid;
    private JDialog dialogCraft;
    private JDialog dialogEquip;
    private JDialog dialogUnequip;
    private JDialog dialogDrop;
    private final int buttonWidth = 150;
    private final int buttonHeight = 100;
    private final int panelWidth = 150;
    private final int panelHeight = 150;
    private int aminoacid = 0;
    private int nucleotide = 0;
    private int next = 0;
    private int counter = 0;
    private Polygon clickPolygon;
    private boolean isMove = true;
    private Field startField;
    private Virologist actualVirologist = new Virologist();
    //A map merete: 990, 550 -> 9, 5 db

    /**
     * Elinditja es kirajzolja a jatekot.
     */
    public MenuView(){
        start();

    }

    /**
     * Letrehozza a gombokat es beteszi a helyere.
     */
    public void makePanelButton(){
        panelButton = new JPanel(new FlowLayout());
        panelButton.setBackground(Color.lightGray);
        panelButton.setVisible(true);
        window.add(panelButton, BorderLayout.SOUTH);

        makeButtonInteract();
        makeButtonCraft();
        makeButtonUseAgent();
        makeButtonSteal();
        makeButtonEquip();
        makeButtonUnequip();
        makeButtonDrop();
        makeButtonNext();
    }

    /**
     * Letrehozza es beallitja az interact gombot.
     */
    public void makeButtonInteract(){
        JButton buttonInteract = new JButton("Interact");
        buttonInteract.setBackground(Color.lightGray);
        buttonInteract.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonInteract.addActionListener(actionEvent -> {
            actualVirologist.interact();
            repaintWindow();
        });
        panelButton.add(buttonInteract);
    }

    /**
     * Letrehozza es beallitja az craft gombot.
     */
    public void makeButtonCraft(){
        JButton buttonCraft = new JButton("Craft");
        buttonCraft.setBackground(Color.lightGray);
        buttonCraft.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonCraft.addActionListener(actionEvent -> {
            if (actualVirologist.getCodes().size() > 0) {
                makeDialogcraft();
                repaintWindow();
            }else{
                JOptionPane optionPane = new JOptionPane("You don't know any code!", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                JDialog jDilaog = optionPane.createDialog("You can't craft!");
                jDilaog.setVisible(true);
            }
        });
        panelButton.add(buttonCraft);
    }

    /**
     * Letrehozza es beallitja az use agent gombot.
     */
    public void makeButtonUseAgent(){
        JButton buttonUseAgent = new JButton("Use agent");
        buttonUseAgent.setBackground(Color.lightGray);
        buttonUseAgent.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonUseAgent.addActionListener(actionEvent -> {
            isMove = false;

            if (actualVirologist.getAgents().size() > 0) {
                makeDialogUseAgent();
                repaintWindow();
            }else {
                JOptionPane optionPane = new JOptionPane("You have no agent!", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                JDialog jDilaog = optionPane.createDialog("You can't use agent!");
                jDilaog.setVisible(true);
            }
        });
        panelButton.add(buttonUseAgent);
    }

    /**
     * Letrehozza es beallitja a steal gombot.
     */
    public void makeButtonSteal(){
        JButton buttonSteal = new JButton("Steal");
        buttonSteal.setBackground(Color.lightGray);
        buttonSteal.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonSteal.addActionListener(actionEvent -> {
            isMove = false;
            makeDialogSteal();
            repaintWindow();
        });
        panelButton.add(buttonSteal);
    }

    /**
     * Letrehozza es beallitja az equip gombot.
     */
    public void makeButtonEquip(){
        JButton buttonEquip = new JButton("Equip");
        buttonEquip.setBackground(Color.lightGray);
        buttonEquip.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonEquip.addActionListener(actionEvent -> {
            if (actualVirologist.getEquipments().size() > 0) {
                makeDialogEquip();
                repaintWindow();
            }else {
                JOptionPane optionPane = new JOptionPane("You have no equipment!", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                JDialog jDilaog = optionPane.createDialog("You have no equipment!");
                jDilaog.setVisible(true);
            }
        });
        panelButton.add(buttonEquip);
    }

    /**
     * Letrehozza es beallitja az unequip gombot.
     */
    public void makeButtonUnequip(){
        JButton buttonUnequip = new JButton("Unequip");
        buttonUnequip.setBackground(Color.lightGray);
        buttonUnequip.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonUnequip.addActionListener(actionEvent -> {
            if (actualVirologist.getActiveEquipments().size() > 0) {
                makeDialogUnequip();
                repaintWindow();
            }else {
                JOptionPane optionPane = new JOptionPane("You have no active equipment!", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                JDialog jDilaog = optionPane.createDialog("You have no active equipment!");
                jDilaog.setVisible(true);
            }
        });
        panelButton.add(buttonUnequip);
    }

    /**
     * Letrehozza es beallitja a drop gombot.
     */
    public void makeButtonDrop(){
        JButton buttonDrop = new JButton("Drop");
        buttonDrop.setBackground(Color.lightGray);
        buttonDrop.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonDrop.addActionListener(actionEvent -> {
            if (actualVirologist.getEquipments().size() > 0) {
                makeDialogDrop();
                repaintWindow();
            }else {
                JOptionPane optionPane = new JOptionPane("You have no equipment!", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                JDialog jDilaog = optionPane.createDialog("You have no equipment!");
                jDilaog.setVisible(true);
            }
        });
        panelButton.add(buttonDrop);
    }

    /**
     * Letrehozza es beallitja a next gombot.
     */
    public void makeButtonNext(){
        JButton buttonNext = new JButton("Next");
        buttonNext.setBackground(Color.lightGray);
        buttonNext.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        buttonNext.addActionListener(actionEvent -> {
            next();
            counter++;
            if(counter == city.getPlayers().size()) {
                city.nextRound(window);
                counter = 0;
            }
            repaintWindow();

        });
        panelButton.add(buttonNext);
    }

    /**
     * Letrehozza az oldalso panelt, ahol kiirja a jatekos tulajdonsagait
     */
    public void makePanelProperty(){
        panelProperty = new JPanel();
        panelProperty.setLayout(new BoxLayout(panelProperty, BoxLayout.Y_AXIS));
        panelProperty.setBackground(Color.lightGray);
        panelProperty.setVisible(true);
        window.add(panelProperty, BorderLayout.EAST);

        makePanelCode();
        makePanelAgents();
        makePanelEquipments();
        makePanelMaterial();
    }

    /**
     * Letrehozza es beallitja a code megjelenitot.
     */
    public void makePanelCode(){
        panelCodes = new JPanel();
        panelCodes.setLayout(new BoxLayout(panelCodes, BoxLayout.Y_AXIS));
        panelCodes.setVisible(true);
        panelCodes.setBackground(Color.lightGray);
        panelCodes.setPreferredSize(new Dimension(panelWidth, panelHeight));
        JLabel l = new JLabel("Codes");
        l.setBackground(Color.gray);
        l.setOpaque(true);
        panelCodes.add(l);
        try{
        for (int i = 0; i < actualVirologist.getCodes().size(); i++) {
            JLabel jl = new JLabel(actualVirologist.getCodes().get(i).toString());
            panelCodes.add(jl);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        panelProperty.add(panelCodes);
    }

    /**
     * Letrehozza es beallitja az agens megjelenitot.
     */
    public void makePanelAgents() {
        panelAgents = new JPanel();
        panelAgents.setLayout(new BoxLayout(panelAgents, BoxLayout.Y_AXIS));
        panelAgents.setVisible(true);
        panelAgents.setBackground(Color.lightGray);
        panelAgents.setPreferredSize(new Dimension(panelWidth, panelHeight));
        JLabel l = new JLabel("Agents");
        l.setBackground(Color.gray);
        l.setOpaque(true);
        panelAgents.add(l);
        try {
            for (int i = 0; i < actualVirologist.getAgents().size(); i++) {
                JLabel jl = new JLabel(actualVirologist.getAgents().get(i).toString());
                panelAgents.add(jl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        panelProperty.add(panelAgents);
    }

    /**
     * Letrehozza es beallitja az equipment megjelenitot.
     */
    public void makePanelEquipments(){
        panelEquipment = new JPanel();
        panelEquipment.setLayout(new BoxLayout(panelEquipment, BoxLayout.Y_AXIS));
        panelEquipment.setVisible(true);
        panelEquipment.setBackground(Color.lightGray);
        panelEquipment.setPreferredSize(new Dimension(panelWidth, panelHeight));
        JLabel l = new JLabel("Equipment");
        l.setBackground(Color.gray);
        l.setOpaque(true);
        panelEquipment.add(l);

        try {
            for (int i = 0; i < actualVirologist.getEquipments().size(); i++) {
                JLabel jl = new JLabel(actualVirologist.getEquipments().get(i).toString());
                panelEquipment.add(jl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        panelProperty.add(panelEquipment);
    }

    /**
     * Letrehozza es beallitja a material megjelenitot.
     */
    public void makePanelMaterial(){
        panelMaterial = new JPanel(new FlowLayout());
        panelMaterial.setVisible(true);
        panelMaterial.setBackground(Color.lightGray);
        panelMaterial.setPreferredSize(new Dimension(panelWidth, panelHeight));

        //Haromszogek:
        JLabel yellowTriangleLabel = new JLabel(new ImageIcon(Main.wdPath+"/assets/menu/yellow_small_triangle.jpg"));
        JLabel grayTriangleLabel = new JLabel(new ImageIcon(Main.wdPath+"/assets/menu/gray_small_triangle.jpg"));
        yellowTriangleLabel.setVisible(true);
        grayTriangleLabel.setVisible(true);
        panelMaterial.add(yellowTriangleLabel, BorderLayout.CENTER);
        panelMaterial.add(grayTriangleLabel, BorderLayout.CENTER);

        setMaterials();
        labelNucleotide = new JLabel(String.valueOf(nucleotide));
        labelAminoacid = new JLabel(String.valueOf(aminoacid));

        panelMaterial.add(labelAminoacid, BoxLayout.Y_AXIS);
        panelMaterial.add(labelNucleotide);
        panelProperty.add(panelMaterial);
    }

    /**
     * Letrehozza es beallitja a map megjelenitot.
     */
    public void makePanelMap(){
        panelMap = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                if (generateMapBool) {
                    mapGenerator.drawPolygons((Graphics2D) g);
                }
            }
        };
        panelMap.setVisible(true);
        panelMap.setBackground(Color.black);
        click();
        window.add(panelMap, BorderLayout.CENTER);
    }

    /**
     * Ujrarajzolja az ablak osszes elemet.
     */
    public void repaintWindow(){
        setMaterials();
        labelNucleotide.setText(String.valueOf(nucleotide));
        labelAminoacid.setText(String.valueOf(aminoacid));

        window.remove(panelButton);
        window.remove(panelProperty);
        window.remove(panelMap);

        makePanelButton();
        makePanelProperty();
        makePanelMap();

        panelButton.revalidate();
        panelButton.repaint();
        panelProperty.revalidate();
        panelProperty.repaint();
        panelMap.revalidate();
        panelMap.repaint();
        panelCodes.revalidate();
        panelCodes.repaint();
        panelAgents.revalidate();
        panelAgents.repaint();
        panelEquipment.revalidate();
        panelEquipment.repaint();
        panelMaterial.revalidate();
        panelMaterial.repaint();
        window.revalidate();
        window.repaint();
    }

    /**
     * Megszamolja es beallitja az alapanyagokat.
     */
    public void setMaterials(){
        aminoacid = 0;
        nucleotide = 0;
        try {
            for (int i = 0; i < actualVirologist.getMaterials().size(); i++) {
                if (Objects.equals(actualVirologist.getMaterials().get(i).toString(), "Aminoacid")) {
                    aminoacid++;
                } else {
                    nucleotide++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Letrehozza es megjeleniti a craft ablakat.
     */
    public void makeDialogcraft(){
        dialogCraft = new JDialog();
        dialogCraft.setTitle("Craft");
        dialogCraft.setSize(500, 300);
        dialogCraft.setVisible(true);
        dialogCraft.add(new JLabel("Choose a code!"), BorderLayout.NORTH);

        String[] list = new String[actualVirologist.getCodes().size()];
        for (int i = 0; i < actualVirologist.getCodes().size(); i++){
            list[i] = actualVirologist.getCodes().get(i).getAgent().getEffect().toString()+" - amino: "+actualVirologist.getCodes().get(i).getAminoCost()+", nucleo: "+actualVirologist.getCodes().get(i).getNucleoCost();
        }

        JList<String> listCraft = new JList<>(list);
        listCraft.setVisibleRowCount(3);
        listCraft.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dialogCraft.add(new JScrollPane(listCraft), BorderLayout.CENTER);
        listCraft.addListSelectionListener(
                e -> {
                    if (actualVirologist.getCodes().get(listCraft.getSelectedIndex()).getAminoCost() > aminoacid ||
                            actualVirologist.getCodes().get(listCraft.getSelectedIndex()).getNucleoCost() > nucleotide) {
                        JOptionPane optionPane = new JOptionPane("You con't have enough materials!", JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
                        JDialog jDilaog = optionPane.createDialog("You con't have enough materials!");
                        jDilaog.setVisible(true);
                    }else{
                        Code craftCode = actualVirologist.getCodes().get(listCraft.getSelectedIndex());
                        actualVirologist.craft(craftCode);
                    }
                    dialogCraft.dispose();
                    repaintWindow();
                    isMove = true;
                }
        );
    }

    /**
     * Letrehozza es megjeleniti az use agent ablakat.
     */
    public void makeDialogUseAgent(){
        JDialog dialogUseAgent = new JDialog();
        dialogUseAgent.setTitle("Use Agent");
        dialogUseAgent.setSize(500, 300);
        dialogUseAgent.setVisible(true);
        dialogUseAgent.add(new JLabel("Choose a virologist and an agent!"), BorderLayout.NORTH);


        String[] listagents = new String[actualVirologist.getAgents().size()];


        for (int i = 0; i < actualVirologist.getAgents().size(); i++){
            listagents[i] = actualVirologist.getAgents().get(i).getEffect().toString();
        }


        JList<String> listAgents = new JList<>(listagents);
        listAgents.setVisibleRowCount(10);
        listAgents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel panelDialogAgents = new JPanel();
        panelDialogAgents.add(new JScrollPane(listAgents), BorderLayout.CENTER);

        dialogUseAgent.add(panelDialogAgents, BorderLayout.WEST);

        listAgents.addListSelectionListener(
                e -> actualVirologist.useAgent(fieldToPolygon(clickPolygon).getCitizen(), actualVirologist.getAgents().get(listAgents.getSelectedIndex()))
        );

    }

    /**
     * Letrehozza es megjeleniti a steal ablakat.
     */
    public void makeDialogSteal(){

        JDialog dialogSteal = new JDialog();
        dialogSteal.setTitle("Steal");
        dialogSteal.setSize(500, 300);
        dialogSteal.setVisible(true);
        dialogSteal.add(new JLabel("Choose a virologist!"), BorderLayout.NORTH);

        JButton jButton = new JButton("Steal!");
        jButton.addActionListener(e -> {
            isMove = false;
            if (fieldToPolygon(clickPolygon).getCitizen().isStunned()) {
                actualVirologist.addEquipment(fieldToPolygon(clickPolygon).getCitizen().stealEquipment());
                actualVirologist.addMaterial(fieldToPolygon(clickPolygon).getCitizen().stealMaterial());
                isMove = true;
            }
        });



    }

    /**
     * Letrehozza es megjeleniti az equip ablakat.
     */
    public void makeDialogEquip(){
        dialogEquip = new JDialog();
        dialogEquip.setTitle("Equip");
        dialogEquip.setSize(500, 300);
        dialogEquip.setVisible(true);
        dialogEquip.add(new JLabel("Choose an equipment!"), BorderLayout.NORTH);

        String[] list = new String[actualVirologist.getEquipments().size()];
        for (int i = 0; i < actualVirologist.getEquipments().size(); i++){
            list[i] = actualVirologist.getEquipments().get(i).toString();
        }

        JList<String> listEquip = new JList<>(list);
        listEquip.setVisibleRowCount(3);
        listEquip.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dialogEquip.add(new JScrollPane(listEquip), BorderLayout.CENTER);
        listEquip.addListSelectionListener(
                e -> {
                    actualVirologist.equip(actualVirologist.getEquipments().get(listEquip.getSelectedIndex()));
                    dialogEquip.dispose();
                    repaintWindow();
                }
        );
    }

    /**
     * Letrehozza es megjeleniti az unequip ablakat.
     */
    public void makeDialogUnequip(){
        dialogUnequip = new JDialog();
        dialogUnequip.setTitle("Unequip");
        dialogUnequip.setSize(500, 300);
        dialogUnequip.setVisible(true);
        dialogUnequip.add(new JLabel("Choose an equipment!"), BorderLayout.NORTH);

        String[] list = new String[actualVirologist.getActiveEquipments().size()];
        for (int i = 0; i < actualVirologist.getActiveEquipments().size(); i++){
            list[i] = actualVirologist.getActiveEquipments().get(i).toString();
        }

        JList<String> listUnequip = new JList<>(list);
        listUnequip.setVisibleRowCount(3);
        listUnequip.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dialogUnequip.add(new JScrollPane(listUnequip), BorderLayout.CENTER);
        listUnequip.addListSelectionListener(
                e -> {
                    actualVirologist.unequip(actualVirologist.getActiveEquipments().get(listUnequip.getSelectedIndex()));
                    dialogUnequip.dispose();
                    repaintWindow();
                }
        );
    }

    /**
     * Letrehozza es megjeleniti a drop ablakat.
     */
    public void makeDialogDrop(){
        dialogDrop = new JDialog();
        dialogDrop.setTitle("Drop");
        dialogDrop.setSize(500, 300);
        dialogDrop.setVisible(true);
        dialogDrop.add(new JLabel("Choose an equipment!"), BorderLayout.NORTH);

        String[] list = new String[actualVirologist.getEquipments().size()];
        for (int i = 0; i < actualVirologist.getEquipments().size(); i++){
            list[i] = actualVirologist.getEquipments().get(i).toString();
        }

        JList<String> listDrop = new JList<>(list);
        listDrop.setVisibleRowCount(3);
        listDrop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dialogDrop.add(new JScrollPane(listDrop), BorderLayout.CENTER);
        listDrop.addListSelectionListener(
                e -> {
                    actualVirologist.drop(actualVirologist.getEquipments().get(listDrop.getSelectedIndex()));
                    dialogDrop.dispose();
                    repaintWindow();
                }
        );
    }

    /**
     * Elinditja a jatekot.
     */
    public void start(){
        JFrame frameBeginning = new JFrame();
        frameBeginning.setSize(400,400);
        frameBeginning.setTitle("The world of worldless virologists");
        frameBeginning.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frameBeginning.setResizable(false);
        JButton startButton = new JButton();
        JLabel vaccineLabel = new JLabel(new ImageIcon(Main.wdPath+"/assets/menu/vaccine.png"));
        JLabel startLabel = new JLabel("Start game!");
        startLabel.setFont(new Font("Verdana", Font.PLAIN, 60));
        startButton.add(vaccineLabel);
        startButton.add(startLabel);
        startButton.setBackground(Color.cyan);
        startButton.setPreferredSize(new Dimension(300, 300));
        startButton.addActionListener(actionEvent -> {

            generate();

            frameBeginning.setVisible(false);
            window = new JFrame();
            window.setSize(1280,720);
            window.setTitle("The world of worldless virologists");
            window.setDefaultCloseOperation(EXIT_ON_CLOSE);
            window.setResizable(false);
            actualVirologist = (Virologist) city.getPlayers().get(0);
            makePanelButton();
            makePanelProperty();
            makePanelMap();
            repaintWindow();
            window.setVisible(true);
        });
        frameBeginning.add(startButton);
        frameBeginning.setVisible(true);
    }

    /**
     * Legeneralja a palyat.
     */
    public void generate(){
        mapGenerator = new MapGenerator();
        city = mapGenerator.generateMap();

        generateMapBool = true;

        next();
        city.nextRound(window);
    }

    /**
     * Beallitja az aktualis virologust a kovetkezore.
     */
    public void next(){
        for (int i = 0; i < city.getPlayers().size(); i++){
            city.getPlayers().get(i).setColor(Color.orange);
        }
        actualVirologist = (Virologist) city.getPlayers().get(next);
        actualVirologist.setColor(Color.gray);
        startField = actualVirologist.getCurrentField();
        next++;
        if (city.getPlayers().size() == next ){
            next = 0;
        }
    }

    /**
     * Klikkeles beallitja a poligont.
     */
    public void click(){
        panelMap.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                for (var p : mapGenerator.getUpperLayer()) {
                    if (p.contains(e.getPoint())) {
                        System.out.println("click");
                        if (isMove){
                            for (int j = 0; j < actualVirologist.getCurrentField().getNeighbors().size(); j++) {
                                if (fieldToPolygon(p) == actualVirologist.getCurrentField().getNeighbors().get(j)){
                                    startField = fieldToPolygon(p);
                                    actualVirologist.setDirection(startField);
                                    System.out.println("move");
                                }
                            }
                        } else {
                            for (int j = 0; j < actualVirologist.getCurrentField().getNeighbors().size(); j++) {
                                if (fieldToPolygon(p) == actualVirologist.getCurrentField().getNeighbors().get(j)){
                                    clickPolygon = p;
                                }
                            }
                        }
                        return;
                    }
                }

                for (var p : mapGenerator.getLowerLayer()) {
                    if (p.contains(e.getPoint())) {
                        System.out.println("click");
                        if (isMove){
                            for (int j = 0; j < actualVirologist.getCurrentField().getNeighbors().size(); j++) {
                                if (fieldToPolygon(p) == actualVirologist.getCurrentField().getNeighbors().get(j)){
                                    startField = fieldToPolygon(p);
                                    actualVirologist.setDirection(startField);
                                    System.out.println("move");
                                }
                            }
                        } else {
                            for (int j = 0; j < actualVirologist.getCurrentField().getNeighbors().size(); j++) {
                                if (fieldToPolygon(p) == actualVirologist.getCurrentField().getNeighbors().get(j)){
                                    clickPolygon = p;
                                }
                            }
                        }
                        return;
                    }
                }
            }
        });
    }

    /**
     * Egy poligon alapjan megmondja, hogy melyik mezo tartozik hozza.
     * @param p poligon
     * @return a mezo, ami hozza tartozik
     */
    public Field fieldToPolygon(Polygon p){
        for (int i = 0; i < mapGenerator.getFieldViews().size(); i++){
            if (mapGenerator.getFieldViews().get(i).getPolygonToDraw() == p) {
                return mapGenerator.getFieldViews().get(i).getFieldToDraw();
            }
        }
        return null;
    }
}