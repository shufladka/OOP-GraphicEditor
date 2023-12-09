package by.bsuir.domain;

import by.bsuir.plugins.Point;
import by.bsuir.plugins.Polygon;
import by.bsuir.plugins.PolygonType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TriangleRectangular extends Polygon {

    private double radius;
    private Point center;
    private UUID uuid;

    public static List<String> propertyNames = new ArrayList<>();

    static {
        propertyNames.add("Radius");
        propertyNames.add("Center x");
        propertyNames.add("Center y");
    }

    public TriangleRectangular (double radius, Point center) {
        super(center, radius, 3, PolygonType.RECTANGULAR_TRIANGLE);
        this.uuid = UUID.randomUUID();
        this.radius = radius;
        this.center = center;
    }

    // constructor of a new instance of the TriangleRectangular class created based on its properties
    public static TriangleRectangular constructShape(Map<String, Integer> propertiesValues) {
        return new TriangleRectangular(propertiesValues.get("Radius"), new Point(propertiesValues.get("Center x"), propertiesValues.get("Center y")));
    }

    @Override
    public String toString() {
        return "TriangleRectangular{" +
                ", radius=" + radius +
                ", center=" + center +
                '}';
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public Point getCenter() {
        return center;
    }

    @Override
    public void setCenter(Point center) {
        this.center = center;
    }
}