package WindowFolder;
import PaintInstructions.*;

import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
public class Painter {
    PaintInstruction instruction = new PaintTile();
    public void paintTile(InstructedPaintPanel panel) {
        panel.setInstruction(instruction);
    }
    public void paintTile(BufferedImage image) {
        Graphics g = image.getGraphics();

    }
}
