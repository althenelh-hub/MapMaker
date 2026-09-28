package WindowFolder;
import java.awt.image.*;
import PaintInstructions.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
public class BufferedQueuedPanelV2 {
    public QueuedInstructionPanel instructionPanel;
    BufferedImage image;
    public BufferedQueuedPanelV2(BufferedImage image, QueuedInstructionPanel panel) {
        instructionPanel = panel;
        panel.setBaseInstruction(new PaintImage(image));
        this.image = image;
    }
    public void paintInstructions(ArrayList<PaintInstruction> instructions, Rectangle rect) {
        instructionPanel.put(instructions);
        Graphics g = image.createGraphics();
        for(PaintInstruction instruction : instructions) {
            instruction.fulfill(g);
        }
        instructionPanel.repaint(rect);
    }
    public void setMouseListener(MouseListener listener) {
        instructionPanel.addMouseListener(listener);
    }
}
