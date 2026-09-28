package WindowFolder;

import PaintInstructions.*;
import java.util.*;
import java.awt.*;
import java.awt.image.*;

public class BufferedQueuedPanel extends QueuedInstructionPanel{
    BufferedImage image;
    public BufferedQueuedPanel(PaintInstruction instruction) {
        super(instruction);
    }
    public void paintInstructions(ArrayList<PaintInstruction> instructions) {
        Graphics g = image.getGraphics();
        for(PaintInstruction instruction : instructions) {
            instruction.fulfill(g);
        }
        paintQueue.put(instructions);
        repaint();
    }
}
