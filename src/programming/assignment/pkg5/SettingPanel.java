package programming.assignment.pkg5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

class SettingPanel extends JPanel {

    private JButton undoButton, clearButton, firstColorButton, secondColorButton;
    private JComboBox<String> shapeComboBox;
    private JCheckBox filledCheckBox, gradientCheckBox, dashedCheckBox;
    private JTextField lineField, dashField;

    private final String[] SHAPE_OPTIONS = {"Line", "Oval", "Rectangle"};

    private ArrayList<DrawingPanel> actionList = new ArrayList<DrawingPanel>();
    private String shape;
    private boolean filled, gradient;
    public MainPanel mainPanel;

    public SettingPanel(GraphicSettings settings) {
        setLayout(new GridLayout(2, 6));
        // undoButton
        undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (actionList.size() == 0) {
                    //TODO prompt a OptionPane to warn user can't undo
                } else {
                    DrawingPanel panel = actionList.get(actionList.size() - 1);
                    mainPanel.drawingPanel = panel;
                    actionList.remove(panel);
                }
            }
        });
        add(undoButton);
        // clearButton
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.drawingPanel.removeAll();
            }
        });
        add(clearButton);
        // shapeComboBox
        add(new JLabel("Shape:"));
        shapeComboBox = new JComboBox<>(SHAPE_OPTIONS);
        shapeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                shape = (String) (e.getItem());
            }
        });
        add(shapeComboBox);
        // filledCheckBox
        filledCheckBox = new JCheckBox("filled");
        filledCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                filled = (e.getStateChange()==ItemEvent.SELECTED);
            }
        });
        add(filledCheckBox);
        // gradient checkbox
        gradientCheckBox = new JCheckBox("Use gradient");
        gradientCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                gradient = (e.getStateChange()==ItemEvent.SELECTED);
            }
        });
        add(gradientCheckBox);
        // 1st color
        firstColorButton = new JButton("1st Color");
        add(firstColorButton);
        // 2nd color
        secondColorButton = new JButton("2nd Color");
        add(secondColorButton);
        // line field
        add(new JLabel("Line Width:"));
        lineField = new JTextField(3);
        add(lineField);
        // Dash Length
        add(new JLabel("Dash Length"));
        dashField = new JTextField(3);
        add(dashField);
        // Dashed check box
        dashedCheckBox = new JCheckBox("Dashed");
        add(dashedCheckBox);
    }
}