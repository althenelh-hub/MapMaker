package WindowFolder;
import PaintInstructions.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class QueuedInstructionPanel extends JPanel{
    ArrayList<PaintInstruction> instructions;
    PaintImage image;
    public void paintComponent(Graphics g) {
        if(instructions.isEmpty()) {
            //image.changePaint();
            image.fulfill(g);
        }
        else {
            for(PaintInstruction instruction : instructions) {
                System.out.println("Paint component " + getBounds());
                instruction.fulfill(g);
            }
        }
    }
    public QueuedInstructionPanel(PaintImage instruction) {
        instructions = new ArrayList<>();
    }
    public void put(ArrayList<PaintInstruction> instructions) {
        this.instructions.addAll(instructions);
    }
    public void setBaseInstruction(PaintInstruction instruction) {
        //paintQueue.setBaseInstruction(instruction);
    }
}
