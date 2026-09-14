package Listeners;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

public class TestMouseListener implements MouseListener {
    JPanel mainCanvas;
    BufferedImage buffer;
    public TestMouseListener(JPanel mainCanvas, BufferedImage buffer) {
        this.mainCanvas = mainCanvas;
        this.buffer = buffer;
        mainCanvas.addMouseListener(this);
    }
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();

    }
    public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
}
