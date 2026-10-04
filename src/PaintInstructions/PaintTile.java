package PaintInstructions;
import java.awt.*;
public class PaintTile extends PaintForm {
    public PaintTile(int layer) {
        super(layer);
    }
    public void paintForm(Graphics g, Rectangle rect) {
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

}
