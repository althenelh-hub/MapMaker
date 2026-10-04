package Listeners;
import Data.CellData;
import Factories.Factory;
import Factories.FactoryManager;
import Factories.MObjectInjector;
import PaintInstructions.*;
import Terrain.MapObject;
import WindowFolder.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import Data.MapData;

public class CoordinateListener implements MouseListener { //TilePainter
    PaintInjector paintInjector;
    MapGrid mapGrid;
    MapData mapData;
    MObjectInjector injector;
    int mode = 0;
    public CoordinateListener(BufferedQueuedPanelV2 target, MapGrid mapGrid, MObjectInjector injector,
                              PaintInjector paintInjector) {
        target.setMouseListener(this);
        this.mapGrid = mapGrid;
        mapData = new MapData(mapGrid.getColumnsCount(), mapGrid.getRowsCount());
        this.paintInjector = paintInjector;
        this.injector = injector;
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
        Rectangle rect = mapGrid.getRectToFill(mapGrid.getCoordinate(p));
        Point cell = mapGrid.getCoordinate(p);
        injector.put(cell, mapData);
        paintInjector.paintForm(mapData.getCellData(cell), rect);
    }
    public void updateCell() {

    }
    public void removeElement(Point p, String name) {

    }

}
