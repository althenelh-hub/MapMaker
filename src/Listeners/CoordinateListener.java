package Listeners;
import PaintInstructions.PaintTile;
import WindowFolder.BufferedPaintPanelV2;
import WindowFolder.InstructedPaintPanel;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.JComponent;
import java.awt.image.*;
import Utilities.*;
import Units.Unit;
import Terrain.Terrain;
public class CoordinateListener implements MouseListener { //TilePainter
    PaintTile tileInstruction = new PaintTile();
    BufferedPaintPanelV2 paintPanel;
    HashMap<Coordinate, Unit> units;
    Terrain[][] map;
    public CoordinateListener(BufferedPaintPanelV2 target) {
        paintPanel = target;
        paintPanel.setMouseListener(this);
    }
    public void mouseClicked(MouseEvent e) {
        paintTile(e.getPoint());

    }
    public void mouseEntered(MouseEvent e) {

    }
    public void mouseExited(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mousePressed(MouseEvent e) {

    }
    public void paintTile(Point p) {
        tileInstruction.setPosition(p);
        paintPanel.paintInstruction(tileInstruction, p.x, p.y, 19,19);
    }

}
