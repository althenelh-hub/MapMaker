package Factories;

import Data.*;
import Terrain.MapObject;

import java.awt.*;
import java.util.HashMap;

public abstract class Factory<T extends MapObject> {
    HashMap<String, T> mapObjects = new HashMap<>();
    T currentMapObject;
    public abstract void put(Point point, MapData data);
    public abstract boolean check(Point point, MapData data);
    public void change(String name) {
        currentMapObject = mapObjects.get(name);
        System.out.println("Current MObject: " + currentMapObject);
    }
    public void changeCurrent(String name, Integer value) {
        currentMapObject.addValue(name, value);
    }
    public void store (Form form) {
        mapObjects.put(form.getName(), create(form));
    }
    protected abstract T create(Form form);

}
