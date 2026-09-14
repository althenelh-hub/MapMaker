package PaintInstructions;
import java.awt.*;
public class PaintTile implements PaintInstruction {
    Point tilePosition;
    public void fulify(Graphics g) {
        g.fillRect(tilePosition.x, tilePosition.y, 19,19);
    }
}
