package by.bsuir.tools.impl;

import by.bsuir.plugins.Point;
import by.bsuir.plugins.Polygon;
import by.bsuir.tools.ShapeDrawToolInterface;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.List;

public class PolygonDrawTool implements ShapeDrawToolInterface {

    private Polygon polygon;

    @Override
    public void draw(Graphics2D graphics2D) {
        List<Point> points = polygon.getPointsList();

        if (points.isEmpty()) {
            return;
        }

        Path2D path = new Path2D.Double();
        Point firstPoint = points.get(0);
        path.moveTo(firstPoint.getX(), firstPoint.getY());
        for (int i = 1; i < points.size(); i++) {
            Point point = points.get(i);
            path.lineTo(point.getX(), point.getY());
        }

        path.closePath();
        graphics2D.draw(path);
    }

    public PolygonDrawTool(Polygon polygon) {
        this.polygon = polygon;
    }

    public Polygon getPolygon2D() {
        return polygon;
    }

    public void setPolygon2D(Polygon polygon) {
        this.polygon = polygon;
    }
}
