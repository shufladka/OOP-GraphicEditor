package by.bsuir.plugins;

import by.bsuir.domain.MyShape;
import by.bsuir.tools.ShapeDrawToolInterface;
import by.bsuir.tools.impl.PolygonDrawTool;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// class for creating regular polygon and triangles
public class Polygon implements MyShape {

    private double radius;
    private Point center;
    private int n;
    private PolygonType polygonType;
    private List<Point> pointsList;
    private ShapeDrawToolInterface shapeDrawTool;

    public Polygon(Point center, double radius, int n, PolygonType polygonType) {
        this();
        this.radius = radius;
        this.center = center;
        this.n = n;
        this.polygonType = polygonType;
        polygonPoints(this.center, this.radius, this.n, this.polygonType);
    }

    public Polygon() {
        this(new ArrayList<>());
    }

    public Polygon(List<Point> pointsList) {
        this.pointsList = pointsList;
        shapeDrawTool = new PolygonDrawTool(this);
    }

    // points to create a regular polygon
    private void polygonPoints(Point center, double radius, int n, PolygonType polygonType) {

        // processing shapes that occupy the entire area of the circle
        if (polygonType.equals(PolygonType.ISOSCELES_TRIANGLE) || polygonType.equals(PolygonType.REGULAR_POLYGON)) {

            // adjusting the radius value
            radius /= 2;

            double angle = 2 * Math.PI / n;

            for (int i = 0; i < n; i++) {

                Point myPoint = new Point();

                double rotatedAngle = 0;

                rotatedAngle = 3 * Math.PI / 2;

                double x = center.getX() + radius * Math.cos(angle * i + rotatedAngle);
                double y = center.getY() + radius * Math.sin(angle * i + rotatedAngle);

                myPoint.setX(x);
                myPoint.setY(y);

                pointsList.add(myPoint);
            }
        }
        if (polygonType.equals(PolygonType.RECTANGULAR_TRIANGLE)) {

            // create a right triangle consisting of three vertices
            Point vertex1 = new Point(center.getX(), center.getY() + radius); // vertex lying on the Y axis
            Point vertex2 = new Point(center.getX() + radius, center.getY()); // vertex lying on the X axis
            Point vertex3 = new Point(center.getX(), center.getY()); // vertex of right angle

            // add vertices to the points list
            pointsList.add(vertex1);
            pointsList.add(vertex2);
            pointsList.add(vertex3);

            double radians = Math.toRadians(180);

            // find the center of the figure
            double centerX = (vertex1.getX() + vertex2.getX() + vertex3.getX()) / 3.0;
            double centerY = (vertex1.getY() + vertex2.getY() + vertex3.getY()) / 3.0;

            // apply reflection and rotation to all vertices
            for (Point point : pointsList) {

                // rotate
                double rotatedX = centerX + (point.getX() - centerX) * Math.cos(radians) - (point.getY() - centerY) * Math.sin(radians);
                double rotatedY = centerY + (point.getX() - centerX) * Math.sin(radians) + (point.getY() - centerY) * Math.cos(radians);

                point.setX(rotatedX);
                point.setY(rotatedY);
            }
        }
    }

    @Override
    public String toString() {
        return "Polygon{" +
                ", radius=" + radius +
                ", center=" + center +
                ", n=" + n +
                ", polygonType=" + polygonType +
                '}';
    }

    public List<Point> getPointsList() {
        return pointsList;
    }

    public void setPointsList(List<Point> pointsList) {
        this.pointsList = pointsList;
    }

    @Override
    public ShapeDrawToolInterface getShapeListDraw() {
        return shapeDrawTool;
    }

    @Override
    public UUID getUUID() {
        return null;
    }

    public void setUUID(UUID uuid) {
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

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public PolygonType getPolygonType() {
        return polygonType;
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
}