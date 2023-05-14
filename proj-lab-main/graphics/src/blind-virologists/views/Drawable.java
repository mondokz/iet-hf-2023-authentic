package views;

import map.Field;

import java.awt.*;

public interface Drawable {
    public Field getFieldToDraw();
    public Polygon getPolygonToDraw();
    public void draw(Graphics2D g);
}