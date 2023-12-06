package by.bsuir;

import by.bsuir.tools.ShapeWindow;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {

        // create an instance of the ShapeWindow class and set the window name
        ShapeWindow shapeWindow = new ShapeWindow("GraphicEditor 2.6");

        // set the icon for the frame
        shapeWindow.setIconImage(new ImageIcon("src/main/resources/bsuir-icon.png").getImage());

        // make the window visible
        shapeWindow.setVisible(true);

        // align the window to the center
        shapeWindow.setLocationRelativeTo(null);
    }
}
