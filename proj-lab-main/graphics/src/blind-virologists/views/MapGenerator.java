package views;

import citizens.Virologist;
import effects.*;
import items.*;
import map.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/*
    ------->X
    |
    |
    |
    V
    Y
 */


public class MapGenerator {

    public static class Coordinates {
        public int x, y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * alap palya eltolas
     */
    public static final int BASE_OFFSET_X = 30;
    public static final int BASE_OFFSET_Y = 11;

    /**
     * uj csucspontok maximalis eltolasa
     */
    public static final int MAX_VERTEX_SHIFT_X = 10;
    public static final int MAX_VERTEX_SHIFT_Y = 10;

    /**
     * egy mezo merete
     */
    public static final int TILE_SIZE_PX = 110;

    /**
     * mezok szama
     */
    public static final int MAP_SIZE_X = 9;
    public static final int MAP_SIZE_Y = 5;

    /**
     * palya meret pixelben
     */
    public static final int MAP_WIDTH_PX = MAP_SIZE_X * TILE_SIZE_PX;
    public static final int MAP_HEIGHT_PX = MAP_SIZE_Y * TILE_SIZE_PX;

    private ArrayList<Polygon> lowerLayer = new ArrayList<>();
    private ArrayList<Polygon> upperLayer = new ArrayList<>();

    public ArrayList<Drawable> getFieldViews() {
        return fieldViews;
    }

    private ArrayList<Drawable> fieldViews = new ArrayList<>();

    /**
     * Legeneralja a palyat, a mezoket es hozzajuk tartozo view-kat
     */
    public City generateMap() {
        //generate all tiles
        generateAllPolygons();

        City city = new City();

        Random r = new Random();
        int lowerIndex = 0;
        int upperIndex = 0;
        for (int i = 0; i < MAP_SIZE_Y; i++) {
            for (int j = 0; j < MAP_SIZE_X; j++) {
                Polygon polygon;
                if ((i * MAP_SIZE_X + j) % 2 == 0) {
                    polygon = lowerLayer.get(lowerIndex);
                    lowerIndex++;
                } else {
                    polygon = upperLayer.get(upperIndex);
                    upperIndex++;
                }

                //Emptyk nagyobb valoszinuseggel generalodnak
                int nextTileType = r.nextInt(7);
                switch (nextTileType) {

                    case 0://Shelter

                        int equipmentType = r.nextInt(7);
                        Equipment e = null;

                        switch (equipmentType) {
                            case 0:
                                e = new Axe();
                                break;
                            case 1:
                            case 2:
                                e = new Bag();
                                break;
                            case 3:
                            case 4:
                                e = new Cape();
                                break;
                            case 5:
                            case 6:
                                e = new Gloves();
                                break;
                        }

                        Shelter shelter = new Shelter(e, "");
                        ShelterView shelterView = new ShelterView(shelter, polygon);
                        fieldViews.add(shelterView);
                        city.addField(shelter);
                        break;

                    case 1://Warehouse

                        int materialType = r.nextInt(2);
                        Material m = null;

                        if (materialType == 0) {
                            m = new Nucleotide();
                        } else {
                            m = new Aminoacid();
                        }

                        Warehouse warehouse = new Warehouse(m, "");
                        WarehouseView warehouseView = new WarehouseView(warehouse, polygon);
                        fieldViews.add(warehouseView);
                        city.addField(warehouse);
                        break;

                    case 2://Laboratory

                        int agentType = r.nextInt(2);
                        int effectType = r.nextInt(3);
                        int aminoCost = r.nextInt(2);
                        int nucleoCost = r.nextInt(2);
                        Agent a = null;
                        Effect ef = null;
                        if (agentType == 0) {
                            switch (effectType) {
                                case 0:
                                    ef = new Dance();
                                    break;
                                case 1:
                                    ef = new Stun();
                                    break;
                                case 2:
                                    ef = new Forget();
                            }
                            a = new Virus(ef);
                        } else {
                            a = new Vaccine(new Protection());
                        }
                        Code c = new Code(a, nucleoCost, aminoCost);
                        Laboratory laboratory = new Laboratory(c, "");
                        LaboratoryView laboratoryView = new LaboratoryView(laboratory, polygon);
                        fieldViews.add(laboratoryView);
                        city.addField(laboratory);
                        city.increaseCodeCount();
                        break;

                    case 3://InfectedLaboratory

                        agentType = r.nextInt(2);
                        effectType = r.nextInt(3);
                        aminoCost = r.nextInt(2);
                        nucleoCost = r.nextInt(2);
                        a = null;
                        ef = null;
                        if (agentType == 0) {
                            switch (effectType) {
                                case 0:
                                    ef = new Dance();
                                    break;
                                case 1:
                                    ef = new Stun();
                                    break;
                                case 2:
                                    ef = new Forget();
                            }
                            a = new Virus(ef);
                        } else {
                            a = new Vaccine(new Protection());
                        }
                        c = new Code(a, nucleoCost, aminoCost);
                        InfectedLaboratory infectedLaboratory = new InfectedLaboratory(c, "");
                        InfectedLaboratoryView infectedLaboratoryView = new InfectedLaboratoryView(infectedLaboratory, polygon);
                        fieldViews.add(infectedLaboratoryView);
                        city.addField(infectedLaboratory);
                        city.increaseCodeCount();
                        break;

                    default://Empty

                        Empty empty = new Empty();
                        EmptyView emptyView = new EmptyView(empty, polygon);
                        fieldViews.add(emptyView);
                        city.addField(empty);
                        break;
                }
            }
        }

        Virologist v1 = new Virologist(fieldViews.get(0).getFieldToDraw());
        fieldViews.get(0).getFieldToDraw().setCitizen(v1);
        city.addPlayer(v1);

        Virologist v2 = new Virologist(fieldViews.get(MAP_SIZE_X - 1).getFieldToDraw());
        fieldViews.get(MAP_SIZE_X - 1).getFieldToDraw().setCitizen(v2);
        city.addPlayer(v2);

        Virologist v3 = new Virologist(fieldViews.get(MAP_SIZE_X * 4).getFieldToDraw());
        fieldViews.get(MAP_SIZE_X * 4).getFieldToDraw().setCitizen(v3);
        city.addPlayer(v3);

        Virologist v4 = new Virologist(fieldViews.get(fieldViews.size() - 1).getFieldToDraw());
        fieldViews.get(fieldViews.size() - 1).getFieldToDraw().setCitizen(v4);
        city.addPlayer(v4);

        setNeighbors();

        return city;
    }

