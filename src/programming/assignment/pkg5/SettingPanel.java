package programming.assignment.pkg5;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

class SettingPanel extends JPanel {

    private JButton undoButton, clearButton, firstColorButton, secondColorButton;
    private JComboBox<String> shapeComboBox;
    private JCheckBox filledCheckBox, gradientCheckBox, dashedCheckBox;
    private JTextField lineField, dashField;
    private ArrayList<DrawingAction> actionList;
    MainPanel mainPanel;

    SettingPanel(GraphicSettings settings) {
        setLayout(new GridLayout(2, 6));
        // undoButton
        {
            undoButton = new JButton("Undo");
            undoButton.addActionListener(e -> {
                if (actionList == null) {
                    actionList = mainPanel.drawingPanel.actionList;
                }
                    if (actionList.size() == 0) {
                        JOptionPane.showMessageDialog(null,
                                "Can't Undo", "Error Massage",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        actionList.remove(actionList.size() - 1);
                        mainPanel.drawingPanel.repaint();
                    }
            });
            add(undoButton);
        }
        // clearButton
        {
            clearButton = new JButton("Clear");
            clearButton.addActionListener(e -> {
                if (actionList == null) {
                    actionList = mainPanel.drawingPanel.actionList;
                }
                while (actionList.size() > 0) {
                    actionList.remove(0);
                }
                mainPanel.drawingPanel.repaint();
            });
            add(clearButton);
        }
        // shapeComboBox
        {
            add(new JLabel("Shape:"));
            shapeComboBox = new JComboBox<>(GraphicSettings.SHAPES);
            shapeComboBox.addItemListener(e -> settings.shape = (String) (e.getItem()));
            add(shapeComboBox);
        }
        // filledCheckBox
        {
            filledCheckBox = new JCheckBox("filled");
            filledCheckBox.addItemListener(e -> settings.filled = (e.getStateChange() == ItemEvent.SELECTED));
            add(filledCheckBox);
        }
        // gradient checkbox
        {
            gradientCheckBox = new JCheckBox("Use gradient");
            gradientCheckBox.addItemListener(e -> settings.gradient = (e.getStateChange() == ItemEvent.SELECTED));
            add(gradientCheckBox);
        }
        // 1st color
        {
            firstColorButton = new JButton("1st Color");
            firstColorButton.addActionListener(e -> {
                    settings.firstColor = JColorChooser.showDialog(null, "Choose A Color", settings.firstColor);
                if (settings.firstColor == null) {
                    settings.firstColor = Color.black;
                }
                    firstColorButton.setBackground(settings.firstColor);//TODO why color didn't change?
            });
            add(firstColorButton);
        }
        // 2nd color
        {
            secondColorButton = new JButton("2nd Color");
            secondColorButton.addActionListener(e -> {
                    settings.secondColor = JColorChooser.showDialog(null, "Choose A Color", settings.secondColor);
                if (settings.secondColor == null) {
                    settings.secondColor = Color.black;
                }
                    secondColorButton.setBackground(settings.secondColor);//TODO why color didn't change?
            });
            add(secondColorButton);
        }
        // line field
        {
            add(new JLabel("Line Width:"));
            lineField = new JTextField(String.valueOf(settings.lineWidth), 3);
            lineField.getDocument().addDocumentListener(new DocumentListener() {
                private void update() {
                    try {
                        int num = Integer.parseInt(lineField.getText());
                        if (num <= 0) {
                            JOptionPane.showMessageDialog(null,
                                    "Error: Please enter number bigger than 0", "Error Massage",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            settings.lineWidth = num;
                        }
                    } catch (NumberFormatException e) {
                        settings.lineWidth = 2;
                    }
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    update();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    update();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    update();
                }
            });
            add(lineField);
        }
        // Dash Length
        {
            add(new JLabel("Dash Length"));
            dashField = new JTextField(String.valueOf(settings.dashLength), 3);
            dashField.getDocument().addDocumentListener(new DocumentListener() {
                private void update() {
                    try {
                        int num = Integer.parseInt(dashField.getText());
                        if (num <= 0) {
                            JOptionPane.showMessageDialog(null,
                                    "Error: Please enter number bigger than 0", "Error Massage",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            settings.dashLength = num;
                        }
                    } catch (NumberFormatException e) {
                        settings.dashLength = 2;
                    }
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    update();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    update();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    update();
                }
            });
            add(dashField);
        }
        // Dashed check box
        {
            dashedCheckBox = new JCheckBox("Dashed");
            dashedCheckBox.addItemListener(e -> settings.dashed = dashedCheckBox.isSelected());
            add(dashedCheckBox);
        }
    }
}