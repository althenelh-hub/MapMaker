package PaintInstructions.PaintForms;

import java.awt.*;
public class PaintTile extends PaintForm {
    public void paintForm(Graphics g, Rectangle rect) {
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

}
