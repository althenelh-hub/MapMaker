package PaintInstructions;

import java.awt.*;

public class PaintCircleFactory implements PaintFormFactory<PaintCircle> {
    public PaintCircle createForm(Color color) {
        PaintCircle circle = new PaintCircle();
        circle.setColor(color);
        return circle;
    }

}
