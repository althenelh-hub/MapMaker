package WindowFolder;

import PaintInstructions.PaintImageV2;
import PaintInstructions.PaintInstructionV2;
import java.awt.*;
import javax.swing.*;

public class PaintInstructionPanel extends JPanel {
    PaintInstructionV2 instruction;
    Rectangle paintArea;
    public PaintInstructionPanel(PaintInstructionV2 instruction, Point size) {
        this.instruction = instruction;
        paintArea = new Rectangle(0,0, size.x, size.y);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setStartingPoint();
        instruction.fulfill(g, paintArea);
        System.out.println(g.getColor());
    }
    public void setStartingPoint() {
        Rectangle rectangle = getBounds();
        paintArea.x = (rectangle.width-paintArea.width)/2;
        paintArea.y = (rectangle.height-paintArea.height)/2;
    }
    public void setInstruction(PaintInstructionV2 instruction) {
        this.instruction = instruction;
    }
    public Point getStartingPoint() {
        return new Point(paintArea.x, paintArea.y);
    }
}
