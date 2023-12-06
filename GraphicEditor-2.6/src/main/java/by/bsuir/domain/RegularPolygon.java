package by.bsuir.domain;

import by.bsuir.plugins.Point;
import by.bsuir.plugins.Polygon;
import by.bsuir.plugins.PolygonType;
import by.bsuir.plugins.ReflectionType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// class describing a regular polygon
public class RegularPolygon extends Polygon {

    private Color color;
    private ReflectionType reflectionType = ReflectionType.NONE;
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
        this.color = Color.BLACK;
        this.radius = radius;
        this.center = center;
    }

    public RegularPolygon (double radius, Point center, Integer n, Color color) {
        super(center, radius, n, PolygonType.REGULAR_POLYGON, color);
        this.uuid = UUID.randomUUID();
        this.color = color;
        this.radius = radius;
        this.center = center;
    }

    public RegularPolygon (double radius, Point center, Integer n, ReflectionType reflectionType) {
        super(center, radius, n, PolygonType.REGULAR_POLYGON, reflectionType);
        this.uuid = UUID.randomUUID();
        this.color = Color.BLACK;
        this.radius = radius;
        this.center = center;
        this.reflectionType = reflectionType;
    }

    public RegularPolygon (double radius, Point center, Integer n, ReflectionType reflectionType, Color color) {
        super(center, radius, n, PolygonType.REGULAR_POLYGON, reflectionType, color);
        this.uuid = UUID.randomUUID();
        this.color = color;
        this.radius = radius;
        this.center = center;
        this.reflectionType = reflectionType;
    }

    // method to get parameter values from the called form
    public static RegularPolygon constructShape(Map<String, Integer> propertiesValues) {
        return new RegularPolygon(propertiesValues.get("Radius"), new Point(propertiesValues.get("Center x"),
                propertiesValues.get("Center y")), propertiesValues.get("Amount of N"));
    }

    @Override
    public String toString() {
        return "RegularPolygon{" +
                "color=" + color +
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
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
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

    @Override
    public ReflectionType getReflectionType() {
        return reflectionType;
    }

    @Override
    public void setReflectionType(ReflectionType reflectionType) {
        this.reflectionType = reflectionType;
    }
}
