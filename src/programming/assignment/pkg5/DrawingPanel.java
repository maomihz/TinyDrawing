package programming.assignment.pkg5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.ArrayList;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
    private GraphicSettings settings;

    private DrawingAction preview; //The preview action
    ArrayList<DrawingAction> actionList = new ArrayList<>(); //Action list history

    private MainPanel mainPanel;

    // The mouse listener that fires action in this class
    private MouseAdapter myMouseListener = new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
            preview = new DrawingAction(settings.copy(), e.getPoint(), e.getPoint());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (preview != null) {
                addDrawingAction();
            }
            preview = null;
        }

        @Override
        public void mouseMoved(MouseEvent e) {
           mainPanel.updateMouseLabel(e.getPoint());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (preview.settings.shape.equals("Stroke")) {
                preview.middlePoints.add(e.getPoint());
            }
            preview.endPoint = e.getPoint();
            DrawingPanel.this.repaint();
        }
    };

    DrawingPanel(GraphicSettings settings, MainPanel mainPanel) {
        this.settings = settings;
        this.mainPanel = mainPanel;
        setBackground(Color.white);
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // redraw all the previous actions
        for (DrawingAction action : actionList) {
            updateGraphics(g2d, action);
            drawShapes(g2d, action);
        }
        // Passing null renders live preview, if preview exists
        if (preview != null) {
            updateGraphics(g2d, null);
            drawShapes(g2d, null);
        }
    }

    private void updateGraphics(Graphics2D g2d, DrawingAction action) {
        if (action == null) {
            action = preview;
        }
        if (action.settings.dashed) {
            g2d.setStroke(new BasicStroke(action.settings.lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[]{action.settings.dashLength}, 0));
        } else {
            g2d.setStroke(new BasicStroke(action.settings.lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        }
        if (action.settings.gradient && action.settings.firstColor != null && action.startPoint != null && action.endPoint != null) {
            g2d.setPaint(new GradientPaint(action.startPoint.x, action.startPoint.y, action.settings.firstColor, action.endPoint.x, action.endPoint.y, action.settings.secondColor));
        } else if (action.settings.firstColor != null) {
            g2d.setPaint(new GradientPaint(0, 0, action.settings.firstColor, 100, 0, action.settings.firstColor));
        } else {
            throw new RuntimeException("first Color not initialized");
        }
    }

    private void drawShapes(Graphics2D g, DrawingAction action) {
        if (action == null) {
            action = preview;
        }
        if (action.startPoint == null || action.endPoint == null) {
            return;
        }
        int startX = action.startPoint.x, startY = action.startPoint.y, width = action.endPoint.x - action.startPoint.x, height = action.endPoint.y - action.startPoint.y;
        if (width < 0) {
            width = -width;
            startX -= width;
        }

        if (height < 0) {
            height = -height;
            startY -= height;
        }
        if (action.settings.shape.equals("Line") || action.settings.shape.equals("Stroke")) {
            if (action.middlePoints.size() == 0) {
                g.drawLine(action.startPoint.x, action.startPoint.y, action.endPoint.x, action.endPoint.y);
            } else {
                g.drawLine(action.startPoint.x, action.startPoint.y, action.middlePoints.get(0).x, action.middlePoints.get(0).y);
                for (int i=1; i<action.middlePoints.size(); i++) {
                    g.drawLine(action.middlePoints.get(i-1).x, action.middlePoints.get(i-1).y, action.middlePoints.get(i).x, action.middlePoints.get(i).y);
                }
                g.drawLine(action.middlePoints.get(action.middlePoints.size() - 1).x, action.middlePoints.get(action.middlePoints.size() - 1).y, action.endPoint.x, action.endPoint.y);
            }
        } else if (action.settings.filled) {
            switch (action.settings.shape) {
                case ("Rectangle"):
                    g.fillRect(startX, startY, width, height);
                    break;
                case ("Oval"):
                    g.fillOval(startX, startY, width, height);
                    break;
                case ("Line"):
                case ("Stroke"):
                    break;
                default:
                    throw new RuntimeException(settings.shape + " is not supported shape?");
            }
        } else {
            switch (action.settings.shape) {
                case ("Rectangle"):
                    g.drawRect(startX, startY, width, height);
                    break;
                case ("Oval"):
                    g.drawOval(startX, startY, width, height);
                    break;
                case ("Line"):
                case ("Stroke"):
                    break;
                default:
                    throw new RuntimeException(settings.shape + " is not supported shape?");
            }
        }
    }

    private void addDrawingAction() {
        actionList.add(preview);
    }
}
