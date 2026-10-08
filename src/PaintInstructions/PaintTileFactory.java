package PaintInstructions;

import java.awt.*;

public class PaintTileFactory implements PaintFormFactory<PaintTile>{
    public PaintTile createForm(Color color) {
        PaintTile tile = new PaintTile();
        tile.setColor(color);
        return tile;
    }
}
