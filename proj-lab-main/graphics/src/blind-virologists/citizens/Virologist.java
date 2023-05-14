package citizens;

import effects.BearDance;
import effects.Effect;
import items.*;
import map.*;

import java.util.ArrayList;

/**
 * Egy Citizen leszarmazott,
 * A virologus a jatekos vagy AI altal iranyitott
 *
 * @author Barta Daniel
 * @since 2022-03-26
 */
public class Virologist extends Citizen {
    private ArrayList<Equipment> equipments = new ArrayList<>();
    private ArrayList<Equipment> activeEquipments = new ArrayList<>();
    private ArrayList<Material> materials = new ArrayList<>();
    private ArrayList<Code> codes = new ArrayList<>();
    private ArrayList<Agent> agents = new ArrayList<>();
    private int neededAmino = -1;
    private int neededNucleo = -1;


    public Virologist() {

    }

    /**
     * A Virologist konstruktora
     *
     * @param start kezdomezo
     */
    public Virologist(Field start) {
        super(start);
    }

    /**
     * Visitor pattern resze, ha a myField laboratory
     *
     * @param lab sajat mezo
     */
    @Override
    public void visit(Laboratory lab) {
        Code code = lab.readCode();
        if (!codes.contains(code)) {
            codes.add(code);
        }
    }

    /**
     * Visitor pattern resze, ha a myField warehouse
     *
     * @param warehouse sajat mezo
     */
    @Override
    public void visit(Warehouse warehouse) {
        Material felvett = warehouse.collectMaterial();
        if (felvett != null) {
            materials.add(felvett);
        }
    }

    /**
     * Visitor pattern resze, ha a myField Empty
     *
     * @param empty sajat mezo
     */
    @Override
    public void visit(Empty empty) {
        Equipment equipment = empty.pickUpEquipment();
        if (equipment != null) {
            equipments.add(equipment);
        }

    }

    /**
     * Visitor pattern resze, ha a myField Shelter
     *
     * @param shelter sajat mezo
     */
    @Override
    public void visit(Shelter shelter) {
        Equipment equipment = shelter.pickUpEquipment();
        if (equipment != null) {
            equipments.add(equipment);
        }
    }

    @Override
    public void visit(InfectedLaboratory infectedLaboratory) {
        Code code = infectedLaboratory.readCode();
        if (!codes.contains(code)) {
            codes.add(code);
        }
        this.addEffect(new BearDance(1,"b99"));
    }


    /**
     * A jatekos altal vezerlet bemenet hivja meg
     */
    public void equip() {

    }

    /**
     * Aktiv felszereleseket allitja be
     *
     * @param equipment az aktiv felszereles
     */
    public void equip(Equipment equipment) {
        if (activeEquipments.size() < 3) {
            if (equipment != null) {
                activeEquipments.add(equipment);
                equipments.remove(equipment);
                Effect effect = equipment.use(this);
                if (effect != null) {
                    effects.add(effect);
                }
            }
        }
    }


    /**
     * A jatekos altal vezerlet bemenet hivja meg
     * Az aktiv felszerelesek kozul kivalaszt egyet a felhasznalo es azt atteszi a bag-be
     */
    public void unequip() {

    }

    /**
     * Az aktiv felszerelesekbol leveszi a parameterkent kapottat
     *
     * @param equipment a virologus aktiv felszerelese, amit le kell venni
     */
    public void unequip(Equipment equipment) {
        if (equipment != null) {
            equipments.add(equipment);
            activeEquipments.remove(equipment);

            Effect effect = equipment.use(this);
            if (effect != null) {
                effects.remove(effect);
            }
        }
    }

    /**
     * A jatekos altal vezerlet bemenet hivja meg
     * Kivalaszt egy felszerelest es azt eldobja
     */
    public void dropEquipment() {

    }

    /**
     * Torli a parametere kent kapott felszerelest es leteszi a mezore
     *
     * @param equipment a torolni kivant felszereles
     */
    public void drop(Equipment equipment) {
        equipments.remove(equipment);
        currentField.dropEquipment(equipment);
    }

    public void addEquipment(Equipment equipment) {
        if (equipment != null) {
            equipments.add(equipment);
        }
    }

    public void addEquipment(ArrayList<Equipment> equipmentList) {
        if (equipmentList != null) {
            for (Equipment item : equipmentList) {
                addEquipment(item);
            }
        }
    }

    public void removeEquipment(Equipment equipment) {
        activeEquipments.remove(equipment);
    }

    /**
     * A jatekos altal vezerlet bemenet hivja meg
     */
    public void craft() {

    }

    /**
     * Letrehoz a virologus anyagkeszletebol egy uj agenst
     *
     * @param code az agans kodja
     */
    public void craft(Code code) {
        neededAmino = code.getAminoCost();
        neededNucleo = code.getNucleoCost();
        ArrayList<Material> used = new ArrayList<>();
        for (Material item : materials) {
            int a = neededAmino + neededNucleo;
            item.prepareForCraft(this);     //a material csokkenti a ket int-et
            if (a != (neededAmino + neededNucleo)) {   //az adott materialt hasznaljuk
                used.add(item);
            }
        }
        if ((neededAmino + neededNucleo) == 0) {
            Agent agent = code.getAgent();
            agents.add(agent);
            for (Material item : used) { //felhasznaltak torlese
                materials.remove(item);
            }
        }
    }

    /**
     * A jatekos altal vezerlet bemenet hivja meg
     * A felhasznalo agenst ken egy masik virologusra
     */
    public void useAgent() {

    }

