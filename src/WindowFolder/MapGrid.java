package WindowFolder;


import java.awt.*;

public class MapGrid {
    int columns;
    int rows;
    int cellSize;
    int lineWidth;
    Point startingPoint = new Point(0,0);
    public MapGrid(int columns, int rows, int cellSize, int lineWidth, Point startPosition) {
        this.columns = columns;
        this.rows = rows;
        this.cellSize = cellSize;
        this.lineWidth = lineWidth;
        startingPoint = startPosition;
    }
    public Point getCoordinate(Point point) {
        int borderedCell = cellSize+lineWidth;
        int mapWidth = columns*borderedCell+lineWidth+startingPoint.x;
        int mapHeight = rows*borderedCell+lineWidth+startingPoint.y;
        Point offset = new Point(point.x-startingPoint.x, point.y-startingPoint.y);
        if(offset.x < mapWidth && offset.y < mapHeight) {
            return new Point(offset.x / borderedCell, offset.y / borderedCell);
        }
        else {
            return null;
        }
    }
    public Rectangle getRectToFill(Point point) { //returns rectangle that needs to be filled in coordinate
        return new Rectangle(point.x*(cellSize+lineWidth)+1,
                point.y*(cellSize+lineWidth)+1,
                cellSize, cellSize);
    }
    public int getColumnsCount() {
        return columns;
    }
    public int getWidth() {
        return columns*(lineWidth+cellSize)+lineWidth;
    }
    public int getHeight() {
        return rows*(lineWidth+cellSize)+lineWidth;
    }
    public int getRowsCount() {
        return rows;
    }
    public int getCellSize() {
        return cellSize;
    }
    public int getLineWidth() {return lineWidth;}
    public Point getStartingPoint() {return startingPoint;}
    public void setColumns(int columns) {
        this.columns = columns;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }
    public void setLineWidth(int width) {
        lineWidth = width;
    }
    public void setStartingPoint(Point p) {
        startingPoint.x = p.x;
        startingPoint.y = p.y;
    }
    public void reshape(Rectangle parent) {
        startingPoint.x = (parent.width-getWidth())/2;
        startingPoint.y = (parent.height-getHeight())/2;
    }
}
