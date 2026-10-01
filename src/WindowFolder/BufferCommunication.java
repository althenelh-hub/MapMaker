package WindowFolder;

import PaintInstructions.*;

import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;
import java.awt.*;
public class BufferCommunication extends JPanel{
    ArrayList<PaintInstructionV2> instructions = new ArrayList<>();
    PaintInstructionV2 image;
    Rectangle paintArea;
    Rectangle currentArea;
    public BufferCommunication(PaintInstructionV2 image, Point areaSize) {
        Rectangle size = getBounds();
        paintArea = new Rectangle(0, 0, areaSize.x, areaSize.y);
        //setStartingPoint();
        currentArea = new Rectangle(0,0,0,0);
        this.image = image;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(instructions.isEmpty()) {
            setStartingPoint();
            image.fulfill(g, paintArea);
        }
        else {
            for(PaintInstructionV2 p : instructions) {
                System.out.println("Before paintTile: " + currentArea);
                p.fulfill(g, currentArea);
                System.out.println();
            }
            instructions.clear();
        }
    }
    public void changeSize(Point point) {
        paintArea.width += point.x;
        paintArea.height += point.y;
        setStartingPoint();
    }
    public void setStartingPoint() {
        Rectangle rectangle = getBounds();
        paintArea.x = (rectangle.width-paintArea.width)/2;
        paintArea.y = (rectangle.height-paintArea.height)/2;
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
