package PaintInstructions;
import java.awt.*;
public class PaintTile extends PaintForm {
    public void paintForm(Graphics g) {
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

}
