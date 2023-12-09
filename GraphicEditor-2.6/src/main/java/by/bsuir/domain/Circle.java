package by.bsuir.domain;

import by.bsuir.plugins.Point;
import by.bsuir.plugins.PolygonType;
import by.bsuir.plugins.ReflectionType;
import by.bsuir.tools.ShapeDrawToolInterface;
import by.bsuir.tools.impl.CircleDrawTool;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Circle implements MyShape {

    private Color color;
    private ReflectionType reflectionType = ReflectionType.NONE;
    private UUID uuid;
    private double radius;
    private Point center;
    private ShapeDrawToolInterface shapeDrawToolInterface;
    public static List<String> propertyNames = new ArrayList<>();

    static {
        propertyNames.add("Radius");
        propertyNames.add("Center x");
        propertyNames.add("Center y");
    }

    public Circle() {
        this(1, new Point());
    }

    public Circle(double radius, Point center) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius should be positive.");
        }
        this.uuid = UUID.randomUUID();
        this.color = Color.BLACK;
        this.radius = radius;
        this.center = center;
        shapeDrawToolInterface = new CircleDrawTool(this);
    }

    public Circle(double radius, Point center, Color color) {
        if (radius < 0) {
            throw new IllegalArgumentException("Radius should be positive.");
        }
        this.uuid = UUID.randomUUID();
        this.color = color;
        this.radius = radius;
        this.center = center;
        shapeDrawToolInterface = new CircleDrawTool(this);
    }

    // constructor of a new instance of the Circle class created based on its properties
    public static Circle constructShape(Map<String, Integer> propertiesValues) {
        return new Circle(propertiesValues.get("Radius"), new Point(propertiesValues.get("Center x"), propertiesValues.get("Center y")));
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
    public ShapeDrawToolInterface getShapeListDraw() {
        return shapeDrawToolInterface;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public ReflectionType getReflectionType() {
        return reflectionType;
    }

    @Override
    public void setReflectionType(ReflectionType reflectionType) {
        this.reflectionType = reflectionType;
    }

    @Override
    public Point getFirstPoint() {
        return null;
    }

    @Override
    public void setFirstPoint(Point firstPoint) {

    }

    @Override
    public Point getSecondPoint() {
        return null;
    }

    @Override
    public void setSecondPoint(Point secondPoint) {

    }

    @Override
    public int getN() {
        return 0;
    }

    @Override
    public void setN(int n) {

    }

    @Override
    public PolygonType getPolygonType() {
        return null;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "uuid=" + uuid +
                ", radius=" + radius +
                ", center=" + center +
                '}';
    }
}