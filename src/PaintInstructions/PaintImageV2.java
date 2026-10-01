package PaintInstructions;
import java.awt.*;
import java.awt.image.*;
public class PaintImageV2 implements PaintInstructionV2 {
    Point size;
    BufferedImage image;
    public PaintImageV2(Point size, BufferedImage image) {
        this.size = size;
        this.image = image;
    }
    public void fulfill(Graphics g, Rectangle target) {
        g.drawImage(image, target.x, target.y, size.x+ target.x,size.y+ target.y,
                0,0,size.x,size.y, null);
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
