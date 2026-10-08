package PaintInstructions;
import java.awt.*;
public abstract class PaintForm implements PaintInstructionV2{
    protected Color color;
    public void fulfill(Graphics g, Rectangle rectangle) {
        g.setColor(color);
        paintForm(g, rectangle);
    }
    public Color getColor() {
        return color;
    }
    public abstract void paintForm(Graphics g, Rectangle rectangle);
    public void setColor(Color color) {
        this.color = color;
    }
}
