package Factories;

import Data.Characteristic;
import Data.Form;
import Data.MapData;
import Terrain.Unit;

import java.awt.*;

public class UnitFactory extends Factory<Unit> {
    public UnitFactory(String name) {
        super(name);
    }
    public void put(Point point, MapData data) {
        data.putUnit(point, currentMapObject);
    }
    public boolean check(Point point, MapData data) {
        return data.containsUnit(point);
    }
    public String toString() {
        return "Unit";
    }
    public Unit create(Form form) {
        Unit unit = new Unit();
        unit.name = form.getName();
        form.fillMapObject(unit);
        return unit;
    }
}
