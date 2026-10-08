package PaintInstructions;

import WindowFolder.Keeper;

import java.awt.*;

public class PaintDoubleCircle extends PaintForm{
    Keeper<Color> outsideCircle;
    public void paintForm(Graphics g, Rectangle rectangle) {
        g.fillOval(rectangle.x-4, rectangle.y-4, rectangle.width-4, rectangle.height-4);
        g.setColor(outsideCircle.getValue());
        g.drawOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    public void setInsideCircle(Color color) {
        this.color = color;
    }
    public Keeper<Color> getOutsideCircle() {
        return outsideCircle;
    }

}
