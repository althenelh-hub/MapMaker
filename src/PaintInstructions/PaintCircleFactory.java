package PaintInstructions;

import java.awt.*;

public class PaintCircleFactory implements PaintFormFactory<PaintCircle> {
    public PaintCircle createForm(Color color) {
        PaintCircle circle = new PaintCircle(1);
        circle.setColor(color);
        return circle;
    }

}
