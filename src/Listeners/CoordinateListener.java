package Listeners;
import Data.CellData;
import Factories.Factory;
import Factories.FactoryManager;
import Factories.FactorySwitch;
import PaintInstructions.*;
import Terrain.MapObject;
import WindowFolder.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import Data.MapData;

public class CoordinateListener implements MouseListener { //TilePainter
    Painter painter;
    MapGrid mapGrid;
    MapData mapData;
    FactorySwitch factorySwitch;
    int mode = 0;
    public CoordinateListener(BufferedQueuedPanelV2 target, MapGrid mapGrid, FactorySwitch manager, Painter painter) {
        target.setMouseListener(this);
        this.mapGrid = mapGrid;
        mapData = new MapData(mapGrid.getColumnsCount(), mapGrid.getRowsCount());
        this.painter = painter;
        this.factorySwitch = manager;
    }
    public void mouseClicked(MouseEvent e) {
        int button = e.getButton();
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
        System.out.println("Point data: " + p);
        Rectangle rect = mapGrid.getRectToFill(mapGrid.getCoordinate(p));
        Point cell = mapGrid.getCoordinate(p);
        painter.paintForm(mapData.getCellData(cell), rect);
        factorySwitch.put(cell, mapData);
        System.out.println("Show cellData: " + mapData.getCellData(cell));
    }
    public void updateCell() {

    }
    public void removeElement(Point p, String name) {

    }

}
