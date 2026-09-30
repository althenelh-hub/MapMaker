package Data;
import java.awt.*;
import java.util.*;
import Terrain.*;
import Utilities.Coordinate;

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
        ArrayList<GameName> names = new ArrayList<>();
        if(containsTerrain(p)) {
            names.add(terrainMap[p.x][p.y].name);
        }
        if(containsUnit(p)) {
            names.add(units.get(p).name);
        }
        GameName[] result = new GameName[names.size()];
        data.mapObjects = names.toArray(result);
        return data;
    }
    public Unit getUnit(Point coordinate) {
        return units.getOrDefault(coordinate, null);
    }
}
