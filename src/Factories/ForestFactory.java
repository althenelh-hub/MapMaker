package Factories;

import Data.*;
import Terrain.Terrain;

import java.awt.*;

public class ForestFactory extends Factory<Terrain>{
    public ForestFactory(String name) {
        super(name);
    }
    public Terrain create(Form form) {
        Terrain terrain = new Terrain();
        terrain.name = form.getName();
        form.fillMapObject(terrain);
        return terrain;
    }
    public void put(Point point, MapData data) {
        data.putTerrain(point, currentMapObject);
        System.out.println("Inside factory: " + data.getCellData(point));
    }
    public String toString() {
        return "Forest";
    }
    public boolean check(Point point, MapData mapData) {
        return mapData.containsTerrain(point);
    }

}
