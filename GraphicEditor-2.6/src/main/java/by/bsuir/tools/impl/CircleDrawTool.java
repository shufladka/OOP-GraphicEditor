package by.bsuir.tools.impl;

import by.bsuir.domain.Circle;
import by.bsuir.tools.ShapeDrawToolInterface;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleDrawTool implements ShapeDrawToolInterface {

    private Circle circle;

    public CircleDrawTool(Circle circle) {
        this.circle = circle;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(circle.getColor());
        graphics2D.draw(new Ellipse2D.Double(circle.getCenter().getX(), circle.getCenter().getY(), circle.getRadius(), circle.getRadius()));
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
}
