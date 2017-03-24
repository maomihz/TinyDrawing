package programming.assignment.pkg5;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

    private SettingPanel settingPanel;
    DrawingPanel drawingPanel;
    private GraphicSettings settings;
    private JLabel mouseLabel;

    MainPanel() {
        settings = new GraphicSettings();

        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 0.1;
        settingPanel = new SettingPanel(settings);
        settingPanel.mainPanel = this;
        grid.setConstraints(settingPanel, c);
        c.gridy = 1;
        c.gridheight = 5;
        c.weighty = 0.9;
        c.fill = GridBagConstraints.BOTH;
        drawingPanel = new DrawingPanel(settings, this);
        grid.setConstraints(drawingPanel, c);
        add(settingPanel);
        add(drawingPanel);
        setLayout(grid);
        c.gridy = 6;
        c.weighty = 0;
        mouseLabel = new JLabel("(0,0)");
        grid.setConstraints(mouseLabel, c);
        add(mouseLabel);
    }

    void updateMouseLabel(Point p) {
        mouseLabel.setText("(" + String.valueOf(p.x) + "," + String.valueOf(p.y) + ")");
    }

}
