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
    private ArrayList<DrawingPanel> actionList = new ArrayList<>();
    MainPanel mainPanel;

    SettingPanel(GraphicSettings settings) {
        setLayout(new GridLayout(2, 6));
        // undoButton
        {
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
        }
        // clearButton
        {
            clearButton = new JButton("Clear");
            clearButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainPanel.drawingPanel.removeAll();
                }
            });
            add(clearButton);
        }
        // shapeComboBox
        {
            add(new JLabel("Shape:"));
            shapeComboBox = new JComboBox<>(GraphicSettings.SHAPES);
            shapeComboBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    settings.shape = (String) (e.getItem());
                }
            });
            add(shapeComboBox);
        }
        // filledCheckBox
        {
            filledCheckBox = new JCheckBox("filled");
            filledCheckBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    settings.filled = (e.getStateChange() == ItemEvent.SELECTED);
                }
            });
            add(filledCheckBox);
        }
        // gradient checkbox
        {
            gradientCheckBox = new JCheckBox("Use gradient");
            gradientCheckBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    settings.gradient = (e.getStateChange() == ItemEvent.SELECTED);
                }
            });
            add(gradientCheckBox);
        }
        // 1st color
        {
            firstColorButton = new JButton("1st Color");
            firstColorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    settings.firstColor = JColorChooser.showDialog(null, "Choose A Color", settings.firstColor);
                    firstColorButton.setBackground(settings.firstColor);//TODO why color didn't change?
                }
            });
            add(firstColorButton);
        }
        // 2nd color
        {
            secondColorButton = new JButton("2nd Color");
            secondColorButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    settings.secondColor = JColorChooser.showDialog(null, "Choose A Color", settings.secondColor);
                    secondColorButton.setBackground(settings.secondColor);//TODO why color didn't change?
                }
            });
            add(secondColorButton);
        }
        // line field
        {
            add(new JLabel("Line Width:"));
            lineField = new JTextField(String.valueOf(settings.lineWidth), 3);
            lineField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    settings.lineWidth = Integer.getInteger(lineField.getText());
                }
            });
            add(lineField);
        }
        // Dash Length
        {
            add(new JLabel("Dash Length"));
            dashField = new JTextField(String.valueOf(settings.dashLength), 3);
            dashField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    settings.dashLength = Integer.getInteger(dashField.getText());
                }
            });
            add(dashField);
        }
        // Dashed check box
        {
            dashedCheckBox = new JCheckBox("Dashed");
            add(dashedCheckBox);
        }
    }
}