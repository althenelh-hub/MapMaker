package PaintInstructions;
import java.awt.*;
import WindowFolder.*;
public class GridInstruction extends LayeredInstruction { //think about saving color after resetting window
    int columns;
    int rows;
    int cellSize;
    int lineWidth;
    Color cellColor;
    Color lineColor;
    Point startPosition;
    public GridInstruction(int columns, int rows, int cellSize, int lineWidth,Color cellColor,
                           Color lineColor, Point startPosition) {
        this.columns = columns;
        this.rows = rows;
        this.cellSize = cellSize;
        this.lineWidth = lineWidth;
        this.cellColor = cellColor;
        this.lineColor = lineColor;
        this.startPosition = startPosition;
    }
    public GridInstruction(MapGrid grid, Color cellColor, Color lineColor) {
        columns = grid.getColumnsCount();
        rows = grid.getRowsCount();
        cellSize = grid.getCellSize();
        lineWidth = grid.getLineWidth();
        startPosition = grid.getStartingPoint();
        this.cellColor = cellColor;
        this.lineColor = lineColor;
    }
    public void fulfill(Graphics g) {
        g.setColor(cellColor);
        int gridWidth = columns*(cellSize+lineWidth)+lineWidth;
        int gridHeight = rows*(cellSize+lineWidth)+lineWidth;
        g.fillRect(0, 0, gridWidth, gridHeight);
        g.setColor(lineColor);
        int borderedCell = cellSize+lineWidth;
        for(int i = 0;i < columns+1;i++) {
            g.drawLine(i*borderedCell+ startPosition.x, startPosition.y,
                    i*borderedCell+ startPosition.x, rows*borderedCell+ startPosition.y);
        }
        for(int i = 0;i < rows+1;i++) {
            g.drawLine(startPosition.x, i*borderedCell+ startPosition.y,
                    columns*borderedCell+ startPosition.x,i*borderedCell+ startPosition.y);
        }

    }
    public void setStartPosition(int x, int y) {
        startPosition.x = x;
        startPosition.y = y;
    }
    public void setStartPosition(Point p) {
        startPosition.x = p.x;
        startPosition.y = p.y;
    }
    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }
    public void setColumns(int columns) {
        this.columns = columns;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public void setLineWidth(int width) {
        lineWidth = width;
    }
    public void setCellColor(Color color) {
        cellColor = color;
    }
    public void setLineColor(Color color) {
        lineColor = color;
    }
}
