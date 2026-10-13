package Listeners;
import Factories.MObjectInjector;
import PaintInstructions.*;
import Terrain.MapObject;
import WindowFolder.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import Data.MapData;
import WindowFolder.MObjectDisplay.MObjectDisplayControl;
import WindowFolder.PaintPanel.BufferedQueuedPanelV2;

public class CoordinateListener implements MouseListener { //TilePainter
    PaintInjector paintInjector;
    MapGrid mapGrid;
    MapData mapData;
    MObjectInjector injector;
    MObjectDisplayControl show;
    public CoordinateListener(BufferedQueuedPanelV2 target, MapGrid mapGrid, MObjectInjector injector,
                              PaintInjector paintInjector, MObjectDisplayControl show, MapData data) {
        target.setMouseListener(this);
        this.mapGrid = mapGrid;
        mapData = data;
        this.paintInjector = paintInjector;
        this.injector = injector;
        this.show = show;
    }
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            paintTile(e.getPoint());
        }
        else if(e.getButton() == MouseEvent.BUTTON3) {
            Point p = mapGrid.getCoordinate(e.getPoint());
            ArrayList<MapObject> mObjects = new ArrayList<>();
            if(mapData.containsTerrain(p)) {
                mObjects.add(mapData.getTerrain(p));
            }
            if(mapData.containsUnit(p)) {
                mObjects.add(mapData.getUnit(p));
            }
            MapObject[] result = new MapObject[mObjects.size()];
            mObjects.toArray(result);
            show.displayMObjects(result);
        }

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
}
