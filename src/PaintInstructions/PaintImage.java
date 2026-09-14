package PaintInstructions;
import java.awt.*;
import java.awt.image.*;
public class PaintImage implements PaintInstruction {
    public PaintImage (BufferedImage image) {
        this.image = image;
    }
    BufferedImage image;
    public void fulify(Graphics g) {
        g.drawImage(image, 0,0,null);
    }
}
