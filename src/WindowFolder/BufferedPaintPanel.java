package WindowFolder;
import java.awt.image.*;
import java.awt.*;
import PaintInstructions.*;
public class BufferedPaintPanel extends InstructedPaintPanel {
    BufferedImage image;
    PaintImage defaultInstruction;
    public BufferedPaintPanel(BufferedImage image) {
        super();
        this.image = image;
        defaultInstruction = new PaintImage(image);
    }
    public BufferedPaintPanel(int x, int y, int imageType) {
        this(new BufferedImage(x, y, imageType));
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics bufferGraphics = image.getGraphics();
        instruction.fulify(bufferGraphics);
    }
    public void setDefaultInstruction() {
        instruction = defaultInstruction;
    }
}
