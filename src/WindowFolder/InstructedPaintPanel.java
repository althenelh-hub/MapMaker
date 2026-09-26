package WindowFolder;

import PaintInstructions.*;

import javax.swing.*;
import java.awt.*;
//paints what instructed
public class InstructedPaintPanel extends JPanel {
    PaintInstruction instruction;
    @Override
    public void paintComponent(Graphics g) {
        System.out.println(instruction + "Before super");
        super.paintComponent(g);
        System.out.println(instruction);
        instruction.fulfill(g);
    }

    public void setInstruction(PaintInstruction instruction) {
        this.instruction = instruction;
    }
}
