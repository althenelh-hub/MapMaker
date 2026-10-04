package PaintInstructions;

import Data.CellData;

import java.awt.*;

public interface PaintInjector {
    void paintForm(CellData cellData, Rectangle rect);
}
