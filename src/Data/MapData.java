package Data;
import java.awt.*;
import java.util.*;
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
        ArrayList<String> names = new ArrayList<>();
        if(containsTerrain(p)) {
            names.add(terrainMap[p.x][p.y].name);
        }
        if(containsUnit(p)) {
            names.add(units.get(p).name);
        }
        String[] result = new String[names.size()];
        data.mapObjects = names.toArray(result);
        return data;
    }
    public void putUnit(Point point, Unit unit) {
        units.put(point, unit);
    }
    public void putTerrain(Point point, Terrain terrain) {
        terrainMap[point.x][point.y] = terrain;
    }
    public Unit getUnit(Point coordinate) {
        return units.getOrDefault(coordinate, null);
    }
}
