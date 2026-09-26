package Data;
import Terrain.*;
public class CellData {
    public CellData(Terrain tile, Unit unit) {
        this.tile = tile;
        this.unit = unit;
    }
    public CellData() {

    }
    public Terrain tile;
    public Unit unit;
}
