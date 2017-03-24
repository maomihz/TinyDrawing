package programming.assignment.pkg5;

import java.awt.*;

class GraphicSettings {
    boolean filled, gradient, dashed;
    int lineWidth = 2, dashLength = 5;
    String shape = "Stroke";
    Color firstColor = Color.black, secondColor = Color.red;
    static final String[] SHAPES = {"Stroke", "Line", "Oval", "Rectangle"};

    GraphicSettings copy() {
        GraphicSettings g = new GraphicSettings();
        g.filled = filled;
        g.gradient = gradient;
        g.dashed = dashed;
        g.lineWidth = lineWidth;
        g.dashLength = dashLength;
        g.shape = shape;
        g.firstColor = firstColor;
        g.secondColor = secondColor;
        return g;
    }
}
