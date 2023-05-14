package map;

import citizens.Visitor;
import items.Equipment;

/**
 * Az ures mezot megvalosito osztaly.
 *
 * @author Kovacs Aron, Hajos Daniel
 * @since 2022-04-21
 */
public class Empty extends Field {

    private Equipment equipment;

    /**
     * Az ures mezot letrehozo konstruktor
     *
     * @param equipment ha esetleg equipmenttel hoznank letre ures mezot
     */

    public Empty(Equipment equipment,String _ID) {
        this.equipment = equipment;
        setID(_ID);
    }

    /**
     * Az ures mezot letrehozo parameter nelkuli konstruktor
     */
    public Empty() {
        this.equipment = null;
    }

    public Empty(String _ID){
        equipment=null;
        setID(_ID);
    }

    /**
     * Az ures mezo gettere
     *
     * @return a mezon levo felszerelest adja vissza
     */
    @Override
    public Equipment getEquipment() {
        return equipment;
    }

    /**
     * Az ures mezo settere
     *
     * @param equipment beallitja az ures mezon levo felszerelest
     */
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    /**
     * Az ures mezon levo equipment felvetelet vegzo fuggveny
     *
     * @return a felvett felszerelest adja vissza
     */
    public Equipment pickUpEquipment() {
        Equipment collected = equipment;
        equipment = null;
        return collected;
    }

    /**
     * Az eldobott equipmentet eltarolja a mezon
     *
     * @param e az eldobott Equipment
     */
    @Override
    public void dropEquipment(Equipment e) {
        equipment = e;
    }

    /**
     * A mezovel interakcioba lepo visitor fogadasat vegzo fuggveny
     *
     * @param v az interakciot vegzo visitor
     */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }


    @Override
    public String toString(){
        String out= super.toString();
        out+="\teq_";
        if(equipment==null){
            out+="null";
        }else{
            out+=equipment.getID();
        }
        return out;
    }
}
