package PaintInstructions;
import java.awt.image.*;
import java.awt.*;
public class MapInstruction {
    BufferedImage image;
    int cellSize;
    int lineWidth;
    int columns;
    int rows;
    public MapInstruction(BufferedImage image) {
        this.image = image;
    }
    public int getWidth(){
        return columns*(cellSize+lineWidth)+lineWidth;
    }
    public int getHeight() {
        return columns*(cellSize+lineWidth)+lineWidth;
    }
    public void addColumn() {
        Graphics g = image.createGraphics();
        columns++;
        g.drawLine(getWidth(),0,getWidth(),getHeight());
        g.dispose();
    }
    public void addRow() {
        Graphics g = image.createGraphics();
        rows++;
        g.drawLine(0, getHeight(), getWidth(), getHeight());
    }

}
