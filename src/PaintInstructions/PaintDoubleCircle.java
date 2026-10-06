package PaintInstructions;

import WindowFolder.ColorSetter;

import java.awt.*;

public class PaintDoubleCircle extends ColorSetter implements PaintInstructionV2 {
    Color insideCircle;
    public void fulfill(Graphics g, Rectangle rectangle) {
        g.setColor(color);
        g.drawOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.setColor(insideCircle);
        g.fillOval(rectangle.x-4, rectangle.y-4, rectangle.width-4, rectangle.height-4);
    }
    public void setInsideCircle(Color color) {
        insideCircle = color;
    }
    public ColorSetter getOutsideCircle() {
        return this;
    }

}
