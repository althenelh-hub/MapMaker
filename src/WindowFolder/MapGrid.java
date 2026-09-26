package WindowFolder;

import java.awt.*;

public class MapGrid {
    int columns;
    int rows;
    int cellSize;
    int lineWidth;
    public MapGrid(int columns, int rows, int cellSize, int lineWidth) {
        this.columns = columns;
        this.rows = rows;
        this.cellSize = cellSize;
        this.lineWidth = lineWidth;
    }
    public Point getCoordinate(Point point) {
        if(point.x < columns*(cellSize+lineWidth) && point.y < rows*(cellSize + lineWidth)) { //later change for exception
            return new Point(point.x / (cellSize+lineWidth), point.y / (cellSize + lineWidth));
        }
        else {
            return null;
        }
    }
    public Rectangle getRectToFill(Point point) { //returns rectangle that needs to be filled in coordinate
        System.out.println(point);
        return new Rectangle(point.x*(cellSize+lineWidth), point.y*(cellSize+lineWidth),
                cellSize-lineWidth, cellSize-lineWidth);
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
