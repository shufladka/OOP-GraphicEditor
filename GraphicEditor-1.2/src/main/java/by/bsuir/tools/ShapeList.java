package by.bsuir.tools;

import by.bsuir.plugins.Point;
import by.bsuir.plugins.PolygonType;
import by.bsuir.tools.impl.ShapeDrawTool;
import by.bsuir.domain.MyShape;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

// class that implements creating a list of shapes
public class ShapeList implements MyShape {

    private List<MyShape> myShapeList;
    private ShapeDrawTool shapeListDrawImpl;

    public ShapeList() {
        this(new ArrayList<>());
    }

    public ShapeList(List<MyShape> shapeList) {
        this.myShapeList = shapeList;
        shapeListDrawImpl = new ShapeDrawTool(this);
    }

    public List<MyShape> getShapeList() {
        return myShapeList;
    }

    public void setShapeList(List<MyShape> shapeList) {
        this.myShapeList = shapeList;
    }

    public void add(MyShape myShape) {
        myShapeList.add(myShape);
    }

    public void remove(int index) {
        myShapeList.remove(index);
    }

    public int size() {
        return myShapeList.size();
    }

    @Override
    public ShapeDrawToolInterface getShapeListDraw() {
        return shapeListDrawImpl;
    }

    @Override
    public String toString() {
        return "ShapeList{" +
                "myShapeList=" + myShapeList +
                ", shapeListDrawImpl=" + shapeListDrawImpl +
                '}';
    }

    @Override
    public UUID getUUID() {
        return null;
    }

    public void setUUID(UUID uuid) {
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
}
