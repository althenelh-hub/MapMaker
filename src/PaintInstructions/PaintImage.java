package PaintInstructions;
import java.awt.*;
import java.awt.image.*;
public class PaintImage implements PaintInstruction {
    BufferedImage image;
    Rectangle imageSize;
    Rectangle target;
    public PaintImage (BufferedImage image) {
        this.image = image;
    }
    public void setStartPosition() {

    }
    public void fulfill(Graphics g) {
        //g.drawImage(image, 0,0,0,0,0,0,0,0,null);
        g.drawImage(image, 0,0,null);
    }
}
