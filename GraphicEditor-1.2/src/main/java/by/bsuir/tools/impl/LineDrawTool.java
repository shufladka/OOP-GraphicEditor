package by.bsuir.tools.impl;

import by.bsuir.domain.Line;
import by.bsuir.tools.ShapeDrawToolInterface;

import java.awt.*;

public class LineDrawTool implements ShapeDrawToolInterface {

    private Line line;

    public LineDrawTool(Line line) {
        this.line = line;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.draw(new java.awt.geom.Line2D.Double(line.getFirstPoint().getX(),
                line.getFirstPoint().getY(), line.getSecondPoint().getX(), line.getSecondPoint().getY()));
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
}
