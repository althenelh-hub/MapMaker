package PaintInstructions;
import java.awt.*;
import java.awt.image.*;
public class PaintImageV2 implements PaintInstruction {
    Point size;
    Point startPosition;
    BufferedImage image;
    public PaintImageV2(Point size, Point startPosition, BufferedImage image) {
        this.size = size;
        this.startPosition = startPosition;
        this.image = image;
    }
    public PaintImageV2(Point size, Rectangle parentSize, BufferedImage image) {
        this.size = size;
        changeStartingPoint(parentSize);
        this.image = image;
    }
    public void fulfill(Graphics g) {
        g.drawImage(image, startPosition.x, startPosition.y, size.x+ startPosition.x,size.y+ startPosition.y,
                0,0,size.x,size.y, null);
    }
    public void changeStartingPoint(Rectangle parentSize) {
        startPosition = new Point((parentSize.width-size.x)/2, (parentSize.height-size.y)/2);
    }
    public void changeSize(Point offset) {
        size.x+=offset.x;
        size.y+=offset.y;
    }
    public int getWidth() {
        return size.x;
    }
    public int getHeight() {
        return size.y;
    }
}
