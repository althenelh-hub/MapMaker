package Data;
import java.awt.*;
import java.util.HashMap;
import Terrain.*;
public class MapData {
    HashMap<Point, Unit> units = new HashMap<>();
    Terrain[][] terrainMap;
    public MapData(int sizeX, int sizeY) {
        terrainMap = new Terrain[sizeX][sizeY];
    }
    public Terrain getTerrain(Point coordinate) {
        return terrainMap[coordinate.x][coordinate.y];
    }
    public boolean containsTerrain (Point p) {
        return terrainMap[p.x][p.y] != null;
    }
    public boolean containsUnit (Point p) {
        return units.containsKey(p);
    }
    public CellData getCellData(Point p) {
        CellData data = new CellData();
        if(containsTerrain(p)) {
            data.tile = terrainMap[p.x][p.y];
        }
        else {
            data.tile = new Terrain();
            data.tile.name = GameName.EMPTY;
        }
        if(containsUnit(p)) {
            data.unit = units.get(p);
        }
        else {
            data.unit = new Unit();
            data.unit.name = GameName.EMPTY;
        }
        return data;
    }
    public Unit getUnit(Point coordinate) {
        return units.getOrDefault(coordinate, null);
    }
}
