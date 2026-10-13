package WindowFolder.PaintPanel;
import java.awt.image.*;
import PaintInstructions.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class BufferedQueuedPanelV2 {
    public QueuedPaintPanel instructionPanel;
    BufferedImage image;
    public BufferedQueuedPanelV2(BufferedImage image, QueuedPaintPanel panel) {
        instructionPanel = panel;
        this.image = image;

    }
    public void paintInstructions(ArrayList<PaintInstructionV2> instructions, Rectangle rect) {
        instructionPanel.put(instructions, rect);
        Graphics g = image.createGraphics();
        for(PaintInstructionV2 instruction : instructions) {
            instruction.fulfill(g, rect);
        }
        g.dispose();
        instructionPanel.updatePaint();
    }
    public void paintInit(PaintInstructionV2 instruction, Rectangle rectangle) {
        Graphics g = image.createGraphics();
        instruction.fulfill(g, rectangle);
        instructionPanel.repaint();
    }
    public void setMouseListener(MouseListener listener) {
        instructionPanel.addMouseListener(listener);
    }

}
