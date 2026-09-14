package WindowFolder;

import PaintInstructions.*;

import javax.swing.*;
import java.awt.*;
//paints what instructed
public class InstructedPaintPanel extends JPanel {
    PaintInstruction instruction;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        instruction.fulify(g);
    }
    public void setInstruction(PaintInstruction instruction) {
        this.instruction = instruction;
    }
}
