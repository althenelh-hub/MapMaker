package Listeners;
import Data.CellData;
import Factories.Factory;
import PaintInstructions.*;
import Terrain.GameName;
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
    GameName[] mapObjects;
    HashMap<GameName, Factory<?>> factories = new HashMap<>();
    int mode = 0;
    public CoordinateListener(BufferedQueuedPanelV2 target, MapGrid mapGrid) {
        target.setMouseListener(this);
        this.mapGrid = mapGrid;
        mapData = new MapData(mapGrid.getColumnsCount(), mapGrid.getRowsCount());
        painter = new Painter(target);
        mapObjects = new GameName[2];
        mapObjects[0] = GameName.FOREST;
        mapObjects[1] = GameName.WARRIOR;
    }
    public void mouseClicked(MouseEvent e) {
        int button = e.getButton();
            if(button == 1) {
            paintTile(e.getPoint());
        }
        if(button == 3) {
            System.out.println("Clicked");
            mode++;
            mode = mode%mapObjects.length;
            painter.setCurrentForm(mapObjects[mode]);
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
        System.out.println("Point data: " + p);
        Rectangle rect = mapGrid.getRectToFill(mapGrid.getCoordinate(p));
        Point cell = mapGrid.getCoordinate(p);
        painter.paintForm(mapData.getCellData(cell), rect);
    }
    public void updateCell() {

    }
    public void removeElement(Point p, GameName name) {

    }

}
