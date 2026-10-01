package PaintInstructions;
import java.awt.*;
public class PaintTile extends PaintForm {
    public void paintForm(Graphics g, Rectangle rect) {
        System.out.println("Inside paint tile: " + rect);
        System.out.println(color);
        g.setColor(Color.BLACK);
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
    }

}
