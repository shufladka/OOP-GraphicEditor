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

public class TriangleRectangular extends Polygon {

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
    }

    public TriangleRectangular (double radius, Point center) {
        super(center, radius, 3, PolygonType.RECTANGULAR_TRIANGLE);
        this.uuid = UUID.randomUUID();
        this.color = Color.BLACK;
        this.radius = radius;
        this.center = center;
    }

    public TriangleRectangular (double radius, Point center, Color color) {
        super(center, radius, 3, PolygonType.RECTANGULAR_TRIANGLE, color);
        this.uuid = UUID.randomUUID();
        this.color = color;
        this.radius = radius;
        this.center = center;
    }

    public TriangleRectangular (double radius, Point center, ReflectionType reflectionType) {
        super(center, radius, 3, PolygonType.RECTANGULAR_TRIANGLE, reflectionType);
        this.uuid = UUID.randomUUID();
        this.color = Color.BLACK;
        this.radius = radius;
        this.center = center;
        this.reflectionType = reflectionType;
    }

    public TriangleRectangular (double radius, Point center, ReflectionType reflectionType, Color color) {
        super(center, radius, 3, PolygonType.RECTANGULAR_TRIANGLE, reflectionType, color);
        this.uuid = UUID.randomUUID();
        this.color = color;
        this.radius = radius;
        this.center = center;
        this.reflectionType = reflectionType;
    }

    // constructor of a new instance of the TriangleRectangular class created based on its properties
    public static TriangleRectangular constructShape(Map<String, Integer> propertiesValues) {
        return new TriangleRectangular(propertiesValues.get("Radius"), new Point(propertiesValues.get("Center x"), propertiesValues.get("Center y")));
    }

    @Override
    public String toString() {
        return "TriangleRectangular{" +
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