    private void setNeighbors() {
        for (int y = 0; y < MAP_SIZE_Y; y++) {
            for (int x = 0; x < MAP_SIZE_X; x++) {
                Field current = fieldViews.get(y * MAP_SIZE_X + x).getFieldToDraw();

                //up
                if (y - 1 >= 0) {
                    current.addNeighbor(fieldViews.get((y - 1) * MAP_SIZE_X + x).getFieldToDraw());
                }

                //down
                if (y + 1 < MAP_SIZE_Y) {
                    current.addNeighbor(fieldViews.get((y + 1) * MAP_SIZE_X + x).getFieldToDraw());
                }

                //left
                if (x - 1 >= 0) {
                    current.addNeighbor(fieldViews.get(y * MAP_SIZE_X + x - 1).getFieldToDraw());
                }

                //right
                if (x + 1 < MAP_SIZE_X) {
                    current.addNeighbor(fieldViews.get(y * MAP_SIZE_X + x + 1).getFieldToDraw());
                }
            }
        }
    }

    /**
     * uj kereszt alaku poligon keszitese nagyobb merettel, csucsok randomizalasa nelkul
     *
     * @param x koordinata
     * @param y koordinata
     * @return uj poligon
     */
    public Polygon createLowerPolygon(int x, int y) {
        int[] xCoords = new int[]{x, x, x + TILE_SIZE_PX, x + TILE_SIZE_PX, x + TILE_SIZE_PX + MAX_VERTEX_SHIFT_X, x + TILE_SIZE_PX + MAX_VERTEX_SHIFT_X, x + TILE_SIZE_PX, x + TILE_SIZE_PX, x, x, x - MAX_VERTEX_SHIFT_X, x - MAX_VERTEX_SHIFT_X};
        int[] yCoords = new int[]{y, y - MAX_VERTEX_SHIFT_Y, y - MAX_VERTEX_SHIFT_Y, y, y, y + TILE_SIZE_PX, y + TILE_SIZE_PX, y + TILE_SIZE_PX + MAX_VERTEX_SHIFT_Y, y + TILE_SIZE_PX + MAX_VERTEX_SHIFT_Y, y + TILE_SIZE_PX, y + TILE_SIZE_PX, y};
        return new Polygon(xCoords, yCoords, 12);
    }

    /**
     * uj poligon keszitese normal merettel, csucsok randomizalasaval
     *
     * @param x koordinata
     * @param y koordinata
     * @return uj poligon
     */
    public Polygon createUpperPolygon(int x, int y) {
        int[] xCoords = {x, x + TILE_SIZE_PX, x + TILE_SIZE_PX, x};
        int[] yCoords = {y, y, y + TILE_SIZE_PX, y + TILE_SIZE_PX};
        Polygon polygon = new Polygon(xCoords, yCoords, 4);
        var coords = generateVertices(polygon);

        return new Polygon(getXCoordinates(coords), getYCoordinates(coords), coords.size());
    }

    /**
     * a palya osszes poligonjanak legeneralasa
     */
    public void generateAllPolygons() {
        lowerLayer.clear();
        upperLayer.clear();
        for (int y = 0; y < MAP_SIZE_Y; y++) {
            int tmp = y % 2 == 0 ? 0 : 1;
            for (int x = 0; x < MAP_SIZE_X; x++) {
                if (tmp % 2 == 0) {
                    lowerLayer.add(createLowerPolygon(BASE_OFFSET_X + x * TILE_SIZE_PX, BASE_OFFSET_Y + y * TILE_SIZE_PX));
                } else {
                    upperLayer.add(createUpperPolygon(BASE_OFFSET_X + x * TILE_SIZE_PX, BASE_OFFSET_Y + y * TILE_SIZE_PX));
                }
                tmp++;

            }
        }
    }

