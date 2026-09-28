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
    public GridInstruction(int columns, int rows, int cellSize, int lineWidth,Color cellColor, Color lineColor) {
        this.columns = columns;
        this.rows = rows;
        this.cellSize = cellSize;
        this.lineWidth = lineWidth;
        this.cellColor = cellColor;
        this.lineColor = lineColor;
    }
    public GridInstruction(MapGrid grid, Color cellColor, Color lineColor) {
        this.columns = grid.getColumnsCount();
        this.rows = grid.getRowsCount();
        this.cellSize = grid.getCellSize();
        this.lineWidth = grid.getLineWidth();
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
            g.drawLine(i*borderedCell, 0, i*borderedCell, rows*borderedCell);
        }
        for(int i = 0;i < rows+1;i++) {
            g.drawLine(0, i*borderedCell, columns*borderedCell,i*borderedCell);
        }

    }
}
