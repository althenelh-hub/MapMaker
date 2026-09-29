package WindowFolder;
import PaintInstructions.*;
import java.awt.*;
public class SizeManager {
    MapGrid grid;
    PaintImage paintImage;
    public void reshape(Point size) {
        grid.reshape(size);
    }
}
