package Factories;

import Data.MapData;
import Terrain.MapObject;

import java.awt.*;
import java.util.HashMap;

public abstract class Factory<T extends MapObject> {
    HashMap<String, T> mapObjects;
    T currentMapObject;
    abstract void put(Point point, MapData data);
    abstract boolean check(Point point, MapData data);
    public void change(String name) {
        currentMapObject = mapObjects.get(name);
    }
    public void create(String name, T mapObject) {
        mapObjects.put(name, mapObject);
    }
}
