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
    public void reshape(int x, int y) {
        Terrain[][] nextMap = new Terrain[x][y];
        int xSize = Math.min(nextMap.length, terrainMap.length);
        int ySize = Math.min(nextMap[0].length, terrainMap[0].length);
        for(int i = 0;i < xSize;i++) {
            for(int j = 0;j < ySize;j++) {
                nextMap[i][j] = terrainMap[i][j];
            }
        }
        terrainMap = nextMap;
    }
    public Unit getUnit(Point p) {
        return units.get(p);
    }
    public CellData getCellData(Point p) {
        CellData data = new CellData();
        ArrayList<String> names = new ArrayList<>();
        if(containsTerrain(p)) {
            names.add(terrainMap[p.x][p.y].name);
        }
        else {
            names.add("baseTerrain");
        }
        if(containsUnit(p)) {
            names.add(units.get(p).name);
            names.add(units.get(p).getFaction());
        }
        String[] result = new String[names.size()];
        data.mapObjects = names.toArray(result);
        return data;
    }
    public void putUnit(Point point, Unit unit) {
        units.put(point, unit);
    }
    public void putTerrain(Point point, Terrain terrain) {
        System.out.println("Inside MapData: " + terrain);
        terrainMap[point.x][point.y] = terrain;
        System.out.println("Inside MapData: " + terrainMap[point.x][point.y]);
    }
}
