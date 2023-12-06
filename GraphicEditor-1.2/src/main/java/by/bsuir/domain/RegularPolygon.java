package by.bsuir.domain;

import by.bsuir.plugins.Point;
import by.bsuir.plugins.Polygon;
import by.bsuir.plugins.PolygonType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// class describing a regular polygon
public class RegularPolygon extends Polygon {

    private double radius;
    private Point center;
    private UUID uuid;
    public static List<String> propertyNames = new ArrayList<>();

    static {
        propertyNames.add("Radius");
        propertyNames.add("Center x");
        propertyNames.add("Center y");
        propertyNames.add("Amount of N");
    }

    public RegularPolygon (double radius, Point center, Integer n) {
        super(center, radius, n, PolygonType.REGULAR_POLYGON);
        this.uuid = UUID.randomUUID();
        this.radius = radius;
        this.center = center;
    }

    // method to get parameter values from the called form
    public static RegularPolygon constructShape(Map<String, Integer> propertiesValues) {
        return new RegularPolygon(propertiesValues.get("Radius"), new Point(propertiesValues.get("Center x"),
                propertiesValues.get("Center y")), propertiesValues.get("Amount of N"));
    }

    @Override
    public String toString() {
        return "RegularPolygon{" +
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