    /**
     * Agens kenese
     *
     * @param target az aldozat
     * @param agent  az agnes amit felhasznal
     */
    public void useAgent(Citizen target, Agent agent) {
        Effect effect = agent.use();
        boolean attack = target.addEffect(effect);
        if (!attack) {   //ha a targetnek volt reflect-je
            this.addEffect(effect);
        }
        agents.remove(agent);
    }

    public void addMaterial(Material material) {
        if (material != null) {
            materials.add(material);
        }
    }

    public void addMaterial(ArrayList<Material> materialList) {
        if (materialList != null) {
            for (Material item : materialList) {
                addMaterial(item);
            }
        }
    }

    /**
     * Anyagkeszlet lopasa
     *
     * @return az ellophato anyagkeszlet
     */
    public ArrayList<Material> stealMaterial() {
        if (stunned) {
            ArrayList<Material> toReturn = materials;
            materials = new ArrayList<>();
            return toReturn;
        }
        return null;
    }

    /**
     * Felszereles lopas kezdemenyezese
     *
     * @return az ellophato felszerelesek
     */
    public ArrayList<Equipment> stealEquipment() {
        if (stunned) {
            ArrayList<Equipment> toReturn = equipments;
            equipments = new ArrayList<>();
            //activeEquipments = new ArrayList<>();   //mert az aktivak is letunnek
            return toReturn;
        }
        return null;
    }

    /**
     * A jatekos altal vezerlet bemenet hivja meg
     * A virologus lopast indit egy masik virologus ellen, a megszerzett felszerelest es anyagkeszletet elrakja
     */
    public void steal() {

    }

    public int getNeededAmino() {
        return neededAmino;
    }

    public void setNeededAmino(int neededAmino) {
        this.neededAmino = neededAmino;
    }

    public int getNeededNucleo() {
        return neededNucleo;
    }

    public void setNeededNucleo(int neededNucleo) {
        this.neededNucleo = neededNucleo;
    }

    public ArrayList<Code> getCodes() {
        return codes;
    }

    public void clearCodes(){
        codes = new ArrayList<>();
    }


    /**
     * Visszaadja a materialokat.
     * @return materials
     * @author Eros Pal
     */
    public ArrayList<Material> getMaterials(){
        return materials;
    }

    /**
     * Visszaadja az agenseket.
     * @return agents
     * @author Eros Pal
     */
    public ArrayList<Agent> getAgents(){
        return agents;
    }

    /**
     * Visszaadja az equipmenteket.
     * @return equipments
     * @author Eros Pal
     */
    public ArrayList<Equipment> getEquipments(){
        return equipments;
    }

    /**
     * Visszaadja az aktiv equipmenteket.
     * @return activeEquipments
     * @author Eros Pal
     */
    public ArrayList<Equipment> getActiveEquipments(){
        return activeEquipments;
    }


    @Override
    public String toString() {
        String ki = "";
        ki += "\tfi_" + currentField.getID();
        if (direction != null) {
            ki += "\n\tdi_" + direction.getID();
        }
        else{
            ki += "\n\tdi_null";
        }
        ki += "\n\tst_" + stunned;
        ki += "\n\tres_" + resistance;
        ki += "\n\tre_" + reflect;
        ki += "\n\tma_" + maxMaterial;
        ki += "\n\tref_" + reflectCount;
        ki += "\n\tneededAmino_" + neededAmino;
        ki += "\n\tneededNucleo_" + neededNucleo;

        //codes
        if (codes.size() > 0) {
            ki += "\n\tco_";
            for (Code item : codes) {
                ki += item.getID() + "+";
            }
            ki = ki.substring(0, ki.length() - 1);
        } else {
            ki += ("\n\tco_null");
        }
        //material
        if (materials.size() > 0) {
            ki += ("\n\tma_");
            for (Material item : materials) {
                ki += (item.getID() + "+");
            }
            ki = ki.substring(0, ki.length() - 1);
        } else {
            ki += ("\n\tma_null");
        }
        //agents
        if (agents.size() > 0) {
            ki += ("\n\tag_");
            for (Agent item : agents) {
                ki += (item.getID() + "+");
            }
            ki = ki.substring(0, ki.length() - 1);
        } else {
            ki += ("\n\tag_null");
        }
        //equipments
        if (equipments.size() > 0) {
            ki += ("\n\teq_");
            for (Equipment item : equipments) {
                ki += (item.getID() + "+");
            }
            ki = ki.substring(0, ki.length() - 1);
        } else {
            ki += ("\n\teq_null");
        }
        //active equipments
        if (activeEquipments.size() > 0) {
            ki += ("\n\teqa_");
            for (Equipment item : activeEquipments) {
                ki += (item.getID() + "+");
            }
            ki = ki.substring(0, ki.length() - 1);
        } else {
            ki += ("\n\teqa_null");
        }
        //effects
        if (effects.size() > 0) {
            ki += ("\n\tef_");
            for (Effect item : effects) {
                ki += (item.getID() + "+");
            }
            ki = ki.substring(0, ki.length() - 1);
        } else {
            ki += ("\n\tef_null");
        }

        return ki;
    }

    /**
     * for tests
     *
     * @param id - test id
     */
    public Virologist(String id) {
        this.ID = id;
    }

    /**
     * for tests
     *
     * @param start
     * @param id    - test id
     */
    public Virologist(Field start, String id) {
        super(start);
        this.ID = id;
    }

}






