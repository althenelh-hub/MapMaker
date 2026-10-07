package WindowFolder;

import PaintInstructions.PaintImageV2;
import PaintInstructions.PaintInstructionV2;
import java.awt.*;
import javax.swing.*;

public class PaintInstructionPanel extends PaintPanel<PaintInstructionV2> {
    public PaintInstructionPanel(PaintInstructionV2 instruction, Point size) {
        super(size);
        this.instruction = instruction;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    public Point getStartingPoint() {
        return new Point(paintArea.x, paintArea.y);
    }
}
