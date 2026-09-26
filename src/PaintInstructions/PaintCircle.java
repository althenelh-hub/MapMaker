package PaintInstructions;

import java.awt.*;

public class PaintCircle extends PaintForm {
    public void paintForm(Graphics g) {
        g.fillOval(rect.x, rect.y, rect.width, rect.height);
    }
}
