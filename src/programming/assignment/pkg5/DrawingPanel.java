package programming.assignment.pkg5;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener {
    private GraphicSettings settings;
    public Point startPoint, endPoint;

    public DrawingPanel(GraphicSettings settings) {
        this.settings = settings;
        setBackground(Color.white);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        updateGraphics(g2d);
        drawShapes(g2d);
    }

    private void updateGraphics(Graphics2D g2d) {
        if (settings.dashed) {
            g2d.setStroke(new BasicStroke(settings.lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
        } else {
            g2d.setStroke(new BasicStroke(settings.lineWidth));
        }
        if (settings.gradient && settings.firstColor != null && startPoint != null && endPoint != null) {
            g2d.setPaint(new GradientPaint(startPoint.x, startPoint.y, settings.firstColor, endPoint.x, endPoint.y, settings.secondColor));
        } else if (settings.firstColor != null) {
            g2d.setPaint(new GradientPaint(0, 0, settings.firstColor, 100, 0, settings.firstColor));
        } else {
            throw new RuntimeException("first Color not initialized");
        }
    }

    private void drawShapes(Graphics2D g) {
        if (settings.filled && startPoint != null && endPoint != null) {
            switch (settings.shape) {
                case ("Line"):
                    g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                    break;
                case ("Rectangle"):
                    g.fillRect(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
                    break;
                case ("Oval"):
                    g.fillOval(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
                    break;
                default:
                    throw new RuntimeException(settings.shape + " is not supported shape?");
            }
        } else if (startPoint != null && endPoint != null) {
            switch (settings.shape) {
                case ("Line"):
                    g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                    break;
                case ("Rectangle"):
                    g.drawRect(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
                    break;
                case ("Oval"):
                    g.drawOval(startPoint.x, startPoint.y, endPoint.x - startPoint.x, endPoint.y - startPoint.y);
                    break;
                default:
                    throw new RuntimeException(settings.shape + " is not supported shape?");
            }
        }
    }


    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (endPoint != null) {
            //TODO paint the object
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
        System.out.println(startPoint + "  " + endPoint);
        this.repaint();
    }

}
