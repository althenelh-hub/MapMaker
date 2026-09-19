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
    public void paintInstruction(PaintInstruction paintInstruction) {
        paintPanel.setInstruction(paintInstruction);
        paintPanel.repaint(); //repaint places in queue not starts immediately
        Graphics g = image.getGraphics();
        paintInstruction.fulify(g);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //paintPanel.setInstruction(paintImage); //maybe there is a way to change paintState only when changing size
            }
        });
    }
    public void paintInstruction(PaintInstruction paintInstruction, int x, int y, int width, int height) {
        paintPanel.setInstruction(paintInstruction);
        paintPanel.repaint(x, y, width, height); //repaint places in queue not starts immediately
        Graphics g = image.getGraphics();
        paintInstruction.fulify(g);
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
