package WindowFolder;

import Data.MapData;
import PaintInstructions.GridInstruction;
import PaintInstructions.PaintImageV2;
import PaintInstructions.Painter;
import WindowFolder.PaintPanel.QueuedPaintPanel;

import java.awt.*;
import java.awt.image.*;

public class MapControl {
    MapGrid grid;
    PaintImageV2 paintImage;
    MapData data;
    Painter painter;
    BufferedImage image;
    QueuedPaintPanel instructionPanel;
    public MapControl(BufferedImage image, QueuedPaintPanel panel, MapGrid grid, PaintImageV2 paintImageV2, MapData data,
                      Painter painter) {
        this.image = image;
        this.instructionPanel = panel;
        this.grid = grid;
        this.paintImage = paintImageV2;
        this.data = data;
        this.painter = painter;
    }
    public void addColumns(int columns) {
        grid.setColumns(columns);
        grid.reshape(instructionPanel.getBounds());
        repaintImage();
    }
    public void addRows(int rows) {
        grid.setRows(rows);
        grid.reshape(instructionPanel.getBounds());
        repaintImage();

    }
    public void changeCellSize(int cellSize) {
        grid.setCellSize(cellSize);
        grid.reshape(instructionPanel.getBounds());
        paintImage.changeSize(new Point(grid.getWidth(), grid.getHeight()));
        instructionPanel.changeSize(new Point(grid.getWidth(), grid.getHeight()));
        GridInstruction instruction = new GridInstruction(grid, Color.RED, Color.BLACK);
        Graphics imageGraphics = image.createGraphics();
        instruction.fulfill(imageGraphics, new Rectangle(0,0,0,0));
        painter.repaintWhole(data, grid, image);


    }
    public void repaintImage() {
        data.reshape(grid.columns, grid.rows);
        instructionPanel.changeSize(new Point(grid.getWidth(), grid.getHeight()));
        Point point = paintImage.getSize();
        BufferedImage buffer = new BufferedImage(point.x, point.y, BufferedImage.TYPE_INT_RGB);
        paintImage.changeSize(new Point(grid.getWidth(), grid.getHeight()));
        Graphics bufferGraphics = buffer.createGraphics();
        paintImage.fulfill(bufferGraphics, new Rectangle(0,0,0,0));
        Graphics g = image.createGraphics();
        GridInstruction instruction = new GridInstruction(grid, Color.GRAY, Color.BLACK);
        instruction.fulfill(g, new Rectangle(0,0,0,0));
        PaintImageV2 bufferPaintImage = new PaintImageV2(new Point(buffer.getWidth(), buffer.getHeight()), buffer);
        bufferPaintImage.fulfill(g, new Rectangle(0,0,0,0));
        instructionPanel.repaint();
    }
}
