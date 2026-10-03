package PaintInstructions;
import java.awt.*;
public abstract class PaintForm extends LayeredInstruction {
    Color color;
    public PaintForm() {
        color = Color.BLACK;
    }
    public void fulfill(Graphics g, Rectangle rectangle) {
        g.setColor(color);
        paintForm(g, rectangle);
    }
    public abstract void paintForm(Graphics g, Rectangle rectangle);
    public void setColor(Color color) {
        this.color = color;
    }
}
