package WindowFolder;

import PaintInstructions.*;

import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.*;
import java.awt.*;
public class BufferCommunication extends JPanel{
    ArrayList<PaintInstruction> instructions = new ArrayList<>();
    PaintImageV2 image;
    public BufferCommunication(BufferedImage image, Point size) {
        this.image = new PaintImageV2(size,getBounds(),image);
    }
    public void paintComponent(Graphics g) {
        if(instructions.isEmpty()) {
            image.changeStartingPoint(getBounds());
            image.fulfill(g);
        }
        else {
            for(PaintInstruction p : instructions) {
                p.fulfill(g);
            }
            instructions.clear();
        }
    }
    public void put(ArrayList<PaintInstruction> instructions) {
        this.instructions.addAll(instructions);
    }

}