    /**
     * az osszes poligon felrajzolasa
     *
     * @param g a cel Graphics2D
     */
    public void drawPolygons(Graphics2D g) {


        for (int i = 0; i < fieldViews.size(); i += 2) {
            fieldViews.get(i).draw(g);
        }
        for (int i = 1; i < fieldViews.size(); i += 2) {
            fieldViews.get(i).draw(g);
            g.setColor(Color.BLACK);
            var stroke = g.getStroke();
            g.setStroke(new BasicStroke(2));
            g.drawPolygon(fieldViews.get(i).getPolygonToDraw());
            g.setStroke(stroke);
        }

        var originalStroke = g.getStroke();
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(25));
        g.drawRect(MapGenerator.BASE_OFFSET_X - 4, MapGenerator.BASE_OFFSET_Y, MapGenerator.MAP_WIDTH_PX + 2, MapGenerator.MAP_HEIGHT_PX);
        g.setStroke(originalStroke);
    }

    /**
     * uj random poligon generalasa a meglevo alapjan, az eredeti oldalain uj csucsok felvetelevel es eltolassukkal
     *
     * @param p az uj poligon alapja
     * @return uj poligon
     */
    private ArrayList<Coordinates> generateVertices(Polygon p) {
        Random random = new Random();

        ArrayList<Coordinates> polyCoords = getPolyCoordinates(p);

        ArrayList<Coordinates> newCoords = new ArrayList<>();

        for (int i = 0; i < polyCoords.size(); i++) {
            Coordinates edgeBegin = polyCoords.get(i);
            Coordinates edgeEnd;
            if (i + 1 == polyCoords.size()) {
                edgeEnd = polyCoords.get(0);
            } else {
                edgeEnd = polyCoords.get(i + 1);
            }

            newCoords.add(edgeBegin);

            //oldal kihagyasa ha a az palya szele
            if ((edgeBegin.x == BASE_OFFSET_X && edgeEnd.x == BASE_OFFSET_X)
                    || (edgeBegin.x == BASE_OFFSET_X + MAP_WIDTH_PX && edgeEnd.x == BASE_OFFSET_X + MAP_WIDTH_PX)
                    || (edgeBegin.y == BASE_OFFSET_Y && edgeEnd.y == BASE_OFFSET_Y)
                    || (edgeBegin.y == BASE_OFFSET_Y + MAP_HEIGHT_PX && edgeEnd.y == BASE_OFFSET_Y + MAP_HEIGHT_PX)) {
                continue;
            }

            int addEdge = random.nextInt(3);
            if (addEdge == 0) {
                continue;
            }

            int _offsetX = random.nextInt(2 * MAX_VERTEX_SHIFT_X) - MAX_VERTEX_SHIFT_X;
            int _offsetY = random.nextInt(2 * MAX_VERTEX_SHIFT_Y) - MAX_VERTEX_SHIFT_Y;

            int newX = (edgeEnd.x - edgeBegin.x) / 2 + edgeBegin.x;
            int newY = (edgeEnd.y - edgeBegin.y) / 2 + edgeBegin.y;

            newX += _offsetX;
            newY += _offsetY;

            newCoords.add(new Coordinates(newX, newY));
        }
        return newCoords;
    }

    /**
     * poligon koordinatainak visszaadasa Coordinates listakent
     *
     * @param p poligon
     * @return lista a koordinatakkal
     */
    public static ArrayList<Coordinates> getPolyCoordinates(Polygon p) {
        ArrayList<Coordinates> out = new ArrayList<>();

        int[] xPoints = p.xpoints;
        int[] yPoints = p.ypoints;

        for (int i = 0; i < xPoints.length; i++) {
            out.add(new Coordinates(xPoints[i], yPoints[i]));
        }

        return out;
    }

    /**
     * Coordinates listabol az x koordinatak kivalogatasa es tombkent visszaadasa a poligon keszitesehez
     *
     * @param coords bemeneti lista
     * @return tomb az x koordinatakbol
     */
    public static int[] getXCoordinates(ArrayList<Coordinates> coords) {
        ArrayList<Integer> outCoords = new ArrayList<>();
        for (int i = 0; i < coords.size(); i++) {
            outCoords.add(coords.get(i).x);
        }
        return outCoords.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Coordinates listabol az y koordinatak kivalogatasa es tombkent visszaadasa a poligon keszitesehez
     *
     * @param coords bemeneti lista
     * @return tomb az y koordinatakbol
     */
    public static int[] getYCoordinates(ArrayList<Coordinates> coords) {
        ArrayList<Integer> outCoords = new ArrayList<>();
        for (int i = 0; i < coords.size(); i++) {
            outCoords.add(coords.get(i).y);
        }
        return outCoords.stream().mapToInt(i -> i).toArray();
    }

    public ArrayList<Polygon> getLowerLayer() {
        return lowerLayer;
    }

    public ArrayList<Polygon> getUpperLayer() {
        return upperLayer;
    }
}
