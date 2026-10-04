package PaintInstructions;
import java.awt.*;
import WindowFolder.*;
public class GridInstruction implements PaintInstructionV2 { //think about saving color after resetting window
    int columns;
    int rows;
    int cellSize;
    int lineWidth;
    Color cellColor;
    Color lineColor;
    public GridInstruction(int columns, int rows, int cellSize, int lineWidth,Color cellColor,
                           Color lineColor) {
        this(new MapGrid(columns, rows, cellSize, lineWidth, new Point(0,0)), cellColor, lineColor);
    }
    public GridInstruction(MapGrid grid, Color cellColor, Color lineColor) {
        columns = grid.getColumnsCount();
        rows = grid.getRowsCount();
        cellSize = grid.getCellSize();
        lineWidth = grid.getLineWidth();
        this.cellColor = cellColor;
        this.lineColor = lineColor;
    }
    public void fulfill(Graphics g, Rectangle rectangle) {
        g.setColor(cellColor);
        int gridWidth = columns*(cellSize+lineWidth)+lineWidth;
        int gridHeight = rows*(cellSize+lineWidth)+lineWidth;
        g.fillRect(0, 0, gridWidth, gridHeight);
        g.setColor(lineColor);
        int borderedCell = cellSize+lineWidth;
        for(int i = 0;i < columns+1;i++) {
            g.drawLine(i*borderedCell+ rectangle.x, rectangle.y,
                    i*borderedCell+ rectangle.x, rows*borderedCell+ rectangle.y);
        }
        for(int i = 0;i < rows+1;i++) {
            g.drawLine(rectangle.x, i*borderedCell+ rectangle.y,
                    columns*borderedCell+ rectangle.x,i*borderedCell+ rectangle.y);
        }

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
