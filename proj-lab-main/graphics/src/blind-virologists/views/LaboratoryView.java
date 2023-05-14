package views;

import map.Field;
import map.Laboratory;

import java.awt.*;
import java.util.ArrayList;

public class LaboratoryView extends FieldView{
    Color color= Color.YELLOW;

    public LaboratoryView(Field fieldToDraw, Polygon polygonToDraw) {
        super(fieldToDraw, polygonToDraw);
    }


    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        super.draw(g);
    }
}
