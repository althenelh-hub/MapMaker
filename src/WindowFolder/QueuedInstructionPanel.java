package WindowFolder;
import PaintInstructions.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QueuedInstructionPanel extends JPanel{
    PaintQueue paintQueue;
    public void paintComponent(Graphics g) {
        PaintInstruction[] instructions = paintQueue.emptyQueue();
        for(PaintInstruction instruction : instructions) {
            System.out.println("Paint component " + getBounds());
            instruction.fulfill(g);
        }
    }
    public QueuedInstructionPanel(PaintInstruction instruction) {
        paintQueue = new PaintQueue(instruction);
    }
    public void put(PaintInstruction paintInstruction) {
        paintQueue.put(paintInstruction);
    }
    public void put(ArrayList<PaintInstruction> instructions) {
        paintQueue.put(instructions);
    }
    public void setBaseInstruction(PaintInstruction instruction) {
        paintQueue.setBaseInstruction(instruction);
    }
}
