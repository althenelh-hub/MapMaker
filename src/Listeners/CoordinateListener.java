package Listeners;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JComponent;
public class CoordinateListener implements MouseListener {
    Point p = new Point(0,0);
    public CoordinateListener(JComponent target) {
        target.addMouseListener(this);
    }
    public void mouseClicked(MouseEvent e) {
        p = e.getPoint();
    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e) {

    }

}
