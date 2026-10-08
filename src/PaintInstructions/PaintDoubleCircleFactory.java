package PaintInstructions;

import java.awt.*;

public class PaintDoubleCircleFactory implements PaintFormFactory<PaintDoubleCircle> {
    public PaintDoubleCircle createForm(Color color) {
        return new PaintDoubleCircle();
    }
}
