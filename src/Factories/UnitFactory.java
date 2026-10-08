package Factories;

import Data.Characteristic;
import Data.Form;
import Data.MapData;
import Terrain.Faction;
import Terrain.Unit;
import WindowFolder.Keeper;

import java.awt.*;

public class UnitFactory extends Factory<Unit> {
    Keeper<String> faction;
    public UnitFactory(String name) {
        super(name);
        faction = new Keeper<>();
        faction.setValue("Enemy");
    }
    public void put(Point point, MapData data) {
        Unit unit = new Unit();
        currentMapObject.shareValues(unit);
        System.out.println(faction.getValue());
        unit.setFaction(faction.getValue());
        data.putUnit(point, unit);
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
    public Keeper<String> getFaction() {
        return faction;
    }
}
