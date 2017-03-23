package programming.assignment.pkg5;

import java.awt.*;

public class GraphicSettings {
    protected boolean filled, gradient, dashed;
    protected int lineWidth = 2, dashLength = 5;
    protected String shape = "Line";
    protected Color firstColor = Color.black, secondColor = Color.red;
    protected static final String[] SHAPES = {"Line", "Oval", "Rectangle"};
}