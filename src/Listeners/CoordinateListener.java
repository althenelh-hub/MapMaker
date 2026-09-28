package Listeners;
import Data.CellData;
import PaintInstructions.*;
import Terrain.GameName;
import WindowFolder.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import Data.MapData;

public class CoordinateListener implements MouseListener { //TilePainter
    Painter painter;
    MapGrid mapGrid;
    MapData mapData;
    public CoordinateListener(BufferedQueuedPanelV2 target, MapGrid mapGrid) {
        target.setMouseListener(this);
        this.mapGrid = mapGrid;
        mapData = new MapData(mapGrid.getColumnsCount(), mapGrid.getRowsCount());
        painter = new Painter(target);
        int lineWidth = mapGrid.getLineWidth();
        int cellSize = mapGrid.getCellSize();
        painter.manageAllInstructions (new Rectangle(lineWidth, lineWidth, cellSize, cellSize));
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
        Rectangle rect = mapGrid.getRectToFill(mapGrid.getCoordinate(p));
        Point cell = mapGrid.getCoordinate(p);
        painter.paintForm(mapData.getCellData(cell), rect);
    }
    public void removeElement(Point p, GameName name) {

    }

}
