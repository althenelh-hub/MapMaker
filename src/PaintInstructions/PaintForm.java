package PaintInstructions;
import java.awt.*;
public abstract class PaintForm extends LayeredInstruction {
    Color color;
    Rectangle rect;
    public PaintForm() {
        color = Color.BLACK;
        rect = new Rectangle(0,0,0,0);
    }
    public void fulfill(Graphics g) {
        g.setColor(color);
        paintForm(g);
    }
    public abstract void paintForm(Graphics g);
    public void setColor(Color color) {
        this.color = color;
    }
    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

}
