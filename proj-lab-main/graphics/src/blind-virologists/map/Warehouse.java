package map;

import citizens.Visitor;
import items.Material;

/**
 * A warehouse mezot megvalositó osztaly.
 *
 * @author Kovacs Aron, Hajos Daniel
 * @since 2022-04-21
 */
public class Warehouse extends Field {

    private Material material;

    /**
     * Az warehouse mezot letrehozo konstruktor
     *
     * @param material a warehouseban levo materialt adja meg
     */
    public Warehouse(Material material,String _ID) {
        this.material = material;
        setID(_ID);
    }

    /**
     * Az warehouse mezot letrehozo parameter nelkuli konstruktor
     */
    public Warehouse() {
        this.material = null;
    }

    public Warehouse(String _ID){
        setID(_ID);
        material=null;
    }

    /**
     * A warehouse mezo gettere
     *
     * @return material visszaadja a mezon levo anyagot
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * A warehouse mezo settere
     *
     * @param material a warehouseban levo anyagot allitja be
     */
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * Az warehouse mezon levo material felvetelet vegzo fuggveny
     *
     * @return collected a felvett anyagot adja vissza
     */
    public Material collectMaterial() {
        Material collected = this.material;
        setMaterial(null);
        return collected;
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
        if(material==null){
            out+="null";
        }else{
            out+=material.getID();
        }
        return out;
    }
}
