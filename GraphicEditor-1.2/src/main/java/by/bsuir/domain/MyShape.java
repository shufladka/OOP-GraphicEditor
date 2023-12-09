package by.bsuir.domain;

import by.bsuir.plugins.Point;
import by.bsuir.plugins.PolygonType;
import by.bsuir.tools.ShapeDrawToolInterface;

import java.awt.*;
import java.util.UUID;

// custom interface for getting a list of drawn figures
public interface MyShape {
    ShapeDrawToolInterface getShapeListDraw();
    UUID getUUID();
    void setUUID(UUID uuid);
    double getRadius();
    void setRadius(double radius);
    Point getCenter();
    void setCenter(Point center);
    Point getFirstPoint();
    Point getSecondPoint();
    void setFirstPoint(Point firstPoint);
    void setSecondPoint(Point secondPoint);
    int getN();
    void setN(int n);
    PolygonType getPolygonType();
}
