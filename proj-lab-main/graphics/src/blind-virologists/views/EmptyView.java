package views;

import map.Empty;
import map.Field;

import java.awt.*;
import java.util.ArrayList;

public class EmptyView extends FieldView{
    Color color= Color.GREEN;

    public EmptyView(Field fieldToDraw, Polygon polygonToDraw) {
        super(fieldToDraw, polygonToDraw);
    }


    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        super.draw(g);
    }
}
