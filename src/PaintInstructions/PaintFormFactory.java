package PaintInstructions;

import java.awt.*;

public interface PaintFormFactory<T extends PaintForm> {
    T createForm(Color color);
}
