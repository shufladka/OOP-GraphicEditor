package by.bsuir.tools;

import by.bsuir.domain.MyShape;

import javax.swing.*;
import java.awt.*;

// class that implements the figure drawing panel
public class ShapeDrawToolsPanel extends JPanel {

    private MyShape myShape;

    // ShapeDrawToolsPanel class constructor
    public ShapeDrawToolsPanel(MyShape myShape) {
        this.myShape = myShape;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        // настройка сглаживания
        RenderingHints renderingHints = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        graphics2D.setRenderingHints(renderingHints);

        // настройка толщины линий
        graphics2D.setStroke(new BasicStroke(2.5f));

        myShape.getShapeListDraw().draw(graphics2D);
    }
}
