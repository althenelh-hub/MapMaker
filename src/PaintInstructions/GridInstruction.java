package PaintInstructions;
import java.awt.*;
public class GridInstruction implements PaintInstruction { //think about saving color after resetting window
    int columns;
    int rows;
    int cellSize;
    Color cellColor;
    Color lineColor;
    public GridInstruction(int columns, int rows, int cellSize, Color cellColor, Color lineColor) {
        this.columns = columns;
        this.rows = rows;
        this.cellSize = cellSize;
        this.cellColor = cellColor;
        this.lineColor = lineColor;
    }
    public void fulify(Graphics g) {
        g.setColor(cellColor);
        g.fillRect(0, 0, columns*cellSize, rows*cellSize);
        g.setColor(lineColor);
        for(int i = 0;i < columns+1;i++) {
            g.drawLine(i*cellSize, 0, i*cellSize, rows*cellSize);
        }
        for(int i = 0;i < rows+1;i++) {
            g.drawLine(0, i*cellSize, columns*cellSize,i*cellSize);
        }

    }
}
