package by.bsuir.domain;

import by.bsuir.plugins.Point;
import by.bsuir.plugins.PolygonType;
import by.bsuir.tools.ShapeDrawToolInterface;
import by.bsuir.tools.impl.LineDrawTool;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Line implements MyShape {

    private UUID uuid;
    private Point firstPoint;
    private Point secondPoint;
    private ShapeDrawToolInterface shapeDrawToolInterface;

    public static List<String> propertyNames = new ArrayList<>();

    static {
        propertyNames.add("Point A x");
        propertyNames.add("Point A y");
        propertyNames.add("Point B x");
        propertyNames.add("Point B y");
    }

    public Line() {
        this(new Point(), new Point());
    }

    public Line(Point firstPoint, Point secondPoint) {
        this.uuid = UUID.randomUUID();
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
        shapeDrawToolInterface = new LineDrawTool(this);
    }

    // constructor of a new instance of the Line class created based on its properties
    public static Line constructShape(Map<String, Integer> propertiesValues) {
        return new Line (new Point(propertiesValues.get("Point A x"), propertiesValues.get("Point A y")),
                new Point(propertiesValues.get("Point B x"), propertiesValues.get("Point B y")));
    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(Point firstPoint) {
        this.firstPoint = firstPoint;
    }

    public Point getSecondPoint() {
        return secondPoint;
    }

    public void setSecondPoint(Point secondPoint) {
        this.secondPoint = secondPoint;
    }

    @Override
    public String toString() {
        return "Line{" +
                ", uuid=" + uuid +
                ", firstPoint=" + firstPoint +
                ", secondPoint=" + secondPoint +
                '}';
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

    @Override
    public double getRadius() {
        return 0;
    }

    @Override
    public void setRadius(double radius) {
    }

    @Override
    public Point getCenter() {
        return null;
    }

    @Override
    public void setCenter(Point center) {
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
}
