package WindowFolder;

import Data.MapData;
import PaintInstructions.Painter;

public class MovementControl {
    MapData data;
    Painter painter;
    public MovementControl(MapData data, Painter painter) {
        this.data = data;
        this.painter = painter;
    }
}
