package programming.assignment.pkg5;

import java.awt.Point;
import java.util.ArrayList;

public class DrawingAction {
    GraphicSettings settings;
    Point startPoint, endPoint;
    ArrayList<Point> middlePoints;

    DrawingAction(GraphicSettings settings, Point p1, Point p2) {
        this.settings = settings;
        this.startPoint = p1;
        this.endPoint = p2;
        middlePoints = new ArrayList<Point>();
    }
}
