package WindowFolder;

import java.awt.*;
import java.awt.image.BufferedImage;
import PaintInstructions.*;

import javax.swing.*;
import java.awt.event.*;
public class BufferedPaintPanelV2 { //can't use this as InstructedPaintPanel
    InstructedPaintPanel paintPanel;
    BufferedImage image;
    PaintImage paintImage;
    public BufferedPaintPanelV2(BufferedImage image, InstructedPaintPanel paintPanel) {
        this.paintPanel = paintPanel;
        this.image = image;
        paintImage = new PaintImage(image);
    }
    public BufferedPaintPanelV2(BufferedImage image) {
        this(image, new InstructedPaintPanel());
    }
    public BufferedPaintPanelV2(int x, int y, int imageType, InstructedPaintPanel paintPanel) {
        this(new BufferedImage(x, y, imageType), paintPanel);
    }
    public BufferedPaintPanelV2(int x, int y, int imageType) {
        this(x, y, imageType, new InstructedPaintPanel());
    }
    public void paintInstruction(PaintInstruction[] paintInstructions, Rectangle rect) {
        for(PaintInstruction instruction : paintInstructions) {
            paintPanel.setInstruction(instruction);
            paintPanel.repaint(rect.x, rect.y, rect.width,rect.height);
            Graphics g = image.getGraphics();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    paintPanel.setInstruction(paintImage);
                }
            });
        }
    }
    public void paintInstruction(PaintInstruction paintInstruction) {
        paintPanel.setInstruction(paintInstruction);
        paintPanel.repaint(); //repaint places in queue not starts immediately
        Graphics g = image.getGraphics();
        paintInstruction.fulfill(g);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //paintPanel.setInstruction(paintImage); //maybe there is a way to change paintState only when changing size
            }
        });
    }
    public void paintInstruction(PaintInstruction paintInstruction, Rectangle rect) {
        paintPanel.setInstruction(paintInstruction);
        paintPanel.repaint(rect.x, rect.y, rect.width, rect.height); //repaint places in queue not starts immediately
        Graphics g = image.getGraphics();
        paintInstruction.fulfill(g);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                paintPanel.setInstruction(paintImage); //maybe there is a way to change paintState only when changing size
            }
        });
    }
    public void paintInstruction() {
        paintPanel.repaint();
        Graphics g = image.getGraphics();
        paintPanel.instruction.fulfill(g);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                paintPanel.setInstruction(paintImage); //maybe there is a way to change paintState only when changing size
            }
        });
    }
    public void setMouseListener(MouseListener listener) {
        paintPanel.addMouseListener(listener);
    }
}
