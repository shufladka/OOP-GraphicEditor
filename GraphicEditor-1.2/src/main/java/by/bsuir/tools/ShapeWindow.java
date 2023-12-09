package by.bsuir.tools;

import by.bsuir.domain.*;
import by.bsuir.plugins.ActionParameterType;
import by.bsuir.plugins.Point;
import by.bsuir.plugins.SearchClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.*;

public class ShapeWindow extends JFrame {

    // getter for the map structure of the relationship between shape identifiers and edit buttons
    public Map <UUID, JButton> getFiguresButtonsMap() {
        return figuresButtonsMap;
    }

    // map structure of the relationship between shape identifiers and editing buttons
    private Map <UUID, JButton> figuresButtonsMap = new HashMap<>();

    // panel with tools for creating shapes
    private JPanel leftButtonPanel;

    // panel with tools for editing created shapes
    private JPanel rightButtonPanel;

    // stores a list of drawn shapes
    private ShapeList shapeList;

    private ShapeDrawToolsPanel shapeDrawToolsPanel;

    // constructor of the ShapeWindow class
    public ShapeWindow(String title) throws HeadlessException {
        this.shapeList = new ShapeList();
        this.setTitle(title);

        setConfig();
        setMainPanel();
    }

    // method for creating an application window
    private void setConfig() {

        // set the window size
        setSize(1280, 800);

        // set the condition for closing the window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // method for displaying the main panel
    private void setMainPanel() {
        setDrawToolsPanel();

        staticFigures();

        setLeftButtonPanel();
        setRightButtonPanel();
    }

    // method for statically initializing shapes
    private void staticFigures() {

        shapeList.add(new Circle(180, new Point(80, 400)));
        shapeList.add(new Line(new Point(120, 240), new Point(660, 410)));
        shapeList.add(new RegularPolygon(190, new Point(240, 140), 6));
        shapeList.add(new RegularPolygon(165, new Point(400, 550), 8));
        shapeList.add(new TriangleIsosceles(200, new Point(580, 160)));
        shapeList.add(new TriangleRectangular(170, new Point(640, 480)));
    }

    // method for displaying the active area
    private void setDrawToolsPanel() {
        shapeDrawToolsPanel = new ShapeDrawToolsPanel(shapeList);
        shapeDrawToolsPanel.setPreferredSize(new Dimension(880, 800));
        add(shapeDrawToolsPanel, BorderLayout.CENTER);
    }

    // method for displaying a button bar for creating new shapes
    private void setLeftButtonPanel() {

        // setting the left panel
        leftButtonPanel = new JPanel();
        leftButtonPanel.setBorder(BorderFactory.createEtchedBorder());
        leftButtonPanel.setPreferredSize(new Dimension(200, 800));

        List<Class> shapeClasses = getShapeClasses();
        for (Class shapeClass : shapeClasses) {
            JButton jButton = new JButton(shapeClass.getSimpleName());
            jButton.setPreferredSize(new Dimension(160, 35));
            jButton.addActionListener(getButtonActionListener(shapeClass));
            leftButtonPanel.add(jButton);
        }

        // button to clear the active area of the program
        JButton jButton = new JButton("REMOVE ALL");
        jButton.setPreferredSize(new Dimension(160, 35));
        jButton.addActionListener(e -> removeAllShapes());
        leftButtonPanel.add(jButton);

        add(leftButtonPanel, BorderLayout.WEST);
    }

    // method for displaying a button bar for editing created shapes
    private void setRightButtonPanel() {

        // setting up the right panel
        rightButtonPanel = new JPanel();
        rightButtonPanel.setBorder(BorderFactory.createEtchedBorder());
        rightButtonPanel.setPreferredSize(new Dimension(200, 800));

        for (int i = 0; i < shapeList.getShapeList().size(); i++) {

            int index = i;

            String buttonName = shapeList.getShapeList().get(index).getClass().getSimpleName();
            addButtonsToRightPanel(buttonName, index);
        }

        add(rightButtonPanel, BorderLayout.EAST);
    }

    // method that adds buttons for editing created shapes and redrawing the right panel
    private void repaintRightPanel(String buttonName, int index) {
        addButtonsToRightPanel(buttonName, index);

        // redraw the right panel
        rightButtonPanel.revalidate();
        rightButtonPanel.repaint();
    }

    // method for generating new buttons for editing shapes on the right panel
    private void addButtonsToRightPanel(String buttonName, int index) {
        JButton jButton = new JButton((index + 1) + "." + buttonName);
        jButton.setPreferredSize(new Dimension(160, 35));

        // deleting a figure is done by clicking the right mouse button
        jButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    editShapeSettings(jButton, ActionParameterType.REMOVE);
                }
            }
        });

        jButton.addActionListener(e -> editShapeSettings(jButton, ActionParameterType.REFRESH_POSITION));

        // add a button to the "button - shape" correspondence table
        figuresButtonsMap.put(shapeList.getShapeList().get(index).getUUID(), jButton);

        rightButtonPanel.add(jButton);
    }

    // method for removing the last shape from the active area (along with the edit button)
    private void removeAllShapes() {
        while (!shapeList.getShapeList().isEmpty()) {
            shapeList.remove(shapeList.size() - 1);
            rightButtonPanel.remove(shapeList.size());

            rightButtonPanel.revalidate();
            repaint();
        }
    }

    // method for removing a shape from the active area by its index (along with the edit button)
    private void editShapeSettings(JButton jButton, ActionParameterType actionParameterType) {

        int shapeIndex = 0;
        Map<UUID, JButton> buttonMap = getFiguresButtonsMap();

        // find the UUID of the button in the button map
        for (Map.Entry<UUID, JButton> entry : buttonMap.entrySet()) {
            if (entry.getValue() == jButton) {
                UUID uuid = entry.getKey();

                for (int i = 0; i < shapeList.getShapeList().size(); i++) {
                    if (shapeList.getShapeList().get(i).getUUID().equals(uuid)) {
                        shapeIndex = i;
                        break;
                    }
                }

                // resizing an existing shape
                if (actionParameterType.equals(ActionParameterType.REFRESH_POSITION)) {

                    // getting the class name
                    Class shapeClass = shapeList.getShapeList().get(shapeIndex).getClass();
                    editLine(shapeClass, shapeIndex, uuid);

                }

                // deleting the current figure
                if (actionParameterType.equals(ActionParameterType.REMOVE)) {

                    // remove the figure from the list
                    shapeList.remove(shapeIndex);

                    // remove the button from the parent component
                    Container parent = jButton.getParent();
                    if (parent != null) {
                        parent.remove(jButton);
                        parent.revalidate();
                        parent.repaint();

                        // remove the button for editing the current figure
                        shapeDrawToolsPanel.repaint();
                    }
                }

                break;
            }
        }
    }

    // method for editing line position
    private void editLine(Class shapeClass, int shapeIndex, UUID uuid) {

        List<String> shapePropertiesNames;

        try {
            shapePropertiesNames = getShapePropertyNames(shapeClass);
        } catch (IllegalAccessException | NoSuchFieldException e1) {
            JOptionPane.showMessageDialog(this, "Shape inheritor should have 'propertyNames' field.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Map<String, JSpinner> shapeFields = new HashMap<>();
        buildShapeFieldsDialog(shapeFields, shapePropertiesNames);

        try {
            MyShape newShape = buildNewShape(shapeClass, shapeFields);

            // replacing a new figure in place of the old one before making changes
            shapeList.getShapeList().set(shapeIndex, newShape);

            // replacing the UUID of the new shape for the edit button to work correctly
            shapeList.getShapeList().get(shapeIndex).setUUID(uuid);

            // redrawing the active area of the program window
            repaint();

        } catch (NoSuchMethodException e1) {
            JOptionPane.showMessageDialog(this, "Shape inheritor should have 'constructShape' method.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalAccessException e1) {
            JOptionPane.showMessageDialog(this, "'constructShape' method should be public static.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (InvocationTargetException e1) {
            JOptionPane.showMessageDialog(this, e1, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // method that monitors each button that creates a shape
    private ActionListener getButtonActionListener(Class shapeClass) {

        ActionListener actionListener = e -> {

            List<String> shapePropertiesNames;

            try {
                shapePropertiesNames = getShapePropertyNames(shapeClass);
            } catch (IllegalAccessException | NoSuchFieldException e1) {
                JOptionPane.showMessageDialog(this, "Shape inheritor should have 'propertyNames' field.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Map<String, JSpinner> shapeFields = new HashMap<>();
            buildShapeFieldsDialog(shapeFields, shapePropertiesNames);

            try {
                MyShape newMyShape = buildNewShape(shapeClass, shapeFields);

                // adding a shape to the list of shapes
                shapeList.add(newMyShape);

                // creating a new button on the editing panel corresponding to this shape
                repaintRightPanel(shapeClass.getSimpleName(), (shapeList.size() - 1));

                // redrawing the active area of the program window
                repaint();

            } catch (NoSuchMethodException e1) {
                JOptionPane.showMessageDialog(this, "Shape inheritor should have 'constructShape' method.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalAccessException e1) {
                JOptionPane.showMessageDialog(this, "'constructShape' method should be public static.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (InvocationTargetException e1) {
                JOptionPane.showMessageDialog(this, e1, "Error", JOptionPane.ERROR_MESSAGE);
            }
        };
        return actionListener;
    }

    // method that retrieves shape classes from by.bsuir.domain
    private List<Class> getShapeClasses() {

        String shapePackageName = "by.bsuir.domain";
        List<Class> shapeClasses = new ArrayList<>();

        try {
            shapeClasses = SearchClasses.getClassesFromPackage(shapePackageName, MyShape.class);
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(ShapeWindow.this, "The error while trying to extract classes from '" + shapePackageName + "' package", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return shapeClasses;
    }

    // method that gets property names for the MyShape class
    private List<String> getShapePropertyNames(Class shapeName) throws NoSuchFieldException, IllegalAccessException {

        Field field = shapeName.getField("propertyNames");
        List<String> propertyNames = (List<String>) field.get(null);

        return propertyNames;
    }

    // method that creates a form fields dialog and fills in the fields for the shape
    private void buildShapeFieldsDialog(Map<String, JSpinner> shapeFields, List<String> shapePropertiesNames) {

        List<Component> windowFields = new ArrayList<>();

        for (String propertyName : shapePropertiesNames) {
            JLabel fieldLabel = new JLabel(propertyName);
            JSpinner numberSpinner = new JSpinner(new SpinnerNumberModel());
            shapeFields.put(propertyName, numberSpinner);
            windowFields.add(fieldLabel);
            windowFields.add(numberSpinner);
        }

        JOptionPane.showConfirmDialog(this, windowFields.toArray(), "Enter your params", JOptionPane.PLAIN_MESSAGE);
    }


    // method that creates a new figure
    private MyShape buildNewShape(Class shapeClass, Map<String, JSpinner> shapeFields) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method constructShapeMethod = shapeClass.getMethod("constructShape", Map.class);

        Map<String, Integer> shapeProperties = new HashMap<>();

        for (Map.Entry<String, JSpinner> shapeField : shapeFields.entrySet()) {
            shapeProperties.put(shapeField.getKey(), (Integer) shapeField.getValue().getValue());
        }

        return (MyShape) constructShapeMethod.invoke(null, shapeProperties);
    }
}
