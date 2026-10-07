package WindowFolder;

import PaintInstructions.*;

import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;
import java.awt.*;
public class BufferCommunication extends PaintPanel<PaintInstructionV2> {
    ArrayList<PaintInstructionV2> instructions = new ArrayList<>();
    Rectangle currentArea;
    public BufferCommunication(PaintInstructionV2 image, Point areaSize) {
        super(areaSize);
        //setStartingPoint();
        currentArea = new Rectangle(0,0,0,0);
        this.instruction = image;
    }
    public void paintInstruction(Graphics g) {
        if(instructions.isEmpty()) {
            super.paintInstruction(g);
        }
        else {
            for(PaintInstructionV2 p : instructions) {
                p.fulfill(g, currentArea);
            }
            instructions.clear();
        }
    }
    public void changeSize(Point point) {
        paintArea.width += point.x;
        paintArea.height += point.y;
        setStartingPoint();
    }
    public void put(ArrayList<PaintInstructionV2> instructions, Rectangle Area) {
        this.instructions.addAll(instructions);
        currentArea.setRect(Area); //check if it is actually sets individually
        offsetCurrentArea(getStartingPoint());
    }
    public Point getStartingPoint() {
        return new Point(paintArea.x, paintArea.y);
    }
    public void offsetCurrentArea(Point point) {
        System.out.println("Before offset: " + currentArea);
        currentArea.x += point.x;
        currentArea.y += point.y;
        System.out.println("After offset: " + currentArea);
    }
    public void updatePaint() {
        repaint(currentArea);
    }

}
