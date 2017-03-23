package programming.assignment.pkg5;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
    private GraphicSettings settings;
    private Point startPoint, endPoint;
    ArrayList<DrawingAction> actionList = new ArrayList<>();


    DrawingPanel(GraphicSettings settings) {
        this.settings = settings;
        setBackground(Color.white);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (DrawingAction action : actionList) {
            updateGraphics(g2d, action);
            drawShapes(g2d, action);
        }
        updateGraphics(g2d, null);
        drawShapes(g2d, null);
    }

    private void updateGraphics(Graphics2D g2d, DrawingAction action) {
        if (action == null) {
            action = new DrawingAction(settings, startPoint, endPoint);
        }
        if (action.settings.dashed) {
            g2d.setStroke(new BasicStroke(action.settings.lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        } else {
            g2d.setStroke(new BasicStroke(action.settings.lineWidth));
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
            action = new DrawingAction(settings, startPoint, endPoint);
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
        if (action.settings.shape.equals("Line")) {
            g.drawLine(action.startPoint.x, action.startPoint.y, action.endPoint.x, action.endPoint.y);
        } else if (action.settings.filled) {
            switch (action.settings.shape) {
                case ("Rectangle"):
                    g.fillRect(startX, startY, width, height);
                    break;
                case ("Oval"):
                    g.fillOval(startX, startY, width, height);
                    break;
                case ("Line"):
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
                    break;
                default:
                    throw new RuntimeException(settings.shape + " is not supported shape?");
            }
        }
    }

    private void addDrawingAction() {
        actionList.add(new DrawingAction(settings.copy(), startPoint, endPoint));
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (endPoint != null) {
            addDrawingAction();
        }
        startPoint = null;
        endPoint = null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
    }


    @Override
    public void mouseEntered(MouseEvent e) {
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        endPoint = e.getPoint();
        this.repaint();
    }

}

class DrawingAction {
    GraphicSettings settings;
    Point startPoint, endPoint;

    DrawingAction(GraphicSettings settings, Point p1, Point p2) {
        this.settings = settings;
        this.startPoint = p1;
        this.endPoint = p2;
    }
}