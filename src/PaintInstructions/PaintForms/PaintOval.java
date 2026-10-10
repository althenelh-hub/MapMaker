package PaintInstructions.PaintForms;

import java.awt.*;

public class PaintOval extends PaintForm {
    public void paintForm(Graphics g, Rectangle rectangle) {
        g.drawOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}
