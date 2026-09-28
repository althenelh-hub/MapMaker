package WindowFolder;

import java.awt.*;

public class MapGrid {
    int columns;
    int rows;
    int cellSize;
    int lineWidth;
    Point startingPoint;
    public MapGrid(int columns, int rows, int cellSize, int lineWidth) {
        this.columns = columns;
        this.rows = rows;
        this.cellSize = cellSize;
        this.lineWidth = lineWidth;
    }
    public Point getCoordinate(Point point) {
        int borderedCell = cellSize+lineWidth;
        int mapWidth = columns*borderedCell+lineWidth;
        int mapHeight = rows*borderedCell+lineWidth;
        if(point.x < mapWidth && point.y < mapHeight) { //later change for exception
            return new Point(point.x / borderedCell, point.y / borderedCell);
        }
        else {
            return null;
        }
    }
    public Rectangle getRectToFill(Point point) { //returns rectangle that needs to be filled in coordinate
        System.out.println(point);
        return new Rectangle(point.x*(cellSize+lineWidth)+1, point.y*(cellSize+lineWidth)+1,
                cellSize, cellSize);
    }
    public int getColumnsCount() {
        return columns;
    }
    public int getRowsCount() {
        return rows;
    }
    public int getCellSize() {
        return cellSize;
    }
    public int getLineWidth() {return lineWidth;}
}
