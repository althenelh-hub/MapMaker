package WindowFolder.PaintPanel;

import PaintInstructions.PaintInstructionV2;
import javax.swing.*;
import java.awt.*;
public class PaintPanel<T extends PaintInstructionV2> extends JPanel {
    T instruction;
    Rectangle paintArea;
    public PaintPanel(Point size) {
        paintArea = new Rectangle(0,0, size.x, size.y);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintInstruction(g);
    }
    public void paintInstruction(Graphics g) { //logic outside of base paintComponent
        setStartingPoint();
        instruction.fulfill(g, paintArea);
    }
    public void setStartingPoint() {
        Rectangle rectangle = getBounds();
        paintArea.x = (rectangle.width-paintArea.width)/2;
        paintArea.y = (rectangle.height-paintArea.height)/2;
    }
    public void setInstruction(T instruction) {
        this.instruction = instruction;
    }
}
