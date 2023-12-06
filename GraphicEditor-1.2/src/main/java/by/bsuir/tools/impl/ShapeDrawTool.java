package by.bsuir.tools.impl;

import by.bsuir.tools.ShapeDrawToolInterface;
import by.bsuir.tools.ShapeList;
import by.bsuir.domain.MyShape;

import java.awt.*;
import java.util.List;

// implementation of the interface for drawing a list of shapes
public class ShapeDrawTool implements ShapeDrawToolInterface {

    private ShapeList shapeList;

    @Override
    public void draw(Graphics2D graphics2D) {
        List<MyShape> myShapes = shapeList.getShapeList();
        for (MyShape myShape : myShapes) {
            myShape.getShapeListDraw().draw(graphics2D);
        }
    }

    public ShapeDrawTool(ShapeList shapeList) {
        this.shapeList = shapeList;
    }

    public ShapeList getShapeList() {
        return shapeList;
    }

    public void setShapeList(ShapeList shapeList) {
        this.shapeList = shapeList;
    }
}
