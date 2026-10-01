package Factories;

import Data.MapData;
import Terrain.Terrain;

import java.awt.*;

public class ForestFactory extends Factory<Terrain>{
    public ForestFactory() {

    }
    public void put(Point point, MapData data) {
        data.putTerrain(point, currentMapObject);
    }
    public boolean check(Point point, MapData mapData) {
        return mapData.containsTerrain(point);
    }

}
